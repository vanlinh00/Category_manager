package com.example.CategoryManagement.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory; // Danh mục cha

    @OneToMany(mappedBy = "parentCategory")
    private Set<Category> subCategories = new HashSet<>(); // Danh mục con

    @OneToMany(mappedBy = "category")
    private Set<CategoryGroupCategory> categoryGroupCategories = new HashSet<>();

}
