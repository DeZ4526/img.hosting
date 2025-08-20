package com.sservice.img.hosting.repository;

import com.sservice.img.hosting.dto.ImageLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageLikeRepository extends JpaRepository<ImageLike, Long> {

    // Проверить, лайкнул ли пользователь конкретное изображение
    Optional<ImageLike> findByUserIdAndImageId(Long userId, Long imageId);

    // Посчитать количество лайков у изображения
    long countByImageId(Long imageId);

    // Удалить лайк
    @Transactional
    void deleteByUserIdAndImageId(Long userId, Long imageId);

    @Query("SELECT COUNT(l) FROM ImageLike l WHERE l.image.id = :imageId")
    long getLikeCount(Long imageId);
}