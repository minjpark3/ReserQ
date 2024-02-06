package com.sparta.reserq.controller;
import com.sparta.reserq.CustomValidationApiException;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.dto.CommentDto;
import com.sparta.reserq.config.jwt.UserDetailsImpl;
import com.sparta.reserq.service.CommentService;
import com.sparta.reserq.domain.dto.CMRespDto;
import com.sparta.reserq.service.LikesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final LikesService likesService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commnetSave(@RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Comment comment = commentService.createComment(commentDto.getContent(),commentDto.getPostsId(),userDetails.getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기 성공",comment), HttpStatus.CREATED);
    }

    @PostMapping("/{commentId}/likes")
    public ResponseEntity<?>likes(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likesService.commentLike(commentId,userDetails.getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요성공",null),HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}/likes")
    public ResponseEntity<?>unlikes(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        likesService.unCommentLike(commentId,userDetails.getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요취소성공",null),HttpStatus.OK);
    }

    //===========================================================
    //===========================================================

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            commentService.unComment(id, userDetails);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (CustomValidationApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}