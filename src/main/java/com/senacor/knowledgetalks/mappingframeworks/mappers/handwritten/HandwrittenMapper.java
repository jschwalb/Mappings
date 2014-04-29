package com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HandwrittenMapper implements Mapper {

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        Book result = null;
        if (bookDTO.getBookType() != null) {
            if (bookDTO.getBookType() == BookTypeDTO.NON_FICTION) {
                result = new NonFictionBook();
            } else if (bookDTO.getBookType() == BookTypeDTO.NOVEL) {
                result = new Novel();
            }
        } else {
            result = new Book();
        }

        result.setTitle(bookDTO.getTitle());
        result.setPublisher(bookDTO.getPublisher());

        if (bookDTO.getReleaseDate() != null) {
            Calendar releaseDate = Calendar.getInstance();
            releaseDate.setTime(bookDTO.getReleaseDate());
            result.setReleaseDate(releaseDate);
        }

        Author author = new Author();
        author.setBirthday(bookDTO.getAuthorBirthday());
        author.setFirstName(bookDTO.getAuthorFirstName());
        author.setLastName(bookDTO.getAuthorLastName());
        result.setAuthor(author);

        if (bookDTO.getChapterTitles() != null) {
            List<Chapter> chapters = new ArrayList<Chapter>(bookDTO.getChapterTitles().size());
            for (String chapterTitle : bookDTO.getChapterTitles()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(chapterTitle);
                chapters.add(chapter);
            }
            result.setChapters(chapters);
        }

        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setTitle(book.getTitle());
        bookDTO.setPublisher(book.getPublisher());

        if (book.getReleaseDate() != null) {
            bookDTO.setReleaseDate(book.getReleaseDate().getTime());
        }

        if (book.getAuthor() != null) {
            Author author = book.getAuthor();
            bookDTO.setAuthorFirstName(author.getFirstName());
            bookDTO.setAuthorLastName(author.getLastName());
            bookDTO.setAuthorBirthday(author.getBirthday());
        }

        if (book.getChapters() != null) {
            List<String> chapterTitles = new ArrayList<String>(book.getChapters().size());
            for (Chapter chapter : book.getChapters()) {
                chapterTitles.add(chapter.getTitle());
            }
        }

        if (book instanceof NonFictionBook) {
            bookDTO.setBookType(BookTypeDTO.NON_FICTION);
        } else if (book instanceof Novel) {
            bookDTO.setBookType(BookTypeDTO.NOVEL);
        }

        return bookDTO;
    }

}
