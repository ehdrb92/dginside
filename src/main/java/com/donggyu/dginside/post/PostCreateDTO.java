package com.donggyu.dginside.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostCreateDTO {
    @NotBlank(message = "The title field is required")
    @Size(max = 255, message = "Up to 255 characters")
    private String title;

    @NotBlank(message = "The password field is required")
    private String category;

    @NotBlank(message = "The contents field is required")
    private String contents;

    private List<String> attachedFile;
}
