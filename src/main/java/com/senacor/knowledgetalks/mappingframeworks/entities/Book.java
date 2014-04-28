package com.senacor.knowledgetalks.mappingframeworks.entities;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by jschwalb on 25.04.14.
 */
@Data
public class Book {

    private String title;

    private Author author;

    private String publisher;

    private List<Chapter> chapters;

    private Date releaseDate;

}
