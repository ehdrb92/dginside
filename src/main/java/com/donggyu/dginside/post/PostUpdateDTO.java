package com.donggyu.dginside.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateDTO {
    private String title;
    private String category;
    private String contents;
    private List<String> attachedFile;
}
