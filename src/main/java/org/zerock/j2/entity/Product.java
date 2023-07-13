package org.zerock.j2.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "images")
@Builder
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pno;
  
  private String pname;

  private String pdesc; // 상품 설명
  
  private String writer;

  private int price;
  
  // 삭제 플래그
  private boolean delFlag;

  @ElementCollection(fetch = FetchType.LAZY)
  @Builder.Default
  private List<ProductImage> images = new ArrayList<>();

  public void addImage(String name) {
  
    ProductImage pImage = ProductImage.builder().fname(name).ord(images.size()).build();

    images.add(pImage);

  }

  public void clearImages() {

    images.clear();
    
  }

  public void changePrice(int price) {

    this.price = price;
    
  }

  public void changePname(String pname) {

    this.pname = pname;

  }

  public void changePdesc(String pdesc) {

    this.pdesc = pdesc;

  }

  public void changeDel(boolean delFlag) {

    this.delFlag = delFlag;

  }
}
