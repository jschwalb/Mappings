package com.senacor.knowledgetalks.mappingframeworks.performance;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookCoverEnum;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

/**
 * Created by phuebl on 30.04.14.
 */
public class BookGenerator {

    static RandomStringUtils RANDOM_STRING_UTILS = new RandomStringUtils();
    static Random RANDOM = new Random();

    public static Book generateRandomBook() {
        Book book = new Book();
        boolean bookType = RANDOM.nextBoolean();
        if (bookType) {
            book = new NonFictionBook();
        } else {
            book = new Novel();
        }

        boolean bookCover = RANDOM.nextBoolean();
        if (bookCover) {
            book.setBookCover(BookCover.HARD_COVER);
        } else {
            book.setBookCover(BookCover.PAPER_BACK);
        }


        Author author = new Author();
        author.setBirthday(new Date());
        author.setFirstName(RANDOM_STRING_UTILS.random(10));
        author.setLastName(RANDOM_STRING_UTILS.random(10));

        book.setAuthor(author);
        book.setPublisher(RANDOM_STRING_UTILS.random(25));
        book.setReleaseDate(Calendar.getInstance());
        book.setTitle(RANDOM_STRING_UTILS.random(30));

        List<Chapter> chapters = new ArrayList<Chapter>();
        int countChapter = RANDOM.nextInt(30);
        for (int i = 0; i < countChapter; i++) {
            Chapter chapter = new Chapter();
            chapter.setTitle(RANDOM_STRING_UTILS.random(30));
        }
        book.setChapters(chapters);
        return book;
    }

    public static BookDTO generateRandomBookDTO(){
        BookDTO bookDTO = new BookDTO();
        boolean bookType = RANDOM.nextBoolean();
        if (bookType) {
            bookDTO.setBookType(BookTypeDTO.NON_FICTION);
        } else {
            bookDTO.setBookType(BookTypeDTO.NOVEL);
        }

        boolean bookCover = RANDOM.nextBoolean();
        if (bookCover) {
            bookDTO.setBookCover(BookCoverEnum.HARD_COVER);
        } else {
            bookDTO.setBookCover(BookCoverEnum.PAPER_BACK);
        }

        bookDTO.setTitle(RANDOM_STRING_UTILS.random(30));
        bookDTO.setAuthorFirstName(RANDOM_STRING_UTILS.random(10));
        bookDTO.setAuthorLastName(RANDOM_STRING_UTILS.random(10));
        bookDTO.setAuthorBirthday(new Date());
        bookDTO.setPublisher(RANDOM_STRING_UTILS.random(25));
        bookDTO.setReleaseDate(new Date());

        List<String> chapters = new LinkedList<String>();
        int countChapter = RANDOM.nextInt(30);
        for (int i = 0; i < countChapter; i++) {

            chapters.add(RANDOM_STRING_UTILS.random(30));
        }
        bookDTO.setChapterTitles(chapters);
        return bookDTO;
    }
}
