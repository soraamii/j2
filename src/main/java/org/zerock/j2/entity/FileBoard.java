package org.zerock.j2.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "images") // 연관 참조 관계가 있으면 exclude 
public class FileBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  private String title;
  private String content;
  private String writer;

  // 여러 개의 이미지를 가진다.
  @BatchSize(size = 20) // 일괄 처리 -> n+1 방지
  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true) // 배열 
  //(1개 조회)fetch = FetchType.EAGER -> join까지 처리해줌 -> 리스트로 조회할 때, n+1 문제가 나타남
  // FetchType의 기본은 LAZY
  // orphanRemoval: 부모 Entity를 제거하면 자식 Entity를 제거
  @JoinColumn(name = "board") // 이미지는 보드에 종속되게 만들어 줌
  @Builder.Default
  private List<FileBoardImage> images = new ArrayList<>(); // 하위 객체까지 같이 save

  // 컬렉션을 절대 새로 만들 수 없다
  public void addImage(FileBoardImage boardImage) {

    // 순번
    boardImage.changeOrd(images.size());

    // 추가
    images.add(boardImage);


  }

  // 수정 전 삭제
  public void cleanImages() {
    
    images.clear(); // 내용들을 다 지움

  }
  
}
