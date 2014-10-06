package com.senacor.knowledgetalks.mappingframeworks.mappers.moo;

import com.codiform.moo.Moo;
import com.codiform.moo.annotation.AccessMode;
import com.codiform.moo.configuration.Configuration;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Author;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;

/**
 * Created by phuebl on 08.06.14.
 */
public class MooMapper implements Mapper{

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        Configuration configuration = new Configuration();
        configuration.setDefaultAccessMode(AccessMode.FIELD);

        Moo moo = new Moo(configuration);
        Book result = new Book();
        Author author = new Author();
        result.setAuthor(author);
        //Author author = moo.translate(bookDTO, Author.class);
        moo.update(bookDTO, result);
        //result.setAuthor(author);
        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        return null;
    }

    @Override
    public String getMapperName() {
        return "Moo";
    }
}
