package com.cz.dto.category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/10.
 */
public class CategoryDto implements Serializable{

    private static final long serialVersionUID = -8428540691908176793L;

    private Long id;
    private String name;
    private List<CategoryDto> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDto> children) {

        this.children = children;
    }

    public CategoryDto(){}

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Long id, String name, List<CategoryDto> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    @Override
    public String toString() {
        return "DtoCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
