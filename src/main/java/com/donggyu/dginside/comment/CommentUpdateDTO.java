package com.donggyu.dginside.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDTO {
    @NotBlank(message = "The contents field is required")
    @Size(max = 255, message = "Up to 255 characters")
    private String contents;
}
