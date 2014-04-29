package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractMapperTest {

    protected Mapper mapper;
    private BookDTO bookDTO;
    private Book bookEntity;

    @Before
    public void setUp() {
        initializeMappers();
        this.bookDTO = initializeDTO();
        this.bookEntity = initializeEntity();
    }

    protected abstract void initializeMappers();

    private BookDTO initializeDTO() {
        BookDTO result = new BookDTO();
        result.setTitle("Buddenbrooks");
        result.setReleaseDate(new Date());
        result.setPublisher("Fischer");
        result.setBookType(BookTypeDTO.NOVEL);

        result.setAuthorFirstName("Thomas");
        result.setAuthorLastName("Mann");
        result.setAuthorBirthday(new Date());

        result.setChapterTitles(Arrays.asList("Kapitel 1", "Kapitel 2", "Kapitel 3"));

        return result;
    }

    private Book initializeEntity() {
        Book result = new NonFictionBook();
        result.setTitle("Groovy in Action");
        result.setPublisher("Manning");
        result.setReleaseDate(Calendar.getInstance());

        Author author = new Author();
        author.setLastName("König");
        author.setFirstName("Dirk");
        author.setBirthday(new Date());
        result.setAuthor(author);

        Chapter chapter1 = new Chapter();
        chapter1.setTitle("One");
        Chapter chapter2 = new Chapter();
        chapter2.setTitle("Two");
        List<Chapter> chapterList = Arrays.asList(chapter1, chapter2);
        result.setChapters(chapterList);

        return result;
    }

    @Test
    public void testMapBookEntity2DTO() {
        BookDTO result = this.mapEntity2DTO(bookEntity);

        assertNotNull(result);
        assertEquals(bookEntity.getTitle(), result.getTitle());
        assertEquals(bookEntity.getPublisher(), result.getPublisher());
        assertEquals(bookEntity.getReleaseDate().getTime(), result.getReleaseDate());

        if(bookEntity.getAuthor() != null) {
            Author author = bookEntity.getAuthor();
            assertEquals(author.getFirstName(), result.getAuthorFirstName());
            assertEquals(author.getLastName(), result.getAuthorLastName());
            assertEquals(author.getBirthday(), result.getAuthorBirthday());
        }

        if(bookEntity.getChapters() != null) {
            // assertNotNull(result.getChapterTitles());
            // assertEquals(bookEntity.getChapters().size(), result.getChapterTitles().size());
            //TODO: Check Content of Chapters...
        }
    }

    public abstract BookDTO mapEntity2DTO(Book bookEntity);

    @Test
    public void testMapDTO2BookEntity() {
        Book result = this.mapDTO2Entity(bookDTO);

        assertNotNull(result);
        assertEquals(bookDTO.getTitle(), result.getTitle());
        assertEquals(bookDTO.getPublisher(), result.getPublisher());
        assertEquals(bookDTO.getReleaseDate(), result.getReleaseDate().getTime());

        assertNotNull(result.getAuthor());
        assertEquals(bookDTO.getAuthorFirstName(), result.getAuthor().getFirstName());
        assertEquals(bookDTO.getAuthorLastName(), result.getAuthor().getLastName());
        assertEquals(bookDTO.getAuthorBirthday(), result.getAuthor().getBirthday());

//        assertNotNull(result.getChapters());
//        assertEquals(bookDTO.getChapterTitles().size(), result.getChapters().size());
        //TODO: Check Content of Chapters...
    }

    public abstract Book mapDTO2Entity(BookDTO bookDTO);

}
