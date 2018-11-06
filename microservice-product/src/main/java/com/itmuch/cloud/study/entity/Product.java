package com.itmuch.cloud.study.entity;

import javax.persistence.*;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String productName;
  @Column
  private String productType;
  @Column
  private String productStatus;
  @Column
  private String productImg;
  @Column
  private Long productSort;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(String productStatus) {
    this.productStatus = productStatus;
  }

  public String getProductImg() {
    return productImg;
  }

  public void setProductImg(String productImg) {
    this.productImg = productImg;
  }

  public Long getProductSort() {
    return productSort;
  }

  public void setProductSort(Long productSort) {
    this.productSort = productSort;
  }
}
