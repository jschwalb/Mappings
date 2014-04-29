package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractMapperTest {

    private BookDTO bookDTO;
    private Book bookEntity;

    @Before
    public void setUp() {
        initializeMappers();
        this.bookDTO = initializeDTO();
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
        //TODO...

        return null;
    }

    @Test
    public void testMapBookEntity2DTO() {
        BookDTO result = this.mapEntity2DTO(bookEntity);

        //TODO: Assertions...
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

        //assertNotNull(result.getChapters());
        //assertEquals(bookDTO.getChapterTitles().size(), result.getChapters().size());
        //TODO: Check Content of Chapters...
    }

    public abstract Book mapDTO2Entity(BookDTO bookDTO);

}
