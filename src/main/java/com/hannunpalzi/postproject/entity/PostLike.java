package com.hannunpalzi.postproject.entity;

import com.hannunpalzi.postproject.dto.PostLikeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor
public class PostLike {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;
    @Column
    private String username;
    public PostLike(Post post, String username) {
        this.post = post;
        this.username = username;
    }
}
