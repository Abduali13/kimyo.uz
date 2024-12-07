package com.company.kimyo.uz.repository.impl;

import com.company.kimyo.uz.entity.Orders;
import com.company.kimyo.uz.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl {
    private final EntityManager entityManager;

    public Page<Orders> searchAllOrdersWithMoreParams(Map<String, String> params) {
        int size = 10, page=0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }

        String firstQuery = "select o from Orders as o where true";
        String secondQuery = "select count(o.orderId) from Orders as o where true";

        StringBuilder buildQuery = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + buildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + buildQuery);

        queryOne.setFirstResult(size * page );
        queryOne.setMaxResults(size);


        setParams(queryOne, params);
        setParams(queryTwo, params);

        return new PageImpl<Orders>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")){
            query.setParameter("orderId", params.get("id"));
        }
        if (params.containsKey("price")){
            query.setParameter("totalPrice", params.get("price"));
        }
        if (params.containsKey("date")){
            query.setParameter("orderDate", params.get("date"));
        }

    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
            stringBuilder.append(" And o.orderId = :id");
        }
        if (params.containsKey("price")){
            stringBuilder.append(" And lower(o.totalPrice) like concat(lower(:price), '%')");
        }
        if (params.containsKey("date")){
            stringBuilder.append(" And lower(o.orderDate) like concat(lower(:date), '%')");
        }
        return stringBuilder;

    }
}
