//package com.example.CategoryManagement.controllers;
//
//import com.example.CategoryManagement.Repositories.CategoryGroupRepository;
//import com.example.CategoryManagement.models.CategoryGroup;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/category-groups")
//public class CategoryGroupController {
//
//    @Autowired
//    private CategoryGroupRepository categoryGroupRepository;
//
//
//    @PostMapping
//    public CategoryGroup createCategoryGroup(@RequestBody CategoryGroup categoryGroup) {
//        return categoryGroupRepository.save(categoryGroup);
//    }
//}
