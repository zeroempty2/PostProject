package com.hannunpalzi.postproject.entity;

import com.hannunpalzi.postproject.dto.CategoryRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int layer = 0;

    @Column
    private String parent = null;

    public Category(CategoryRequestDto requestDto) {
        this.name = requestDto.getName();
    }

    public Category(CategoryRequestDto requestDto, int layer, String parent) {
        this.name = requestDto.getName();
        this.layer = layer;
        this.parent = parent;
    }

    public void updateName(CategoryRequestDto requestDto) {
        this.name = requestDto.getName();
    }
}
