package com.hjc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTreeNode {
    private String id;
    private String label;
    private List<CategoryTreeNode> children;
}
