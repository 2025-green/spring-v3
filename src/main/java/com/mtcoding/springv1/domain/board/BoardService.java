package com.mtcoding.springv1.domain.board;

import com.mtcoding.springv1.controller.dto.BoardDetailResponseDTO;
import com.mtcoding.springv1.controller.dto.BoardResponseDTO;
import com.mtcoding.springv1.controller.dto.BoardSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// 비지니스로직, 트랜잭션관리, 응답DTO 생성
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponseDTO> 게시글목록() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardResponseDTO> respDTO = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
            boardResponseDTO.setId(board.getId());
            boardResponseDTO.setTitle(board.getTitle());
            respDTO.add(boardResponseDTO);
        }
        return respDTO;
    }


    public BoardDetailResponseDTO 게시글상세(int id) {
        Board board = boardRepository.findById(id);

        BoardDetailResponseDTO respDTO = new BoardDetailResponseDTO();
        respDTO.setId(board.getId());
        respDTO.setTitle(board.getTitle());
        respDTO.setContent(board.getContent());

        return respDTO;
    }

    @Transactional
    public void 게시글쓰기(BoardSaveRequestDTO boardSaveRequestDTO) {
        boardRepository.save(boardSaveRequestDTO.getTitle(), boardSaveRequestDTO.getContent(), 1);
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 게시글수정(int id, BoardSaveRequestDTO boardSaveRequestDTO) {
        boardRepository.updateById(id, boardSaveRequestDTO.getTitle(), boardSaveRequestDTO.getContent());
    }
}
