package com.company.kimyo.uz.repository;

import com.company.kimyo.uz.entity.Product;
import com.company.kimyo.uz.entity.User;
import jakarta.persistence.EntityManager;
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
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // todo: Native query
    // todo: 1 - example Native Query
//    @Query(value = "select * from product as p where p.prod_id = :id and deleted_at is null",
//            nativeQuery = true
//    )

    //todo: 2 - example HQL
    @Query(value = "select p from Product as p where p.prodId = :id and p.deletedAt is null")

    //todo: 3 - example Named Query
//    @Query(name = "findByProductId")
    Optional<Product> findByProductId(@Param(value = "id") Integer prodId);


    // todo: HQL
    @Query(
            value = "select p from Product as p order by p.prodId",
            countQuery = "select count(p.prodId) from Product as p"
    )
    Page<Product> findAllProductByPage(Pageable pageable);

    Optional<Product> findByProdIdAndDeletedAtIsNull(Integer productId);

    List<Product> findAllByDeletedAtIsNotNull();


    @Query(
            value = "select p from Product  as p " +
                    "where p.prodId = coalesce(:id, p.prodId) " +
                    "and p.prodName = coalesce(:name, p.prodName) " +
                    "and p.prodColor = coalesce(:color, p.prodColor) " +
                    "and p.prodType = coalesce(:type, p.prodType) " +
                    "and p.prodAmount >= coalesce(:amount, p.prodAmount) " +
                    "and p.prodPrice >= coalesce(:price, p.prodPrice) ",

            countQuery = "select count(p.prodId) from Product as p " +
                    "where p.prodId = coalesce(:id, p.prodId) \n" +
                    "  and p.prodName = coalesce(:name, p.prodName) \n" +
                    "  and p.prodColor = coalesce(:color, p.prodColor) \n" +
                    "  and p.prodType = coalesce(:type, p.prodType) \n" +
                    "  and p.prodAmount >= coalesce(:amount, p.prodAmount) \n" +
                    "  and p.prodPrice >= coalesce(:price, p.prodPrice)"
    )
    Page<Product> searchAllProductWithMoreParams(
            @Param(value = "id") Integer prodId,
            @Param(value = "name") String prodName,
            @Param(value = "color") String prodColor,
            @Param(value = "type") String prodType,
            @Param(value = "price") Double prodPrice,
            @Param(value = "amount") Integer prodAmount,
            Pageable pageable
    );

}
