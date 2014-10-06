package com.senacor.knowledgetalks.mappingframeworks.dtos;

import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;
import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import com.senacor.knowledgetalks.mappingframeworks.entities.Author;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.entities.BookCover;
import com.senacor.knowledgetalks.mappingframeworks.entities.Chapter;
import lombok.Data;
import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jschwalb on 25.04.14.
 */
@Data
@Mappable
public class BookDTO {

    @JMap(classes = {Book.class})
    @Source(type = Book.class, property = "title")
    private String title;

    @JMap(value = "firstName", classes = {Author.class})
    @Property(source = "mvel:author.firstName")
    private String authorFirstName;

    @JMap(value = "lastName", classes = {Author.class})
    @Property(source = "mvel:author.lastName")
    private String authorLastName;

    @JMap(value = "birthday", classes = {Author.class})
    @Property(source = "mvel:author.birthday")
    private Date authorBirthday;

    @JMap(classes = {Book.class})
    @Ignore
    private Date releaseDate;

    @JMap(classes = {Book.class})
    private String publisher;

    private BookTypeDTO bookType;

    private List<String> chapterTitles;

    @JMap(classes = {Book.class})
    @Property(source = "mvel:BookCover.valueOf(this.bookCover.name())")
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
