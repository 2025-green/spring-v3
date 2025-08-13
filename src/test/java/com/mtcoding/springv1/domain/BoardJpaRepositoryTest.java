package com.mtcoding.springv1.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtcoding.springv1.controller.dto.BoardDetailResponseDTO;
import com.mtcoding.springv1.domain.board.Board;
import com.mtcoding.springv1.domain.board.BoardJpaRepository;
import com.mtcoding.springv1.domain.user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardJpaRepository.class)
@DataJpaTest
public class BoardJpaRepositoryTest {

    @Autowired
    BoardJpaRepository boardJpaRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findByIdJoinUserAndReplies_test() throws JsonProcessingException {
        int id = 5;

        Board board = boardJpaRepository.findByIdJoinUserAndReplies(id);

        BoardDetailResponseDTO dto = new BoardDetailResponseDTO(board);
        //System.out.println(dto);

        ObjectMapper mapper = new ObjectMapper();
        String dtoJson = mapper.writeValueAsString(dto);
        System.out.println(dtoJson);
    }


    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardJpaRepository.findById(id);

        // lazy 전략
        System.out.println("lazy start");
        System.out.println(board.getUser().getUsername());
    }

    @Test
    public void save_test() {
        User user = new User(1, "ssar", "1234", "ssar@nate.com");
        Board board = new Board(null, "title", "content", user);
        boardJpaRepository.save(board);
        System.out.println(board.getId());
    }

    @Test
    public void update_test() {
        Board findBoard = boardJpaRepository.findById(1);

        // findBoard 상태변경하기
        findBoard.update("title change", "content change");


        em.flush();

//        Board updateBoard = boardJpaRepository.update(findBoard);
//
//        System.out.println(updateBoard.getId());
//        System.out.println(updateBoard.getTitle());
//        System.out.println(updateBoard.getContent());
    }

    @Test
    public void deleteById_test() {
        int id = 6;

        boardJpaRepository.deleteById(id);
    }

    @Test
    public void findAllV2_test() {
        boardJpaRepository.findAllV2();
    }


    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardJpaRepository.findAll();

        // lazy loading
//        System.out.println("lazy loading start");
//        int userId = boardList.get(0).getUser().getId();
//        System.out.println("userId: " + userId);
    }
}










