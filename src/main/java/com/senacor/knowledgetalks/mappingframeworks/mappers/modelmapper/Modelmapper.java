package com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class Modelmapper implements Mapper {

    private ModelMapper modelMapper;

    public Modelmapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        return this.modelMapper.map(bookDTO, Book.class);
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        return this.modelMapper.map(book, BookDTO.class);
    }

    @Override
    public String getMapperName() {
        return "Modelmapper";
    }
}
