package com.service.impl;
import com.entity.Comment;
import com.entity.Post;
import com.exception.BlogApiException;
import com.exception.ResourceNotFoundException;
import com.payload.CommentDTO;
import com.repository.CommentRepository;
import com.repository.PostRepository;
import com.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper mapper;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper mapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.postRepository = postRepository;
    }

    public CommentDTO createComment(CommentDTO commentDTO, Long postId) {
        Post post = postRepository.findById(postId).get();
        Comment comment = mapToEntity(commentDTO);
        comment.setPost(post);
        return mapToDTO(commentRepository.save(comment));

    }

    @Override
    public List<CommentDTO> getAllComment(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDTOList.add(mapToDTO(comment));
        }
        return commentDTOList;
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if (!postId.equals(comment.getPost().getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments doesn't belongs to post");
        }
        return (mapToDTO(comment));

    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        if (!postId.equals(comment.getPost().getId())) {
             throw new  BlogApiException(HttpStatus.BAD_REQUEST, "Comment Doesn't belong to post");
           }
        comment.setBody(commentDTO.getBody());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        Comment comment1 = commentRepository.save(comment);
        return mapToDTO(comment1);
    }

    private Comment mapToEntity(CommentDTO commentDTO){
    return mapper.map(commentDTO, Comment.class);
}

    private CommentDTO mapToDTO(Comment comment) {
    return mapper.map(comment, CommentDTO.class);
}

}
