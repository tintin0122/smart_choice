package com.smartchoice.crawler.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductModel {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
  @SequenceGenerator(
      sequenceName = "product_id_seq",
      name = "product_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "shop")
  private String shop;

  @Column(name = "information")
  private String information;

  @Column(name = "url")
  private String url;

  @Column(name = "title")
  private String title;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "discount")
  private BigDecimal discount;

  @Column(name = "created_date")
  private OffsetDateTime createdDate;

  @Column(name = "updated_date")
  private OffsetDateTime updatedDate;
}
