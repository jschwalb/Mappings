package com.senacor.knowledgetalks.mappingframeworks.dtos;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import com.senacor.knowledgetalks.mappingframeworks.entities.Author;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.entities.BookCover;
import com.senacor.knowledgetalks.mappingframeworks.entities.Chapter;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class BookDTO {

    @JMap(classes = {Book.class})
    private String title;

    @JMap(value = "firstName", classes = {Author.class})
    private String authorFirstName;

    @JMap(value = "lastName", classes = {Author.class})
    private String authorLastName;

    @JMap(value = "birthday", classes = {Author.class})
    private Date authorBirthday;

    @JMap(classes = {Book.class})
    private Date releaseDate;

    @JMap(classes = {Book.class})
    private String publisher;

    private BookTypeDTO bookType;

    private List<String> chapterTitles;

    @JMap(classes = {Book.class})
    private BookCoverEnum bookCover;

    @JMapConversion(from={"releaseDate"}, to={"releaseDate"})
    public Calendar conversion(Date releaseDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        return calendar;
    }

    @JMapConversion(from={"bookCover"}, to={"bookCover"})
    public BookCover conversion(BookCoverEnum bookCoverEnum) {
        return BookCover.valueOf(bookCoverEnum.name());
    }

    @JMapConversion(from={"chapterTitles"}, to={"chapters"})
    public List<Chapter> conversion(List<String> chapterTitles) {
        List<Chapter> chapters = new ArrayList<Chapter>(chapterTitles.size());
        for(String chapterTitle: chapterTitles) {
            chapters.add(new Chapter(chapterTitle));
        }
        return chapters;
    }

}
