package com.senacor.knowledgetalks.mappingframeworks.mappers.orika;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;

/**
 * Created by phuebl on 17.05.14.
 */
public class OrikaMapper implements Mapper {

    BoundMapperFacade<BookDTO, Book> mapperDTO2Entity;

    BoundMapperFacade<Book, BookDTO> mapperEntity2DTO;

    public OrikaMapper() {
        initialize();
    }


    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        return mapperDTO2Entity.map(bookDTO);
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        return mapperEntity2DTO.map(book);
    }

    @Override
    public String getMapperName() {
        return "Orika Mapper";
    }


    public void initialize() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.registerObjectFactory(new BookDTOFactory(), TypeFactory.valueOf(BookDTO.class));
        mapperFactory.registerObjectFactory(new BookFactory(), TypeFactory.valueOf(Book.class));
        mapperFactory.classMap(Book.class, BookDTO.class)
//                .field("title", "title")
                .field("author.firstName", "authorFirstName")
                .field("author.lastName", "authorLastName")
                .field("author.birthday", "authorBirthday")
//                .field("publisher", "publisher")
//                .field("releaseDate", "releaseDate")
                .field("chapters{title}", "chapterTitles{}")
//                .field("bookCover", "bookCover")
                .byDefault()
                .register();


        mapperEntity2DTO = mapperFactory.getMapperFacade(Book.class, BookDTO.class);
        mapperDTO2Entity = mapperFactory.getMapperFacade(BookDTO.class, Book.class);
    }

    class BookDTOFactory implements ObjectFactory<BookDTO> {

        @Override
        public BookDTO create(Object source, MappingContext mappingContext) {
            BookDTO createdObject = new BookDTO();
            if (source.getClass() == Novel.class) {
                createdObject.setBookType(BookTypeDTO.NOVEL);
            } else if (source.getClass() == NonFictionBook.class) {
                createdObject.setBookType(BookTypeDTO.NON_FICTION);
            }
            return createdObject;
        }
    }

    class BookFactory implements ObjectFactory<Book> {

        @Override
        public Book create(Object source, MappingContext mappingContext) {
            if (source.getClass() ==  BookDTO.class) {
                BookDTO dto = (BookDTO) source;
                switch (dto.getBookType()) {
                    case NOVEL:
                        return new Novel();
                    case NON_FICTION:
                        return new NonFictionBook();
                    default:
                        return new Book();
                }
            }
            return null;
        }
    }


}
