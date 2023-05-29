package com.service;

import com.payload.CommentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
public CommentDTO createComment(CommentDTO commentDTO, Long postId);

    public List<CommentDTO> getAllComment(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postID, Long commentId, CommentDTO commentDTO);
}
