package com.senacor.knowledgetalks.mappingframeworks.mappers.dozer;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuebl on 24.05.14.
 */
public class DozerMapper implements Mapper {


    private final DozerBeanMapper mapper;

    public DozerMapper() {
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozer-mapping-config.xml");

        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(myMappingFiles);

    }

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        } else {
            return mapper.map(bookDTO, Book.class);
        }
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        if (book == null) {
            return null;
        } else {
            return mapper.map(book, BookDTO.class);
        }
    }

    @Override
    public String getMapperName() {
        return "Dozer Mapper";
    }
}
