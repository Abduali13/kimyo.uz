package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.request.RequestCategoryDto;
import com.company.kimyo.uz.dto.response.ResponseCategoryDto;
import com.company.kimyo.uz.entity.Category;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T23:54:07+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl extends CategoryMapper {

    @Override
    public Category toEntity(RequestCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.categoryName( dto.getCategoryName() );

        return category.build();
    }

    @Override
    public ResponseCategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        ResponseCategoryDto.ResponseCategoryDtoBuilder responseCategoryDto = ResponseCategoryDto.builder();

        responseCategoryDto.categoryId( category.getCategoryId() );
        responseCategoryDto.categoryName( category.getCategoryName() );
        responseCategoryDto.createdAt( category.getCreatedAt() );
        responseCategoryDto.updatedAt( category.getUpdatedAt() );
        responseCategoryDto.deletedAt( category.getDeletedAt() );

        return responseCategoryDto.build();
    }

    @Override
    public ResponseCategoryDto toDtoWithProducts(Category category) {
        if ( category == null ) {
            return null;
        }

        ResponseCategoryDto.ResponseCategoryDtoBuilder responseCategoryDto = ResponseCategoryDto.builder();

        responseCategoryDto.categoryId( category.getCategoryId() );
        responseCategoryDto.categoryName( category.getCategoryName() );
        responseCategoryDto.createdAt( category.getCreatedAt() );
        responseCategoryDto.updatedAt( category.getUpdatedAt() );
        responseCategoryDto.deletedAt( category.getDeletedAt() );

        responseCategoryDto.products( category.getProducts().stream().map(this.productMapper::toDto).collect(Collectors.toSet()) );

        return responseCategoryDto.build();
    }

    @Override
    public Category updateCategory(Category category, RequestCategoryDto dto) {
        if ( dto == null ) {
            return category;
        }

        if ( dto.getCategoryName() != null ) {
            category.setCategoryName( dto.getCategoryName() );
        }

        return category;
    }
}
