package com.mtcoding.springv1.domain;

import com.mtcoding.springv1.domain.board.Board;
import com.mtcoding.springv1.domain.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void deleteById_test() {
        int id = 8;

        boardRepository.deleteById(id);

        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList.size());
    }

    @Test
    public void updateById_test() {
        int id = 1;
        String title = "title update";
        String content = "content update";

        boardRepository.updateById(id, title, content);

        Board board = boardRepository.findById(id);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void save_test() {
        // given
        String title = "제목6";
        String content = "내용6";

        // when
        boardRepository.save(title, content, 1);

        // eye
        Board findBoard = boardRepository.findById(6);
        System.out.println(findBoard.getTitle());
        System.out.println(findBoard.getContent());
    }

    @Test
    public void findAll_test() {
        // given
        // when
        List<Board> boardList = boardRepository.findAll();
        // eye
        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println("-----------------");
        }
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findById(id);

        // eye (눈으로 검증하기)
        if (board == null) {
            System.out.println("조회가 안됐어!!! 그 번호 없나봐");
        } else {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getUser().getId());
            System.out.println(board.getUser().getUsername());
            System.out.println(board.getUser().getPassword());
            System.out.println(board.getUser().getEmail());
        }
    }

    @Test
    public void findByIdV2_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findByIdV2(id);

        // eye (눈으로 검증하기)
        if (board == null) {
            System.out.println("조회가 안됐어!!! 그 번호 없나봐");
        } else {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getUser().getId());
            System.out.println(board.getUser().getUsername());
            System.out.println(board.getUser().getPassword());
            System.out.println(board.getUser().getEmail());
        }
    }
}
