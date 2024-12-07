package com.company.kimyo.uz.repository.impl;


import com.company.kimyo.uz.entity.Category;
import com.company.kimyo.uz.entity.Orders;
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
public class CategoryRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Category> searchAllCategoryWithMoreParams(Map<String, String> params){
        int size = 10, page=0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }

        String firstQuery = "select c from Category as c where true";
        String secondQuery = "select count(c.categoryId) from Category as c where true";

        StringBuilder buildQuery = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + buildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + buildQuery);

        queryOne.setFirstResult(size * page );
        queryOne.setMaxResults(size);


        setParams(queryOne, params);
        setParams(queryTwo, params);

        return new PageImpl<Category>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );
    }
    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")){
            query.setParameter("categoryId", params.get("id"));
        }
        if (params.containsKey("name")){
            query.setParameter("categoryName", params.get("name"));
        }

    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
            stringBuilder.append(" And c.categoryId = :id");
        }
        if (params.containsKey("name")){
            stringBuilder.append(" And lower(c.categoryName) like concat(lower(:name), '%')");
        }
        return stringBuilder;

    }
}
