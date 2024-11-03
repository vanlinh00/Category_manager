package com.example.CategoryManagement.services;

import com.example.CategoryManagement.Repositories.CategoryGroupRepository;
import com.example.CategoryManagement.dtos.CategoryGroupDto;
import com.example.CategoryManagement.dtos.CategoryTreeDto;
import com.example.CategoryManagement.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryGroupRepository categoryGroupRepository;

    public List<CategoryGroupDto> getCategoryTree() {
        return categoryGroupRepository.findAll().stream().map(group -> {
            CategoryGroupDto groupDto = CategoryGroupDto.builder()
                    .groupId(group.getId())
                    .groupName(group.getName())
                    .build();

            List<CategoryTreeDto> categoryDtos = group.getCategoryGroupCategories().stream()
                    .map(link -> convertToTreeDto(link.getCategory()))
                    .collect(Collectors.toList());

            groupDto.setCategories(categoryDtos);
            return groupDto;
        }).collect(Collectors.toList());
    }

    private CategoryTreeDto convertToTreeDto(Category category) {
        CategoryTreeDto dto = CategoryTreeDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();

        dto.setSubCategories(
                category.getSubCategories().stream()
                        .map(this::convertToTreeDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}

/*
    {
        "groupId": 1,
        "groupName": "Sản phẩm phổ biến",
        "categories": [
            {
                "categoryId": 1,
                "categoryName": "Điện thoại",
                "subCategories": [
                    { "categoryId": 5, "categoryName": "Nokia", "subCategories": [] },
                    { "categoryId": 6, "categoryName": "Mobiphone", "subCategories": [] },
                    { "categoryId": 7, "categoryName": "Samsung", "subCategories": [] }
                ]
            },
            {
                "categoryId": 2,
                "categoryName": "Laptop",
                "subCategories": []
            }
        ]
    },
    {
        "groupId": 2,
        "groupName": "Khuyến mãi",
        "categories": [
            {
                "categoryId": 1,
                "categoryName": "Điện thoại",
                "subCategories": [
                    { "categoryId": 5, "categoryName": "Nokia", "subCategories": [] },
                    { "categoryId": 6, "categoryName": "Mobiphone", "subCategories": [] },
                    { "categoryId": 7, "categoryName": "Samsung", "subCategories": [] }
                ]
            }
        ]
    }
]

 */