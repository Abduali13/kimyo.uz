package com.company.kimyo.uz.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private Integer fileId;
    private String fileName;
    private String ext; //.jpg, .png, .pdf
    private String path;

    private String contentType;
    private byte[] content;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
