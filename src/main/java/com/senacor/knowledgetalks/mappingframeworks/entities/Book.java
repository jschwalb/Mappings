package com.senacor.knowledgetalks.mappingframeworks.entities;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class Book {

    @JMap
    private String title;

    private Author author;

    @JMap
    private String publisher;

    private List<Chapter> chapters;

    @JMap
    private Calendar releaseDate;

    @JMap
    private BookCover bookCover;

}
