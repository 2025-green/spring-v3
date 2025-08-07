package com.mtcoding.springv1.controller.dto;

import lombok.Data;

@Data
public class BoardSaveRequestDTO {
    private String title;
    private String content;
}
