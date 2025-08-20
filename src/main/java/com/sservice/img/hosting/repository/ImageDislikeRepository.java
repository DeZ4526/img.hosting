package com.sservice.img.hosting.repository;

import com.sservice.img.hosting.dto.ImageDislikeData;
import com.sservice.img.hosting.dto.ImageLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDislikeRepository extends JpaRepository<ImageDislikeData, Long> {

    // Проверить, дизлакнул ли пользователь конкретное изображение
    Optional<ImageDislikeData> findByUserIdAndImageId(Long userId, Long imageId);

    // Посчитать количество дизлайков у изображения
    long countByImageId(Long imageId);

    // Удалить дизлайк
    @Transactional
    void deleteByUserIdAndImageId(Long userId, Long imageId);

    @Query("SELECT COUNT(l) FROM ImageDislikeData l WHERE l.image.id = :imageId")
    long getDislikeCount(@Param("imageId") Long imageId);
}