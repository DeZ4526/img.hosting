package com.sservice.img.hosting.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor // конструктор со всеми полями
@RequiredArgsConstructor // конструктор только с user и image
@Entity
@Table(
        name = "image_dislikes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "image_id"})
)
public class ImageDislikeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    private UserData user;

    @NonNull
    @ManyToOne
    private ImageData image;

    private LocalDateTime createdAt = LocalDateTime.now();
}