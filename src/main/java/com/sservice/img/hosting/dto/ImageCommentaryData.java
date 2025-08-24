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
        name = "image_commentaries",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "image_id", "commentary_id"})
)
public class ImageCommentaryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private ImageData image;

    @ManyToOne
    @JoinColumn(name = "commentary_id")
    private ImageCommentaryData imageCommentary;

    @NonNull
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}