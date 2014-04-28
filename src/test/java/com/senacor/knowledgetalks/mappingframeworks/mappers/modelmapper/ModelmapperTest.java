package com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

/**
 * Created by jschwalb on 28.04.14.
 */
public class ModelmapperTest extends AbstractMapperTest {

    private Modelmapper modelmapper;

    @Override
    protected void initializeMappers() {
        modelmapper = new Modelmapper();
    }

    @Override
    public BookDTO mapEntity2DTO(Book bookEntity) {
        return modelmapper.mapEntity2DTO(bookEntity);
    }

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        return modelmapper.mapDTO2Entity(bookDTO);
    }

}
