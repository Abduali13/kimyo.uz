package com.company.kimyo.uz.repository;

import com.company.kimyo.uz.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //    List<User> findAllByDeletedAtIsNot();

    @Query(
            value = "select u from User as u where u.userId = :id and u.deletedAt is null ",
            countQuery = "select count(u.userId) from User as u where u.userId = :id and u.deletedAt is null ")
    Optional<User> findByUserId(@Param(value = "id") Integer userId);

    @Query(
            value = "select u from User as u order by u.userId",
            countQuery = "select count(u.userId) from User as u"
    )
    Page<User> findAllUserByPage(Pageable pageable);


    List<User> findAllByDeletedAtIsNotNull();

    Optional<User> findUserByUserIdAndDeletedAtIsNull(Integer userid);

//    Optional<User> findUserByUserIdAndDeletedAtIsNullOrderByCardsDesc(Integer userid);


    @Query(
            value = "select u from User as u where u.userId = coalesce(:id, u.userId)" +
                    " and u.firstname = coalesce(:firstname, u.firstname) " +
                    " and u.lastname = coalesce(:lastname, u.lastname) " +
                    " and u.email = coalesce(:email, u.email)" +
                    " and u.password = coalesce(:password, u.password)" +
                    " and u.age = coalesce(:age, u.age) ",
            countQuery = "select count(u.userId) from User as u where u.userId = coalesce(:id, u.userId)" +
                    " and u.firstname = coalesce(:firstname, u.firstname) " +
                    " and u.lastname = coalesce(:lastname, u.lastname) " +
                    " and u.email = coalesce(:email, u.email)" +
                    " and u.password = coalesce(:password, u.password)" +
                    " and u.age = coalesce(:age, u.age) "
    )
    Page<User> searchAllUserWithMoreParams(
            @Param(value = "id") Integer userId,
            @Param(value = "firstname") String firstname,
            @Param(value = "lastname") String lastname,
            @Param(value = "email") String email,
            @Param(value = "password") String password,
            @Param(value = "age") Integer age,
            Pageable pageable
    );

}

