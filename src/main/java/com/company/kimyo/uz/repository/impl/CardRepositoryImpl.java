package com.company.kimyo.uz.repository.impl;


import com.company.kimyo.uz.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.QueryTimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Card> searchAllCardWithMoreParams(Map<String, String> params) {

        int size = 10, page = 0;
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }

        String firstQuery = "select c from Card as c where true";
        String secondQuery = "select count(c.cardId) from Card c where true";

        StringBuilder buildQuery = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + buildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + buildQuery);

        queryOne.setFirstResult(size * page);
        queryTwo.setMaxResults(size);

        setParams(queryOne, params);
        setParams(queryTwo, params);


        return new PageImpl<>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );

    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("cardId", params.get("id"));
        }
        if (params.containsKey("cardName")) {
            query.setParameter("cardName", params.get("cardName"));
        }
        if (params.containsKey("cardFullName")) {
            query.setParameter("cardFullName", params.get("cardFullName"));
        }
        if (params.containsKey("cardCode")) {
            query.setParameter("cardCode", params.get("cardCode"));
        }
    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")) {
            stringBuilder.append(" and c.id = :cardId");
        }
        if (params.containsKey("cardName")) {
            stringBuilder.append(" and lower(c.cardName) like concat(lower(:cardName), '%')");
        }
        if (params.containsKey("cardFullName")) {
            stringBuilder.append(" and lower(c.cardFullName) concat (lower(cardFullName), '%')");
        }
        if (params.containsKey("cardCode")) {
            stringBuilder.append(" and lower(c.cardCode) concat(lower( cardCode), '%')");
        }
        return stringBuilder;
    }
}
