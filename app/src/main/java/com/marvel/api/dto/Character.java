package com.marvel.api.dto;

import static com.marvel.api.util.MarvelConstants.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character implements Serializable {
    private Integer id;

    @NotBlank(message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = ALTEREGO_REQUIRED)
    private String alterego;

    @NotBlank(message = DESCRIPTION_REQUIRED)
    private String description;

    @NotEmpty(message = POWERS_REQUIRED)
    private List<String> powers;
}
