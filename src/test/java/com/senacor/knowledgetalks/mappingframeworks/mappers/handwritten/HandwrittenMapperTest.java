package com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

public class HandwrittenMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        this.mapper = new HandwrittenMapper();
    }

    @Override
    public BookDTO mapEntity2DTO(Book bookEntity) {
        return this.mapper.mapEntity2DTO(bookEntity);
    }

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        return this.mapper.mapDTO2Entity(bookDTO);
    }

}
