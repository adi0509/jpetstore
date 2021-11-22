package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

  // productid varchar(10) not null,
  // category varchar(10) not null,
  // name varchar(80) null,
  // descn varchar(255) null,
  // constraint pk_product primary key (productid),
  //     constraint fk_product_1 foreign key (category)
  //     references category (catid)

    // empty constructor
    public Product(){}

    public Product(String productId, String category, String name, String descn){
      this.productId = productId;
      this.category = category;
      this.name = name;
      this.descn = descn;
    }
    
    @Id
    @Column(name = "productid")
    private String productId;
    
    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;
    
    @Column(name = "descn")
    private String descn;
    
    public String getProductId() {
      return this.productId;
    }

    public void setProductId(String productId) {
      this.productId = productId;
    }

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
      this.category = category;
    }

    public String getName() {
      return this.name;
    }
    
    public void setName(String name) {
      this.name = name;
    }

    public String getDescn() {
      return this.descn;
    }
    
    public void setDescn(String descn) {
      this.descn = descn;
    }
}
