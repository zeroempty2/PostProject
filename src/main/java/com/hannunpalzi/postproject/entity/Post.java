package com.hannunpalzi.postproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    private Long postLike;
    public Post(String title, String contents, User user){
        this.title = title;
        this.contents = contents;
        this.writer = user.getUsername();
        this.user = user;
        this.postLike = 0L;
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
    public void plusLike(){
        this.postLike += 1;
    }
    public void minusLike(){
        this.postLike -= 1;
    }
    public boolean checkUsernameIsWriter(String username){
        return this.getWriter().equals(username);
    }

}
