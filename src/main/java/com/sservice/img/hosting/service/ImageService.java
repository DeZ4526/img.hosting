package com.sservice.img.hosting.service;

import com.sservice.img.hosting.dto.ImageData;
import com.sservice.img.hosting.dto.ImageLike;
import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.repository.ImageLikeRepository;
import com.sservice.img.hosting.repository.ImageRepository;
import com.sservice.img.hosting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageLikeRepository imageLikeRepository;
    private final UserRepository userRepository;

    private final String uploadDir = "uploads/";
    public ImageService(ImageRepository imageRepository, ImageLikeRepository imageLikeRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.imageLikeRepository = imageLikeRepository;
        this.userRepository = userRepository;
        new File(uploadDir).mkdirs();
    }

    public List<ImageData> getAllImages() {
        return imageRepository.findAll();
    }

    public List<ImageData> getAllMyImages () {
        UserData user = getUserData();

        long userId = user.getId();

        return get(userId);
    }

    public List<ImageData> get (long userId) {
        return imageRepository.findByIdUserUploader(userId);
    }

    public ImageData uploadImage(MultipartFile file, String title, String description, boolean isAdult) {
        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            if (filename != null && !filename.matches(".*\\.(jpg|jpeg|png|gif|webp)$")) {
                throw new EntityNotFoundException("Unsupported file format!");
            }

            Path filepath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(),
                    filepath,
                    StandardCopyOption.REPLACE_EXISTING);

            UserData user = getUserData();

            ImageData imageData = new ImageData();
            imageData.setUrl(filepath.toString());
            imageData.setTitle(title);
            imageData.setDescription(description);
            imageData.setAdult(isAdult);
            imageData.setIdUserUploader(user.getId());

            return imageRepository.save(imageData);

        } catch (IOException e) {
            throw new EntityNotFoundException("Couldn't save the file", e);
        }
    }

    public ResponseEntity<Resource> getImage(long id) throws IOException {
        var imgData = imageRepository.findById(id);

        Path file = Paths.get("").resolve(imgData.get().getUrl());
        if (!Files.exists(file)) {
            throw new EntityNotFoundException("Image not found");
        }

        UrlResource resource = new UrlResource(file.toUri());
        String contentType = Files.probeContentType(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body((Resource) resource);
    }

    public void likeImage(Long imageId) {
        UserData user = getUserData();

        long userId = user.getId();

        ImageData image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found, id=" + imageId ));

        imageLikeRepository.findByUserIdAndImageId(user.getId(), imageId)
                .ifPresent(like -> {
                    throw new IllegalStateException("You already liked this image");
                });

        imageLikeRepository.save(new ImageLike(user, image));
    }

    public void unlikeImage(Long imageId) {
        UserData user = getUserData();

        imageLikeRepository.deleteByUserIdAndImageId(user.getId(), imageId);
    }

    public long getLikeCount(Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            throw new EntityNotFoundException("Image not found, id=" + imageId);
        }

        return imageLikeRepository.countByImageId(imageId);
    }
    private UserData getUserData() {
        return  (UserData) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
