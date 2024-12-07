package com.company.kimyo.uz.repository;

import com.company.kimyo.uz.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Integer> {
    Optional<FileModel> findByFileIdAndDeletedAtIsNull(Integer fileId);

    Optional<FileModel> findByFileNameAndDeletedAtIsNull(String fileName);
}
