package com.service;

import com.payload.PostDTO;

import java.util.List;

public interface PostService {
PostDTO create(PostDTO postDTO);

    PostDTO getPost(long id);

    List<PostDTO> getAllPost();

    PostDTO updatePost(long id, PostDTO postDTO);

    String deletePost(long id);
}
