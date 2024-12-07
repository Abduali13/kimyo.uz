package com.company.kimyo.uz.util;

import com.company.kimyo.uz.dto.ResponseDto;

public interface SimpleCrud<K, V, T> {
    ResponseDto<V> createEntity(T entity);
    ResponseDto<V> getEntity(K entityId);
    ResponseDto<V> updateEntity(K entityId, T entity);
    ResponseDto<V> deleteEntity(K entityId);
}
