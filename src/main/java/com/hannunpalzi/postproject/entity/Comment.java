package com.hannunpalzi.postproject.entity;

import com.hannunpalzi.postproject.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private String writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;
    private Long commentLike;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReComment> reComments = new ArrayList<>();

    public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
        this.comment = commentRequestDto.getComment();
        this.writer = user.getUsername();
        this.user = user;
        this.post = post;
        this.commentLike = 0L;
    }

    public Comment(String comment, User user){
        this.comment = comment;
        this.user = user;
    }
    public void plusLike(){
        this.commentLike += 1;
    }
    public void minusLike(){
        this.commentLike -= 1;
    }
    public void updateComment(String comment){
        this.comment = comment;
    }

    public boolean checkUserNameIsWriter(String username){
        return this.getWriter().equals(username);
    }

}
