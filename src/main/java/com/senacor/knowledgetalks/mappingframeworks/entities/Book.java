package com.senacor.knowledgetalks.mappingframeworks.entities;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class Book {

    private String title;

    private Author author;

    private String publisher;

    private List<Chapter> chapters;

    private Calendar releaseDate;

    private BookBound bookBound;

}
