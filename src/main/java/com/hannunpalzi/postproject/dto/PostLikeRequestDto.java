package com.hannunpalzi.postproject.dto;

import com.hannunpalzi.postproject.entity.Post;
import lombok.Getter;

@Getter
public class PostLikeRequestDto {
   private boolean like = false;
}
