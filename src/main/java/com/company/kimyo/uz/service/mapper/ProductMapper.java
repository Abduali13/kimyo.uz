package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.request.RequestProductDto;
import com.company.kimyo.uz.dto.response.ResponseProductDto;
import com.company.kimyo.uz.entity.Product;
import com.company.kimyo.uz.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class ProductMapper {

    @Lazy
    @Autowired
    protected CategoryService categoryService;


    @Mapping(target = "prodId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "basketId", ignore = true)
    @Mapping(target = "orderItemId", ignore = true)
    public abstract Product toEntity(RequestProductDto dto);

    @Mapping(target = "responseCategoryDto", ignore = true)
    public abstract ResponseProductDto toDto(Product product);

    public abstract ResponseProductDto toDtoWithCategories(Product product);


    @Mapping(target = "prodId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Product updateProduct(@MappingTarget Product product, RequestProductDto dto);

//    public void simple(){
//        Product product =new Product();
//        ResponseProductDto productDto =new ResponseProductDto();
//        productDto.setResponseCategoryDto(product.getCategory().stream());
//    }

    /*public ResponseProductDto toDto(Product product){
        return ResponseProductDto.builder()
                .prodId(product.getProdId())
                .prodName(product.getProdName())
                .description(product.getDescription())
                .stock(product.getStock())
                .categoryId(product.getCategoryId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .deletedAt(product.getDeletedAt())
                .build();
    }


    public ResponseProductDto toDtoWithCategories(Product product){
        return ResponseProductDto.builder()
                .prodId(product.getProdId())
                .prodName(product.getProdName())
                .description(product.getDescription())
                .stock(product.getStock())
                .categoryId(product.getCategoryId())
                .responseCategoryDto(this.categoryService.getEntity(product.getCategoryId()).getData())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .deletedAt(product.getDeletedAt())
                .build();
    }
    public Product toEntity(ResponseProductDto dto){
        return Product.builder()
                .prodName(dto.getProdName())
                .description(dto.getDescription())
                .stock(dto.getStock())
                .categoryId(dto.getCategoryId()).build();
    }
    public Product updateProduct(Product product, ResponseProductDto dto){
        if (dto.getProdName() != null){
            product.setProdName(dto.getProdName());
        }
        if (dto.getDescription() != null){
            product.setDescription(dto.getDescription());
        }
        if (dto.getStock() != null){
            product.setStock(dto.getStock());
        }
        return product;
    }

    public Set<ResponseProductDto> fromEntityToDto(Set<Product> products){
        Set<ResponseProductDto> productDtos = new HashSet<>();
        for (Product product : products) {
            productDtos.add(this.toDto(product));
        }
        return productDtos;
    }*/
}
