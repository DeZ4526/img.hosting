package com.sservice.img.hosting.repository;

import com.sservice.img.hosting.dto.ImageCommentaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageCommentariesRepository extends JpaRepository<ImageCommentaryData, Long> {

    List<ImageCommentaryData> findByImageId(Long imageId);
    Optional<ImageCommentaryData> findByUserIdAndImageId(Long userId, Long imageId);

    // Посчитать количество коментариев у изображения
    long countByImageId(Long imageId);

    @Query("SELECT COUNT(l) FROM ImageCommentaryData l WHERE l.image.id = :imageId")
    long getCount(@Param("imageId") Long imageId);
}
