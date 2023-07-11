package org.zerock.j2.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileBoardImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imgno;

  private String uuid;

  private String fname; // 파일 이름

  private int ord; // 대표 이미지를 위한 순번 처리

  public void changeOrd(int ord) {
    this.ord = ord;
  }
  
}
