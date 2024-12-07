package com.company.kimyo.uz.util;

import com.company.kimyo.uz.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface SimpleRequestCrud<K, V, T>{
    ResponseEntity<ResponseDto<V>> createEntity(T entity);
    ResponseEntity<ResponseDto<V>> getEntity(K entityId);
    ResponseEntity<ResponseDto<V>> updateEntity(K entityId, T entity);
    ResponseEntity<ResponseDto<V>> deleteEntity(K entityId);
}
