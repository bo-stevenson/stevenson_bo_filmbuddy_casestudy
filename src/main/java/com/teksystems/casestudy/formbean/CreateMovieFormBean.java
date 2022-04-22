package com.teksystems.casestudy.formbean;

import com.teksystems.casestudy.validation.TitleUnique;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateMovieFormBean {

    private Integer id;

    @TitleUnique(message = "Movie already exists in database")
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Genre is required")
    private String genre;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;


}
