package com.example.CategoryManagement.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryRequest {
    private String categoryName;
    private List<SubCategoryRequest> subCategories;
}