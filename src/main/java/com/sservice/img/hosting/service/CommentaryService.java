package com.sservice.img.hosting.service;

import com.sservice.img.hosting.dto.ImageCommentaryData;
import com.sservice.img.hosting.dto.ImageData;
import com.sservice.img.hosting.dto.ImageLike;
import com.sservice.img.hosting.dto.UserData;
import com.sservice.img.hosting.repository.ImageCommentariesRepository;
import com.sservice.img.hosting.repository.ImageRepository;
import com.sservice.img.hosting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaryService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ImageCommentariesRepository commentariesRepository;

    public CommentaryService(UserRepository userRepository, ImageRepository imageRepository, ImageCommentariesRepository commentariesRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.commentariesRepository = commentariesRepository;
    }

    public List<ImageCommentaryData> getCommentaries(Long imageId){
        ImageData image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found, id=" + imageId ));

        return commentariesRepository.findByImageId(imageId);
    }

    public void commentImage(Long imageId, String text) {
        UserData user = getUserData();

        long userId = user.getId();

        ImageData image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found, id=" + imageId ));


        commentariesRepository.save(new ImageCommentaryData(user, image, text));
    }
    public void commentImage(Long imageId, Long commentId, String text) {
        UserData user = getUserData();

        long userId = user.getId();

        ImageData image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found, id=" + imageId ));
        var parent = commentariesRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found, id=" + commentId));

        var imageComment = new ImageCommentaryData(user, image, text);
        imageComment.setImageCommentary(parent);
        commentariesRepository.save(imageComment);
    }

    private UserData getUserData() {
        return  (UserData) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
