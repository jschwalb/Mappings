package com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.entities.Chapter;
import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;

/**
 * Modelmapper Realization of the Mapper.
 */
public class Modelmapper implements Mapper {

    private ModelMapper modelMapper;

    public Modelmapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book result = null;

        switch (bookDTO.getBookType()) {
            case NOVEL:
                result = this.modelMapper.map(bookDTO, Novel.class);
                break;
            case NON_FICTION:
                result = this.modelMapper.map(bookDTO, NonFictionBook.class);
                break;
            default:
                result = this.modelMapper.map(bookDTO, Book.class);
        }

        for(String chapterTitle : bookDTO.getChapterTitles()) {
            Chapter chapter = new Chapter(chapterTitle);
            if(result.getChapters() == null) {
                result.setChapters(new ArrayList<Chapter>(bookDTO.getChapterTitles().size()));
            }
            result.getChapters().add(chapter);
        }

        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO result = this.modelMapper.map(book, BookDTO.class);

        if(book instanceof Novel) {
            result.setBookType(BookTypeDTO.NOVEL);
        } else if(book instanceof NonFictionBook) {
            result.setBookType(BookTypeDTO.NON_FICTION);
        }

        for(Chapter chapter : book.getChapters()) {
            if(result.getChapterTitles() == null) {
                result.setChapterTitles(new ArrayList<String>(book.getChapters().size()));
            }
            result.getChapterTitles().add(chapter.getTitle());
        }

        return result;
    }

    @Override
    public String getMapperName() {
        return "Modelmapper";
    }



}
