package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import org.junit.Before;
import org.junit.Test;

public abstract class MapperTest {

    private BookDTO bookDTO;
    private Book bookEntity;

    @Before
    public void setUp() {
    //TODO: Create BookDTO and BookEntity
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
    }

    public abstract Book mapDTO2Entity(BookDTO bookDTO);

}
