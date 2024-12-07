package com.company.kimyo.uz.service;

import com.company.kimyo.uz.dto.request.RequestProductDto;
import com.company.kimyo.uz.dto.response.ResponseProductDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.entity.Product;
import com.company.kimyo.uz.repository.ProductRepository;
import com.company.kimyo.uz.repository.impl.ProductRepositoryImpl;
import com.company.kimyo.uz.service.mapper.ProductMapper;
import com.company.kimyo.uz.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements SimpleCrud<Integer, ResponseProductDto, RequestProductDto> {

    @Lazy
    private final ProductMapper productMapper;
    private final ProductRepositoryImpl productRepositoryImpl;
    private final ProductRepository productRepository;

    @Override
    public ResponseDto<ResponseProductDto> createEntity(RequestProductDto dto) {
        try {
            return ResponseDto.<ResponseProductDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.productMapper.toDto(
                                    this.productRepository.save(
                                            this.productMapper.toEntity(dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-2)
                    .message(String.format("Error occurred while saving product, message: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<ResponseProductDto> getEntity(Integer entityId) {
        Optional<Product> optional = this.productRepository.findByProductId(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-1)
                    .message(String.format("Product with this %d id is not found", entityId)).build();
        }
        return ResponseDto.<ResponseProductDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.productMapper.toDtoWithCategories(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<ResponseProductDto> updateEntity(Integer entityId, RequestProductDto dto) {
        Optional<Product> optional = this.productRepository.findByProdIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-1)
                    .message(String.format("Product with this %d id is not found", entityId)).build();
        }
        return ResponseDto.<ResponseProductDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.productMapper.toDto(
                                this.productRepository.save(
                                        this.productMapper.updateProduct(optional.get(), dto)
                                )
                        )
                )
                .build();
    }

    @Override
    public ResponseDto<ResponseProductDto> deleteEntity(Integer entityId) {
        Optional<Product> optional = this.productRepository.findByProdIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-1)
                    .message(String.format("Product with this %d id is not found", entityId))
                    .build();
        }
        Product product = optional.get();
        product.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseProductDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.productMapper.toDto(
                                this.productRepository.save(product)))
                .build();
    }

    public ResponseDto<List<ResponseProductDto>> getAllProduct() {
        List<Product> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseDto.<List<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found")
                    .build();
        }
        return ResponseDto.<List<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        products.stream()
                                .map(this.productMapper::toDto)
                                .toList()
                )
                .build();
    }

    public ResponseDto<Page<ResponseProductDto>> getAllProductByPage(Integer size, Integer page) {
        return getPageResponseDto(this.productRepository.findAll(PageRequest.of(page, size)));
//        return Optional.of(this.productRepository.findAll(PageRequest.of(page, size)))
//                .map(products -> ResponseDto.<Page<ResponseProductDto>>builder()
//                        .success(true)
//                        .message("OK")
//                        .data(products.map(this.productMapper::toDto))
//                        .build())
//                .orElse(ResponseDto.<Page<ResponseProductDto>>builder()
//                        .code(-1)
//                        .message("Products are not found")
//                        .build());

    }

    public ResponseDto<Page<ResponseProductDto>> getAllProductSortedByColumn(Integer size, Integer page, String column) {

//        Map<String, List<Product>> collect = productPage.stream().collect(Collectors.groupingBy(Product::getProdColor));
        return getPageResponseDto(this.productRepository.findAll(PageRequest.of(page, size, Sort.by(column).ascending())));
    }

    private ResponseDto<Page<ResponseProductDto>> getPageResponseDto(Page<Product> productPage) {
        if (productPage.isEmpty()) {
            return ResponseDto.<Page<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found")
                    .build();
        }
        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        productPage.map(this.productMapper::toDto)
                ).build();
    }

    public ResponseDto<Map<String, List<ResponseProductDto>>> getAllProdByCategory() {
        return ResponseDto.<Map<String, List<ResponseProductDto>>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.productRepository.findAll()
                                .stream()
                                .map(this.productMapper::toDto)
                                .collect(Collectors.groupingBy(ResponseProductDto::getProdColor)))
                .build();


    }

    public ResponseDto<Page<ResponseProductDto>> productBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.productRepository.searchAllProductWithMoreParams(
                                        params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                                        params.get("name"), params.get("color"), params.get("type"),
                                        params.get("price") == null ? null : Double.parseDouble(params.get("price")),
                                        params.get("amount") == null ? null : Integer.parseInt(params.get("amount")),
                                        PageRequest.of(page, size)
                        ).map(this.productMapper::toDto)
                )
                .build();

    }

    public ResponseDto<Page<ResponseProductDto>> productAdvancedSearch(Map<String, String> params) {
        Page<Product> productPage = this.productRepositoryImpl.searchAllProductWithMoreParams(params);
        if (productPage.isEmpty()){
            return ResponseDto.<Page<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found").build();
        }
        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .data(productPage.map(this.productMapper::toDto))
                .build();
    }





    /*public ResponseDto<Set<ResponseProductDto>> getAllByDeleteIsNull() {
        Set<Product> cardList = this.productRepository.findAllByDeletedAtIsNull();
        if (cardList.isEmpty()){
            return ResponseDto.<Set<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found")
                    .build();
        }
        Set<ResponseProductDto> cardDtoList = this.productMapper.fromEntityToDto(cardList);
        return ResponseDto.<Set<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .data(cardDtoList).build();

    }*/
}
