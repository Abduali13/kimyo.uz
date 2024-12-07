package com.company.kimyo.uz.service.mapper;


import com.company.kimyo.uz.dto.request.RequestOrdersDto;
import com.company.kimyo.uz.dto.response.ResponseOrdersDto;
import com.company.kimyo.uz.entity.Orders;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Orders toEntity(RequestOrdersDto dto);

    public abstract ResponseOrdersDto toDto(Orders orders);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Orders.class)
    public abstract Orders updateOrders(@MappingTarget Orders orders, RequestOrdersDto dto);


}
