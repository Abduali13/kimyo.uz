package com.company.kimyo.uz.service;

import com.company.kimyo.uz.dto.FileDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.entity.FileModel;
import com.company.kimyo.uz.repository.FileRepository;
import com.company.kimyo.uz.service.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

     private final FileRepository fileRepository;
     private final FileMapper fileMapper;
    public ResponseDto<FileDto> uploadFile(MultipartFile file) {
        try {

            if(!checkFileContent(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])){
                return ResponseDto.<FileDto>builder()
                        .code(1)
                        .message(String.format("File with %s already exists", file.getOriginalFilename()))
                        .build();
            }

            return ResponseDto.<FileDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.fileMapper.toDto(
                                this.fileRepository.save(
                                        FileModel.builder()
                                                .fileName(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[0])
                                                .ext(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1])
                                                .path(saveFile(file))
                                                .contentType(file.getContentType())
                                                .createdAt(LocalDateTime.now())
                                                .build()
                                )
                            )
                    )
                    .build();

        }
        catch (Exception e){
            return ResponseDto.<FileDto>builder()
                    .code(-2)
                    .message(String.format("Error occurred while saving file; message: %s", e.getMessage()))
                    .build();
        }

    }

    public ResponseDto<FileDto> downloadFile(Integer fileId) {
        Optional<FileModel> optional = this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId);
        if (optional.isEmpty()) {
            return ResponseDto.<FileDto>builder()
                    .code(-1)
                    .message(String.format("File with this %d id is not found", fileId))
                    .build();
        }
        FileModel fileModel = optional.get();
        try {
            FileDto dto = this.fileMapper.toDto(fileModel);
            dto.setContent(Files.readAllBytes(Path.of(fileModel.getPath())));
            return ResponseDto.<FileDto>builder()
                    .success(true)
                    .message("OK")
                    .data(dto)
                    .build();
        }
        catch (Exception e){
            return ResponseDto.<FileDto>builder()
                    .code(-4)
                    .message(String.format("File while reading error, %s", e.getMessage()))
                    .build();
        }


    }

    public ResponseDto<FileDto> deleteFile(Integer fileId) {
        try {
            Optional<FileModel> optional = this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId);
            if (optional.isEmpty()) {
                return ResponseDto.<FileDto>builder()
                        .code(-1)
                        .message(String.format("File with this %d id is not found", fileId))
                        .build();
            }

            FileModel fileModel = optional.get();
            File file = new File(fileModel.getPath());
            if (file.exists()){
                file.delete();
            }
            fileModel.setDeletedAt(LocalDateTime.now());
            return ResponseDto.<FileDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.fileMapper.toDto(
                                    this.fileRepository.save(fileModel)
                            )
                    )
                    .build();
        }
        catch (Exception e){
            return ResponseDto.<FileDto>builder()
                    .code(-4)
                    .message(String.format("File while deleting error, %s", e.getMessage()))
                    .build();
        }


    }


    private boolean checkFileContent(String originalFilename) {
        return this.fileRepository.findByFileNameAndDeletedAtIsNull(originalFilename).isEmpty();
    }


    private String saveFile(MultipartFile file) throws IOException {
        String folder = String.format("%s/%s", "upload/", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        File newFile = new File(folder);
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        String fileName = String.format("%s/%s", folder, file.getOriginalFilename());
        Files.copy(file.getInputStream(), Path.of(fileName));
        return fileName;
    }


}
