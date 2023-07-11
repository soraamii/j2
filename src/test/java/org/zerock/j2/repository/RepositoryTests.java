package org.zerock.j2.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.entity.FileBoardImage;

import jakarta.transaction.Transactional;

@SpringBootTest
public class RepositoryTests {

  @Autowired
  FileBoardRepository repository;

  // 파일 삽입
  @Test
  public void insert() {

      for(int i = 0; i < 100; i++){

      FileBoard fileBoard = FileBoard.builder()
      .title("AA")
      .content("Content")
      .writer("AAA")
      .build();

      FileBoardImage img1 = FileBoardImage.builder()
      .uuid(UUID.randomUUID().toString())
      .fname("AAA.jpg")
      .build();

      FileBoardImage img2 = FileBoardImage.builder()
      .uuid(UUID.randomUUID().toString())
      .fname("BBB.jpg")
      .build();

      fileBoard.addImage(img1);
      fileBoard.addImage(img2);

      repository.save(fileBoard);

    }
  }

  // 삭제
  @Test
  @Commit
  @Transactional
  public void testRemove() {

    Long bno = 2L;

    repository.deleteById(bno);

  }

  // 1건 조회
  @Test
  @Transactional
  public void testRead() {

    Long bno = 100L;

    Optional<FileBoard> result = repository.findById(bno);

    FileBoard board = result.orElseThrow();

    System.out.println(board);

  }

  // 목록 조회
  @Test
  @Transactional
  public void tsetList() {

    Pageable pageable = PageRequest.of(0, 10);

    Page<FileBoard> result = repository.findAll(pageable);

    // System.out.println(result);

    result.get().forEach(board -> {
      System.out.println(board);
      System.out.println(board.getImages()); 
    });

  }

  @Test
  @Transactional
  public void testListQuerydsl() {

    PageRequestDTO requestDTO = new PageRequestDTO();

    System.out.println(repository.list(requestDTO));
  }

  @Test
  public void testSelectOne() {

    Long bno = 100L;

    FileBoard board = repository.selectOne(bno);

    System.out.println(board);
    System.out.println(board.getImages());

  }

  @Transactional
  @Commit
  @Test
  public void testDelete() {

    Long bno = 98L;

    repository.deleteById(bno);

  }

  @Transactional
  @Commit
  @Test
  public void testUpdate() {

    Optional<FileBoard> result = repository.findById(3L);

    FileBoard board = result.orElseThrow();

    board.cleanImages();

    FileBoardImage img1 = FileBoardImage.builder()
      .uuid(UUID.randomUUID().toString())
      .fname("ZZZ.jpg")
      .build();

    board.addImage(img1);

    repository.save(board);
  


  }


}
