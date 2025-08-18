package com.sservice.img.hosting.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // для BIGSERIAL
    private long id;

    @Column(nullable = false)
    private String url;

    @Column(name = "id_user_uploader", nullable = false)
    private long idUserUploader;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "is_adult")
    private boolean isAdult;
}
