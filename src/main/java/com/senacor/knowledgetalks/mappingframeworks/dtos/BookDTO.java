package com.senacor.knowledgetalks.mappingframeworks.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by jschwalb on 25.04.14.
 */
@Data
public class BookDTO {

    private String title;

    private String authorFirstName;

    private String authorLastName;

    private Date authorBirthday;

    private Date releaseDate;

    private String publisher;

    private BookTypeDTO bookType;

    private List<String> chapterTitles;

    private BookBoundEnum bookBound;

}
