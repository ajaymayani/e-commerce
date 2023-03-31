package com.example.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @Column(name = "c_id")
    private String cId;
    @Column(name = "category_name")
   private String categoryName;
    @Column(name = "category_description")
   private String categoryDescription;

}
