package com.sservice.img.hosting.repository;

import com.sservice.img.hosting.dto.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository  extends JpaRepository<ImageData, Long> {

    List<ImageData> findByIdUserUploader(long idUserUploader);
}