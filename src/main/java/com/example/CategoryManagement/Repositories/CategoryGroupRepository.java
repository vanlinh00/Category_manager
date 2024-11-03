package com.example.CategoryManagement.Repositories;

import com.example.CategoryManagement.models.CategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryGroupRepository extends JpaRepository<CategoryGroup, Long> {
}
