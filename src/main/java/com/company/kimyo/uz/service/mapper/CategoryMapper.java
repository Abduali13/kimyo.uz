package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.request.RequestCategoryDto;
import com.company.kimyo.uz.dto.response.ResponseCategoryDto;
import com.company.kimyo.uz.entity.Category;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel="spring", imports = Collectors.class)
//@Component
public abstract class CategoryMapper {

    @Lazy
    @Autowired
    protected ProductMapper productMapper;


    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Category toEntity(RequestCategoryDto dto);


    @Mapping(target = "products", ignore = true)
    public abstract ResponseCategoryDto toDto(Category category);

    @Mapping(target = "products", expression = "java(category.getProducts().stream().map(this.productMapper::toDto).collect(Collectors.toSet()))")
    public abstract ResponseCategoryDto toDtoWithProducts(Category category);


    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "products", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Category.class)
    public abstract Category updateCategory(@MappingTarget Category category, RequestCategoryDto dto);


//    public void simple(Product product){
//        ResponseCategoryDto responseCategoryDto=new ResponseCategoryDto();
//        Category category =new Category();
//        responseCategoryDto.setProducts(category.getProducts().stream().map(this.productMapper::toDto).collect(Collectors.toSet()));
//    }

    /*public ResponseCategoryDto toDto(Category entity){
        return ResponseCategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public ResponseCategoryDto toDtoWithProducts(Category entity){
        return ResponseCategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .productDto(this.fromDtoToEntity(entity.getProducts()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    public Category toEntity(ResponseCategoryDto dto){
        return Category.builder()
                .categoryName(dto.getCategoryName())
                .build();
    }

    public Category updateCategory(Category category, ResponseCategoryDto dto){
        if (dto.getCategoryName() != null){
            category.setCategoryName(dto.getCategoryName());
        }
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
    private Set<ResponseProductDto> fromDtoToEntity(Set<Product> products) {
        Set<ResponseProductDto> productDtos =new HashSet<>();
        for (Product product : products) {
            productDtos.add(this.productMapper.toDto(product));
        }
        return productDtos;
    }*/
}
