package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import org.junit.Before;
import org.junit.Test;

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
        result.setAuthorFirstName("Thomas");
        result.setAuthorLastName("Mann");

        return result;
    }

    @Test
    public void testMapEntity2DTO() {
        BookDTO result = this.mapEntity2DTO(bookEntity);

        //TODO: Assertions...
    }

    public abstract BookDTO mapEntity2DTO(Book bookEntity);

    @Test
    public void testMapDTO2Entity() {
        Book result = this.mapDTO2Entity(bookDTO);

        //TODO: Assertions...

        assertNotNull(result);
        assertEquals(bookDTO.getTitle(), result.getTitle());
    }

    public abstract Book mapDTO2Entity(BookDTO bookDTO);

}
