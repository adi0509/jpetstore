package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory
{
  // itemid varchar(10) not null,
  // qty int not null,
  // constraint pk_inventory primary key (itemid)

    //empty constructor
    public Inventory(){}

    public Inventory(String itemId, int quantity){
      this.itemId = itemId;
      this.quantity = quantity;
    }

    @Id
    @Column(name = "itemid")
    private String itemId;    

    @Column(name = "qty")
    private int quantity;

    public String getItemId() {
      return itemId;
    }
  
    public void setItemId(String itemId) {
      this.itemId = itemId;
    }
  
    public int getQuantity() {
      return this.quantity;
    }
  
    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
}