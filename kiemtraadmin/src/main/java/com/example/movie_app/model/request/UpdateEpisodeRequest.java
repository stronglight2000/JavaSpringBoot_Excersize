package com.example.movie_app.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEpisodeRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Integer displayOrder;

    @NotNull
    private Boolean status;

}
