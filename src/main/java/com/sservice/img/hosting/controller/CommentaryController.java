package com.sservice.img.hosting.controller;

import com.sservice.img.hosting.dto.ImageCommentaryData;
import com.sservice.img.hosting.service.CommentaryService;
import com.sservice.img.hosting.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Image/Commentary")
public class CommentaryController {
    private final CommentaryService commentaryService;

    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }
    @Operation(summary = "Оставить комментарий")
    @PostMapping("/{idImage}")
    public void imageComment(@Parameter(description = "ID изображения") @PathVariable Long idImage, @RequestParam String text) {
        commentaryService.commentImage(idImage, text);
    }
    @Operation(summary = "Оставить комментарий к комментарию")
    @PostMapping("/{idImage}/{idComment}")
    public void commentComment(@Parameter(description = "ID изображения") @PathVariable Long idImage, @PathVariable Long idComment, @RequestParam String text) {
        commentaryService.commentImage(idImage, idComment,text);
    }
    @Operation(summary = "Получить комменатрии к изображению")
    @GetMapping("/{idImage}")
    public List<ImageCommentaryData> getCommentaries(@Parameter(description = "ID изображения") @PathVariable Long idImage) {
        return commentaryService.getCommentaries(idImage);
    }
}
