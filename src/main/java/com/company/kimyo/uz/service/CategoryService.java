package com.company.kimyo.uz.service;

import com.company.kimyo.uz.dto.request.RequestCategoryDto;
import com.company.kimyo.uz.dto.response.ResponseCategoryDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.entity.Category;
import com.company.kimyo.uz.repository.CategoryRepository;
import com.company.kimyo.uz.service.mapper.CategoryMapper;
import com.company.kimyo.uz.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
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
public class CategoryService implements SimpleCrud<Integer, ResponseCategoryDto, RequestCategoryDto> {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseDto<ResponseCategoryDto> createEntity(RequestCategoryDto dto) {
        try {
            Category entity = this.categoryMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseCategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.categoryMapper.toDto(
                                    this.categoryRepository.save(entity)))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-2)
                    .message(String.format("Error occurred while creating category, message: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<ResponseCategoryDto> getEntity(Integer entityId) {
        Optional<Category> optional = this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with this %d id is not found", entityId)).build();
        }
        return ResponseDto.<ResponseCategoryDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.categoryMapper.toDtoWithProducts(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<ResponseCategoryDto> updateEntity(Integer entityId, RequestCategoryDto dto) {
        Optional<Category> optional = this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with this %d id is not found", entityId)).build();
        }
        return ResponseDto.<ResponseCategoryDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.categoryMapper.toDto(
                                this.categoryRepository.save(
                                        this.categoryMapper.updateCategory(optional.get(), dto)
                                )
                        )
                )
                .build();
    }

    @Override
    public ResponseDto<ResponseCategoryDto> deleteEntity(Integer entityId) {
        Optional<Category> optional = this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with this %d id is not found", entityId)).build();
        }
        Category category = optional.get();
        category.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseCategoryDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.categoryMapper.toDto(
                                this.categoryRepository.save(category)))
                .build();
    }

    public ResponseDto<List<ResponseCategoryDto>> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();
        if (categories.isEmpty()) {
            return ResponseDto.<List<ResponseCategoryDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ResponseDto.<List<ResponseCategoryDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        categories.stream().map(this.categoryMapper::toDto).toList()
                ).build();
    }

    public ResponseDto<Page<ResponseCategoryDto>> getAllCategoryByPage(Integer size, Integer page) {
        return this.getPageResponseDto(this.categoryRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseDto<Page<ResponseCategoryDto>> getAllCategorySortedByColumn(Integer size, Integer page, String column) {
        return this.getPageResponseDto(this.categoryRepository.findAll(PageRequest.of(page, size, Sort.by(column).ascending())));
    }

    private ResponseDto<Page<ResponseCategoryDto>> getPageResponseDto(Page<Category> categoryPage) {
        if (categoryPage.isEmpty()) {
            return ResponseDto.<Page<ResponseCategoryDto>>builder()
                    .code(-1)
                    .message("Categories are not found")
                    .build();
        }
        return ResponseDto.<Page<ResponseCategoryDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        categoryPage.map(this.categoryMapper::toDto)
                ).build();
    }


    public ResponseDto<Map<String, List<ResponseCategoryDto>>> getAllCategoryByCategory() {
        return ResponseDto.<Map<String, List<ResponseCategoryDto>>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.categoryRepository.findAll()
                                .stream()
                                .map(this.categoryMapper::toDto).collect(Collectors.groupingBy(ResponseCategoryDto::getCategoryName))
                ).build();
    }

    public ResponseDto<Page<ResponseCategoryDto>> categoryBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        return ResponseDto.<Page<ResponseCategoryDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.categoryRepository.searchAllCategoryWithMoreParams(
                                params.get("id") == null ? null : Integer.parseInt(params.get("id")), params.get("name"), PageRequest.of(page, size)
                        ).map(this.categoryMapper::toDto)
                ).build();
    }
}
