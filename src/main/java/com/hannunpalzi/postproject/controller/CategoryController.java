package com.hannunpalzi.postproject.controller;

import com.hannunpalzi.postproject.dto.CategoryRequestDto;
import com.hannunpalzi.postproject.dto.CategoryListResponseDto;
import com.hannunpalzi.postproject.dto.StatusResponseDto;
import com.hannunpalzi.postproject.entity.UserRoleEnum;
import com.hannunpalzi.postproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryListResponseDto> getCategoryList() {
        return categoryService.getCategoryList();
    }

    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/categories")
    public ResponseEntity<StatusResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        StatusResponseDto responseDto = new StatusResponseDto(HttpStatus.CREATED.value(), "카테고리 등록 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        categoryService.createCategory(requestDto);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.CREATED);
    }

    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<StatusResponseDto> createChildrenCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto requestDto) {
        StatusResponseDto responseDto = new StatusResponseDto(HttpStatus.CREATED.value(), "카테고리 등록 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        categoryService.createChildrenCategory(categoryId, requestDto);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.CREATED);
    }

    @Secured(UserRoleEnum.Authority.ADMIN)
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<StatusResponseDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto requestDto) {
        StatusResponseDto responseDto = new StatusResponseDto(HttpStatus.OK.value(), "카테고리 수정 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        categoryService.updateCategory(categoryId, requestDto);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }

    @Secured(UserRoleEnum.Authority.ADMIN)
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<StatusResponseDto> deleteCategory(@PathVariable Long categoryId) {
        StatusResponseDto responseDto = new StatusResponseDto(HttpStatus.OK.value(), "카테고리 삭제 완료");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
    }
}
