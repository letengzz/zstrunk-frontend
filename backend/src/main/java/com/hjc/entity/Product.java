package com.hjc.entity;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer id;
    private String name;
    private String desc;
    private String image;
    private List<String> images;
    private String tag;
    private String category;
    private String subCategory;
    private List<Spec> specs;
    private List<Parameter> parameters;
    private List<String> features;
    private List<String> specsText;
    private String markdownPath;

    @Data
    public static class Spec {
        private String label;
        private String value;
    }

    @Data
    public static class Parameter {
        private String name;
        private String value;
    }
}
