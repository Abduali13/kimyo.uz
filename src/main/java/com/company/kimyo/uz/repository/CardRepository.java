package com.company.kimyo.uz.repository;


import com.company.kimyo.uz.entity.Card;
import com.company.kimyo.uz.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    // todo: method query - (+)
    // todo: native query
    // todo: HQL - Hibernate Query Language


    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer cardId);

    List<Card> findAllByDeletedAtIsNotNull();



    @Query(
            value = "select c from Card as c where c.cardId = :id and c.deletedAt is null ",
            countQuery = "select count(c.cardId) from Card as c where c.cardId  = :id and c.deletedAt is null"
    )
    Optional<Card> findByCardId(@Param(value = "id") Integer cardId);


    @Query(
            value = "select c from Card as c order by c.cardId",
            countQuery = "select count(c.cardId) from Card as c"
    )
    Page<Card> findAllCardByPage(Pageable pageable);



    @Query(
            value = "select c from Card as c where c.cardId = coalesce(:id, c.cardId) " +
                    " and c.cardName = coalesce(:cardName, c.cardName)" +
                    " and c.cardFullName = coalesce(:cardFullName, c.cardFullName) " +
                    " and c.cardCode = coalesce(:cardCode, c.cardCode) ",
            countQuery = "select count(c.cardId) from Card as c where c.cardId = coalesce(:id, c.cardId) " +
                    " and c.cardName = coalesce(:cardName, c.cardName)" +
                    " and c.cardFullName = coalesce(:cardFullName, c.cardFullName) " +
                    " and c.cardCode = coalesce(:cardCode, c.cardCode) "
    )
    Page<Card> searchAllUserWithMoreParams(
            @Param(value = "id") Integer cardId,
            @Param(value = "cardName") String cardName,
            @Param(value = "cardFullName") String cardFullName,
            @Param(value = "cardCode") String cardCode,
            Pageable pageable
    );


}
