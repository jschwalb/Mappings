package com.senacor.knowledgetalks.mappingframeworks.mappers.omapper;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.omapper.mapper.SimpleMapper;

/**
 * Created by jschwalb on 26.09.14.
 */
public class OMapper implements Mapper {

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        Book result = new Book();
        SimpleMapper<Book, BookDTO> dto2EntityMapper = new SimpleMapper<Book, BookDTO>(Book.class, BookDTO.class);
        dto2EntityMapper.mapBean(result, bookDTO);
        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        BookDTO result = new BookDTO();
        SimpleMapper<BookDTO, Book> entity2DtoMapper = new SimpleMapper<BookDTO, Book>(BookDTO.class, Book.class);
        entity2DtoMapper.mapBean(result, book);
        return result;
    }

    @Override
    public String getMapperName() {
        return "Omapper";
    }

}
