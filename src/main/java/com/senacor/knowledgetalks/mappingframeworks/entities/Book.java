package com.senacor.knowledgetalks.mappingframeworks.entities;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;
import com.googlecode.jmapper.annotations.JMap;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import lombok.Data;
import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;

import java.util.Calendar;
import java.util.List;

@Data
@Mappable
public class Book {

    @JMap
    @Source(property = "title", type = BookDTO.class)
    private String title;

    private Author author;

    @JMap
    @Source(property = "publisher", type = BookDTO.class)
    private String publisher;

    @CollectionProperty(source = "chapterTitles")
    private List<Chapter> chapters;

    @JMap
    @Ignore
    private Calendar releaseDate;

    @JMap
    @Property(source = "mvel:BookCover.valueOf(bookCover.name)")
    @Source(property = "bookCover", type = BookDTO.class)
    private BookCover bookCover;

}
