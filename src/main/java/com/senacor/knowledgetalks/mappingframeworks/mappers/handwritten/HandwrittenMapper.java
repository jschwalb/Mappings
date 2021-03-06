package com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookCoverEnum;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HandwrittenMapper implements Mapper {

    @Override
    public Book mapDTO2Entity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book result = null;
        if (bookDTO.getBookType() != null) {
            switch (bookDTO.getBookType()){
                case NON_FICTION :
                    result = new NonFictionBook();
                    break;
                case NOVEL :
                    result = new Novel();
                    break;
                default:
                    result = new Book();
                    break;
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

        if (bookDTO.getAuthorBirthday() != null || StringUtils.isNotBlank(bookDTO.getAuthorFirstName()) || StringUtils.isNoneBlank(bookDTO.getAuthorLastName())) {
            Author author = new Author();
            author.setBirthday(bookDTO.getAuthorBirthday());
            author.setFirstName(bookDTO.getAuthorFirstName());
            author.setLastName(bookDTO.getAuthorLastName());
            result.setAuthor(author);
        }

        if (bookDTO.getChapterTitles() != null) {
            List<Chapter> chapters = new ArrayList<Chapter>(bookDTO.getChapterTitles().size());
            for (String chapterTitle : bookDTO.getChapterTitles()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(chapterTitle);
                chapters.add(chapter);
            }
            result.setChapters(chapters);
        }

        switch (bookDTO.getBookCover()){
            case HARD_COVER: result.setBookCover(BookCover.HARD_COVER);
                break;
            case PAPER_BACK: result.setBookCover(BookCover.PAPER_BACK);
                break;
        }

        return result;
    }

    @Override
    public BookDTO mapEntity2DTO(Book book) {
        if (book == null) {
            return null;
        }

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
            bookDTO.setChapterTitles(chapterTitles);
        }

        if (book.getClass() ==  NonFictionBook.class) {
            bookDTO.setBookType(BookTypeDTO.NON_FICTION);
        } else if (book.getClass() ==  Novel.class) {
            bookDTO.setBookType(BookTypeDTO.NOVEL);
        }

        switch (book.getBookCover()){
            case HARD_COVER: bookDTO.setBookCover(BookCoverEnum.HARD_COVER);
                break;
            case PAPER_BACK: bookDTO.setBookCover(BookCoverEnum.PAPER_BACK);
                break;
        }

        return bookDTO;
    }

    @Override
    public String getMapperName() {
        return "Handwritten";
    }

}
