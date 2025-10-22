package org.example.miniproject.board.Repository;

import org.example.miniproject.board.domain.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BoardRepository extends CrudRepository<Board,Long>,BoardRepositoryCustom {


    List<Board> findAllByOrderByIdDesc();

}
