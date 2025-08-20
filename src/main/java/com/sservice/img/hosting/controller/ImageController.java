package com.sservice.img.hosting.controller;

import com.sservice.img.hosting.dto.ImageData;
import com.sservice.img.hosting.dto.ImageUploadRequest;
import com.sservice.img.hosting.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/Image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Получить список всех изображений")
    @GetMapping("/get_all")
    public List<ImageData> getAllImages () {
        return imageService.getAllImages();
    }

    @Operation(summary = "Получить список моих изображений")
    @GetMapping("/my/all")
    public List<ImageData> getAllMyImages () {
        return imageService.getAllMyImages();
    }

    @Operation(summary = "Получить список изображений")
    @GetMapping("/get")
    public List<ImageData> get(@Parameter(description = "ID пользователя")  @RequestParam long userId) {
        return imageService.get(userId);
    }

    @Operation(summary = "Загрузить изображение")
    @PostMapping("/upload")
    public ImageData uploadImage(@ModelAttribute ImageUploadRequest request) {
        return imageService.uploadImage(
                request.getFile(),
                request.getTitle(),
                request.getDescription(),
                request.isAdult()
        );
    }
    @Operation(summary = "Получить изображение по ID", description = "Возвращает изображение в виде файла")
    @GetMapping("/get_image_by_id/{idImage}")
    public ResponseEntity<Resource> getImage(@Parameter(description = "ID изображения") @PathVariable Long idImage) throws IOException {

        return imageService.getImage(idImage);
    }

    @Operation(summary = "Поставить лайк")
    @GetMapping("/like/{idImage}")
    public void like(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        imageService.likeImage(idImage);
    }
    @Operation(summary = "Убрать лайк")
    @DeleteMapping("/like/{idImage}")
    public void unLike(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        imageService.unlikeImage(idImage);
    }

    @Operation(summary = "Кол-во лайк")
    @GetMapping("/likeCount/{idImage}")
    public Long getLikeCount(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        return imageService.getLikeCount(idImage);
    }


    @Operation(summary = "Поставить дизлайк")
    @GetMapping("/dislike/{idImage}")
    public void dislike(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        imageService.dislikeImage(idImage);
    }
    @Operation(summary = "Убрать дизлайк")
    @DeleteMapping("/dislike/{idImage}")
    public void unDislike(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        imageService.unDislikeImage(idImage);
    }

    @Operation(summary = "Кол-во дизлайков")
    @GetMapping("/dislikeCount/{idImage}")
    public Long getDislikeCount(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        return imageService.getDislikeCount(idImage);
    }
}
