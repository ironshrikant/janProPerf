package com.service.impl;
import com.entity.Post;
import com.exception.ResourceNotFoundException;
import com.payload.PostDTO;
import com.repository.PostRepository;
import com.service.PostService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.apache.el.lang.ELArithmetic.add;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDTO create(PostDTO postDTO) {
        Post post = postRepository.save(mapToEntity(postDTO));
        return mapToDTO(post);
    }

    @Override
    public PostDTO getPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new
                ResourceNotFoundException("Post", "id",id));
        return  mapToDTO(post);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList){
            PostDTO postDTO =mapToDTO(post);
              postDTOList.add(postDTO);
         }
        return postDTOList;
    }


    @Override
    public PostDTO updatePost(long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        return mapToDTO(postRepository.save(post));
    }

    @Override
    public String deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
         postRepository.delete(post);
        return "Post Deleted";
    }

    private Post mapToEntity(PostDTO postDTO){
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setDescription(postDTO.getDescription());
        return post;
    }

    private PostDTO mapToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        return postDTO;
    }
}
