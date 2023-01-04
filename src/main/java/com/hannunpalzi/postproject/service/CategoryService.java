package com.hannunpalzi.postproject.service;

import com.hannunpalzi.postproject.dto.CategoryRequestDto;
import com.hannunpalzi.postproject.dto.CategoryListResponseDto;
import com.hannunpalzi.postproject.dto.CategoryResponseDto;
import com.hannunpalzi.postproject.entity.Category;
import com.hannunpalzi.postproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponseDto> getCategory() {
        List<Category> categoryList = categoryRepository.getAllByOrderByNameAsc();
        List<Category> parentCategoryList = categoryRepository.getCategoriesByLayer(0);
        List<CategoryListResponseDto> responseDtoList = parentCategoryList.stream().map(category -> new CategoryListResponseDto(category, categoryList)).collect(Collectors.toList());
        return responseDtoList;
    }

    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        Category category = new Category(requestDto);
        categoryRepository.save(category);
        return new CategoryResponseDto(category);
    }

    public CategoryResponseDto createChildrenCategory(Long categoryId, CategoryRequestDto requestDto) {
        Category parentCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
        int layer = parentCategory.getLayer() + 1;
        String parent = parentCategory.getName();
        Category category = new Category(requestDto, layer, parent);
        categoryRepository.save(category);
        return new CategoryResponseDto(category);
    }

    public void updateCategory(Long categoryId, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findByCategoryId(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
        );
    }

    public void deleteCategory(Long categoryId, CategoryRequestDto requestDto) {

    }
}
