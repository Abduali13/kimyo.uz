package com.company.kimyo.uz.service;

import com.company.kimyo.uz.dto.request.RequestOrdersDto;
import com.company.kimyo.uz.dto.response.ResponseOrdersDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.entity.Orders;
import com.company.kimyo.uz.repository.OrdersRepository;
import com.company.kimyo.uz.service.mapper.OrdersMapper;
import com.company.kimyo.uz.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService implements SimpleCrud<Integer, ResponseOrdersDto, RequestOrdersDto> {

    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;

    @Override
    public ResponseDto<ResponseOrdersDto> createEntity(RequestOrdersDto dto) {
        try {
            Orders entity = this.ordersMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseOrdersDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.ordersMapper.toDto(
                                    this.ordersRepository.save(entity)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-2)
                    .message(String.format("Error occurred while saving, message: %s", e.getMessage()))
                    .build();
        }
    }

    @Override
    public ResponseDto<ResponseOrdersDto> getEntity(Integer entityId) {
        Optional<Orders> optional = this.ordersRepository.findOrdersByOrderIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-1)
                    .message(String.format("Order with this %d is not found", entityId))
                    .build();
        }
        return ResponseDto.<ResponseOrdersDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.ordersMapper.toDto(
                                optional.get()
                        )
                )
                .build();
    }

    @Override
    public ResponseDto<ResponseOrdersDto> updateEntity(Integer entityId, RequestOrdersDto dto) {
        Optional<Orders> optional = this.ordersRepository.findOrdersByOrderIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-1)
                    .message(String.format("Order with this %d is not found", entityId))
                    .build();
        }
        return ResponseDto.<ResponseOrdersDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.ordersMapper.toDto(
                                this.ordersRepository.save(
                                        this.ordersMapper.updateOrders(
                                                optional.get(), dto
                                        )
                                )
                        )
                )
                .build();
    }

    @Override
    public ResponseDto<ResponseOrdersDto> deleteEntity(Integer entityId) {
        Optional<Orders> optional = this.ordersRepository.findOrdersByOrderIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-1)
                    .message(String.format("Order with this %d is not found", entityId))
                    .build();
        }
        Orders orders = optional.get();
        orders.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseOrdersDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.ordersMapper.toDto(
                                this.ordersRepository.save(orders)
                        )
                )
                .build();
    }

    public ResponseDto<List<ResponseOrdersDto>> getAllOrders() {
        List<Orders> orders = this.ordersRepository.findAll();
        if (orders.isEmpty()) {
            return ResponseDto.<List<ResponseOrdersDto>>builder()
                    .code(-1)
                    .message("Orders are not found")
                    .build();
        }
        return ResponseDto.<List<ResponseOrdersDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        orders.stream().map(this.ordersMapper::toDto).toList()
                ).build();
    }

    public ResponseDto<Page<ResponseOrdersDto>> getAllOrdersByPage(Integer size, Integer page) {
        return this.getPageResponseDto(this.ordersRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseDto<Page<ResponseOrdersDto>> getAllOrdersSortedByColumn(Integer size, Integer page, String column) {
        return this.getPageResponseDto(this.ordersRepository.findAll(PageRequest.of(page, size, Sort.by(column).ascending())));
    }

    private ResponseDto<Page<ResponseOrdersDto>> getPageResponseDto(Page<Orders> ordersPage) {
        if (ordersPage.isEmpty()) {
            return ResponseDto.<Page<ResponseOrdersDto>>builder()
                    .code(-1)
                    .message("Orders are not found")
                    .build();
        }
        return ResponseDto.<Page<ResponseOrdersDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        ordersPage.map(this.ordersMapper::toDto)
                ).build();
    }

    public ResponseDto<Map<LocalDate, List<ResponseOrdersDto>>> getAllOrdersByCategory() {
        return ResponseDto.<Map<LocalDate, List<ResponseOrdersDto>>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.ordersRepository.findAll()
                                .stream()
                                .map(this.ordersMapper::toDto)
                                .collect(Collectors.groupingBy(ResponseOrdersDto::getOrderDate))
                ).build();
    }

    public ResponseDto<Page<ResponseOrdersDto>> ordersBasicSearch(Map<String, String> params) {
        int size = 10, page =0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        return ResponseDto.<Page<ResponseOrdersDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.ordersRepository.searchAllOrderWithMoreParams(
                                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                                params.get("price") == null ? null : Double.parseDouble(params.get("price")),
                                params.get("date") == null ? null : LocalDate.parse(params.get("date")),
                                PageRequest.of(page, size)
                        ).map(this.ordersMapper::toDto)
                ).build();
    }
}
