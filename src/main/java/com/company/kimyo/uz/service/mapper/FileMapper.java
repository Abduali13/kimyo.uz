package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.FileDto;
import com.company.kimyo.uz.entity.FileModel;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public FileDto toDto(FileModel file) {
        return FileDto.builder()
                .ext(file.getExt())
                .path(file.getPath())
                .fileId(file.getFileId())
                .fileName(file.getFileName())
                .contentType(file.getContentType())
                .createdAt(file.getCreatedAt())
                .deletedAt(file.getDeletedAt())
                .build();
    }
    public FileModel toEntity(FileDto dto ){
        return null;
    }
}
