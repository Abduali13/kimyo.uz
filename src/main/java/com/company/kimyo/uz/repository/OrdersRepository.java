package com.company.kimyo.uz.repository;

import com.company.kimyo.uz.entity.Category;
import com.company.kimyo.uz.entity.Orders;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Optional<Orders> findOrdersByOrderIdAndDeletedAtIsNull(Integer orderId);

    List<Orders> findAllByDeletedAtIsNotNull();

    @Query(
            value = "select o from Orders as o where o.orderId = :id and o.deletedAt is null",
            countQuery = "select count(o.orderId) from Orders as o where o.orderId = :id and o.deletedAt is null"
    )
    Optional<Orders> findByOrderId(@Param(value = "id") Integer orderId);


    @Query(
            value = "select o from Orders as o order by o.orderId",
            countQuery = "select count(o.orderId) from Orders as o"
    )
    Page<Orders> findAllOrderByPage(Pageable pageable);



    @Query(
            value = "select o from Orders as o where o.orderId = coalesce(:id, o.orderId) and o.totalPrice = coalesce(:price, o.totalPrice) and o.orderDate = coalesce(:date, o.orderDate) ",
            countQuery = "select count(o.orderId) from Orders as o where o.orderId = coalesce(:id, o.orderId) and o.totalPrice = coalesce(:price, o.totalPrice) and o.orderDate = coalesce(:date, o.orderDate) "
    )
    Page<Orders> searchAllOrderWithMoreParams(
            @Param(value = "id") Integer orderId,
            @Param(value = "price") Double totalPrice,
            @Param(value = "date") LocalDate orderDate,
            Pageable pageable
    );


}
