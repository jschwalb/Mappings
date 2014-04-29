package com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class HandwrittenMapper implements Mapper {

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        Book result = null;
        if(bookDTO.getBookType() != null) {
            if(bookDTO.getBookType() == BookTypeDTO.NON_FICTION) {
                result = new NonFictionBook();
            } else if (bookDTO.getBookType() == BookTypeDTO.NOVEL) {
                result = new Novel();
            }
        } else {
            result = new Book();
        }

        result.setTitle(bookDTO.getTitle());
        result.setPublisher(bookDTO.getPublisher());

        if(bookDTO.getReleaseDate() != null) {
            Calendar releaseDate = Calendar.getInstance();
            releaseDate.setTime(bookDTO.getReleaseDate());
            result.setReleaseDate(releaseDate);
        }

        Author author = new Author();
        author.setBirthday(bookDTO.getAuthorBirthday());
        author.setFirstName(bookDTO.getAuthorFirstName());
        author.setLastName(bookDTO.getAuthorLastName());
        result.setAuthor(author);

        if(bookDTO.getChapterTitles() != null) {
            List<Chapter> chapters = new LinkedList<Chapter>();
            for(String chapterTitle : bookDTO.getChapterTitles()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(chapterTitle);
            }
            result.setChapters(chapters);
        }

        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        return null;
    }

}
