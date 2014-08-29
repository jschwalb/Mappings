package com.senacor.knowledgetalks.mappingframeworks.mappers.jmapper;

import com.googlecode.jmapper.JMapper;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Author;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;

/**
 * Created by jschwalb on 21.08.14.
 */
public class JMapperImpl implements Mapper {


    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        JMapper<Book, BookDTO> bookJMapper = new JMapper<Book, BookDTO>(Book.class, BookDTO.class);
        Book book = bookJMapper.getDestination(bookDTO);

        JMapper<Author, BookDTO> authorJMapper = new JMapper<Author, BookDTO>(Author.class, BookDTO.class);
        Author author = authorJMapper.getDestination(bookDTO);

        book.setAuthor(author);

        System.out.println(book);
        return book;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        return null;
    }

    @Override
    public String getMapperName() {
        return "JMapper";
    }
}
