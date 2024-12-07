package com.company.kimyo.uz.repository.impl;

import com.company.kimyo.uz.entity.Product;
import com.company.kimyo.uz.entity.User;
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
public class UserRepositoryImpl {

    private final EntityManager entityManager;

    public Page<User> searchAllUserWithMoreParams(Map<String, String> params) {
        int size = 10, page=0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }

        String firstQuery = "select u from User as u where true";
        String secondQuery = "select count(u.userId) from User as u where true";

        StringBuilder buildQuery = buildParams(params);

        Query queryOne = this.entityManager.createQuery(firstQuery + buildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + buildQuery);

        queryOne.setFirstResult(size * page );
        queryOne.setMaxResults(size);


        setParams(queryOne, params);
        setParams(queryTwo, params);

        return new PageImpl<User>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")){
            query.setParameter("userId", params.get("id"));
        }
        if (params.containsKey("firstname")){
            query.setParameter("firstname", params.get("firstname"));
        }
        if (params.containsKey("lastname")){
            query.setParameter("lastname", params.get("lastname"));
        }
        if (params.containsKey("email")){
            query.setParameter("email", params.get("email"));
        }
        if (params.containsKey("password")){
            query.setParameter("password", params.get("password"));
        }
        if (params.containsKey("age")){
            query.setParameter("age", params.get("age"));
        }

    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")){
            stringBuilder.append(" And u.userId = :id");
        }
        if (params.containsKey("firstname")){
            stringBuilder.append(" And u.firstname = :firstname");
        }
        if (params.containsKey("lastname")){
            stringBuilder.append(" And u.lastname = :lastname");
        }
        if (params.containsKey("email")){
            stringBuilder.append(" And u.email = :email");
        }
        if (params.containsKey("password")){
            stringBuilder.append(" And u.password = :password");
        }
        if (params.containsKey("age")){
            stringBuilder.append(" And u.age = :age");
        }
        return stringBuilder;
    }
}
