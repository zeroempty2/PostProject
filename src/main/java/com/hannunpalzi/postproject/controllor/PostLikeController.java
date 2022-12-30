package com.hannunpalzi.postproject.controllor;

import com.hannunpalzi.postproject.dto.PostLikeRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class PostLikeController {
    @PostMapping("/post/{id}/like")
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    public PostLikeRequestDto postLike(PostLikeRequestDto requestDto, HttpServletRequest request) {
    }
}
