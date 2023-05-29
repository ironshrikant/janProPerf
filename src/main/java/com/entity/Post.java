package com.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String title;
   private String description;
   private String content;
   @OneToMany(mappedBy = "post", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
