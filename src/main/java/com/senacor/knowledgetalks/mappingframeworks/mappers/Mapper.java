package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;

/**
 * Created by jschwalb on 28.04.14.
 */
public interface Mapper {

    Book mapDTO2Entity(BookDTO bookDTO);

    BookDTO mapEntity2DTO(Book book);

    String getMapperName();

}
