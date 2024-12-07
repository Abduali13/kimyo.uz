package com.company.kimyo.uz.repository.impl;


import com.company.kimyo.uz.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Product> searchAllProductWithMoreParams(Map<String, String> params) {
        int size = 10, page=0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }

        String firstQuery = "select p from Product as p where true";
        String secondQuery = "select count(p.id) from Product as p where true";

        StringBuilder buildQuery = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + buildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + buildQuery);

        queryOne.setFirstResult(size * page );
        queryOne.setMaxResults(size);


        setParams(queryOne, params);
        setParams(queryTwo, params);

        return new PageImpl<Product>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")){
            query.setParameter("prodId", params.get("id"));
        }
        if (params.containsKey("prodName")){
            query.setParameter("prodName", params.get("prodName"));
        }
        if (params.containsKey("prodType")){
            query.setParameter("prodType", params.get("prodType"));
        }
        if (params.containsKey("prodColor")){
            query.setParameter("prodColor", params.get("prodColor"));
        }
        if (params.containsKey("prodPrice")){
            query.setParameter("prodPrice", params.get("prodPrice"));
        }
        if (params.containsKey("prodAmount")){
            query.setParameter("prodAmount", params.get("prodAmount"));
        }

    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
            stringBuilder.append(" And p.id = :prodId");
        }
        if (params.containsKey("prodName")){
            stringBuilder.append(" And lower(p.prodName) like concat(lower(:prodName), '%')");
        }
        if (params.containsKey("prodType")){
            stringBuilder.append(" And lower(p.prodType) like concat(lower(:prodType), '%')");
        }
        if (params.containsKey("prodColor")){
            stringBuilder.append(" And lower(p.prodColor) like concat(lower(:prodColor), '%')");
        }
        if (params.containsKey("prodPrice")){
            stringBuilder.append(" And p.prodPrice >= :prodPrice");
        }
        if (params.containsKey("prodAmount")){
            stringBuilder.append(" And p.prodAmount >= :prodAmount");
        }
        return stringBuilder;
    }
}
