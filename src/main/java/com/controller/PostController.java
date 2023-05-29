package com.controller;
import com.payload.PostDTO;
import com.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO postDTO){
        return new ResponseEntity<>( postService.create(postDTO),HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable(name= "id") long id){
        return new ResponseEntity<>(postService.getPost(id),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity <List<PostDTO>> getAllPost(){
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable(name="id") long id, @RequestBody PostDTO postDTO ){
    return new ResponseEntity<>(postService.updatePost(id, postDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id")long id){
        return  new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }
}
