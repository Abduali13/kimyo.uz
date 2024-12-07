package com.company.kimyo.uz.util;

import com.company.kimyo.uz.entity.Card;
import com.company.kimyo.uz.entity.Category;
import com.company.kimyo.uz.entity.Product;
import com.company.kimyo.uz.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BuildTime {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    private void executeCreatedAt(Object o){
        if (o instanceof User){
            ((User) o).setCreatedAt(LocalDateTime.now());
        } else if (o instanceof Card) {
            ((Card) o).setCreatedAt(LocalDateTime.now());
        } else if (o instanceof Category) {
            ((Category) o).setCreatedAt(LocalDateTime.now());
        } else if (o instanceof Product) {
            ((Product) o).setCreatedAt(LocalDateTime.now());
        }
    }
    @PreUpdate
    private void executeUpdatedAt(Object o){
        if (o instanceof User){
            ((User) o).setUpdatedAt(LocalDateTime.now());
        } else if (o instanceof Card) {
            ((Card) o).setUpdatedAt(LocalDateTime.now());
        } else if (o instanceof Category) {
            ((Category) o).setUpdatedAt(LocalDateTime.now());
        } else if (o instanceof Product) {
            ((Product) o).setUpdatedAt(LocalDateTime.now());
        }
    }

}
