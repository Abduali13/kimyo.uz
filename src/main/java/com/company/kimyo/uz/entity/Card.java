package com.company.kimyo.uz.entity;


import com.company.kimyo.uz.util.BuildTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
@EntityListeners(value= BuildTime.class)
@EqualsAndHashCode
public class Card {
     @Id
     @GeneratedValue(strategy =GenerationType.IDENTITY)
     private Integer cardId;

//     @Column(name = "cardName")

     @Column(nullable = false)
     private String cardName;

     @Column(nullable = false)
     private String cardFullName;

     @Column(name = "user_id")
     private Integer userId;

     @Column(nullable = false, unique = true)
     private String cardCode;

     @ManyToOne
     @JoinColumn(name = "user_id", insertable = false, updatable = false)
     private User users;

     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     private LocalDateTime deletedAt;

//     @Embedded
//     private BuildTime buildTime;
}
