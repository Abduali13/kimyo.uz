package com.company.kimyo.uz.repository;

import com.company.kimyo.uz.entity.Category;
import com.company.kimyo.uz.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer categoryId);

    List<Category> findAllByDeletedAtIsNotNull();


    @Query(
            value = "select c from Category c where c.categoryId=:id and c.deletedAt is null",
            countQuery = "select count(c.categoryId) from Category as c where c.categoryId = :id and c.deletedAt is null"
    )
    Optional<Category> findByCategoryId(@Param(value = "id") Integer categoryId);

    @Query(
            value = "select c from Category as c order by c.categoryId",
            countQuery = "select count(c.categoryId) from Category as c"
    )
    Page<Category> findAllCategoryByPage(Pageable pageable);

    @Query(
            value = "select c from Category as c where c.categoryId = coalesce(:id, c.categoryId) and c.categoryName = coalesce(:name, c.categoryName) ",
            countQuery = "select count(c.categoryId) from Category as c where c.categoryId = coalesce(:id, c.categoryId) and c.categoryName = coalesce(:name, c.categoryName) "
    )
    Page<Category> searchAllCategoryWithMoreParams(
            @Param(value = "id") Integer categoryId,
            @Param(value = "name") String categoryName,
            Pageable pageable
    );


}
