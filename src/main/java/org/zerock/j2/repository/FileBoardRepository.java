package org.zerock.j2.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.repository.search.FileBoardSearch;

public interface FileBoardRepository extends JpaRepository<FileBoard, Long>, FileBoardSearch {
  
  @EntityGraph(attributePaths = {"images"}) // 한 번데 가져오고 싶음
  @Query("select b from FileBoard b where b.bno = :bno")
  FileBoard selectOne(@Param("bno")Long bno);

}
