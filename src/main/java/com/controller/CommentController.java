package com.controller;
import com.payload.CommentDTO;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController()
@RequestMapping("/api")
public class CommentController {
    public CommentService commentService;

    @Autowired
    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable(value="postId") Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComment(@PathVariable(value="postId") Long postId) {
        return new ResponseEntity<>(commentService.getAllComment(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable(value="postId") Long postId, @PathVariable (value="commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
        public ResponseEntity<?> updateComment(@PathVariable Long postId, @PathVariable(value="commentId") Long commentId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);

    }


}

