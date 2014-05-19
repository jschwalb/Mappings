package com.senacor.knowledgetalks.mappingframeworks.mappers;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookBoundEnum;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public abstract class AbstractMapperTest {

    protected Mapper mapper;
    private BookDTO bookDTO;
    private Book bookEntity;

    @Before
    public void setUp() {
        initializeMappers();
        this.bookDTO = initializeDTO();
        this.bookEntity = initializeEntity();
    }

    protected abstract void initializeMappers();

    private BookDTO initializeDTO() {
        BookDTO result = new BookDTO();
        result.setTitle("Buddenbrooks");
        result.setReleaseDate(new Date());
        result.setPublisher("Fischer");
        result.setBookType(BookTypeDTO.NOVEL);

        result.setAuthorFirstName("Thomas");
        result.setAuthorLastName("Mann");
        result.setAuthorBirthday(new Date());

        result.setChapterTitles(Arrays.asList("Kapitel 1", "Kapitel 2", "Kapitel 3"));

        result.setBookBound(BookBoundEnum.HARD_COVER);

        return result;
    }

    private Book initializeEntity() {
        Book result = new NonFictionBook();
        result.setTitle("Groovy in Action");
        result.setPublisher("Manning");
        result.setReleaseDate(Calendar.getInstance());

        Author author = new Author();
        author.setLastName("König");
        author.setFirstName("Dirk");
        author.setBirthday(new Date());
        result.setAuthor(author);

        Chapter chapter1 = new Chapter();
        chapter1.setTitle("One");
        Chapter chapter2 = new Chapter();
        chapter2.setTitle("Two");
        List<Chapter> chapterList = Arrays.asList(chapter1, chapter2);
        result.setChapters(chapterList);

        result.setBookBound(BookBound.PAPER_BACK);

        return result;
    }

    @Test
    public void testMapBookEntity2DTO() {
        BookDTO result = mapper.mapEntity2DTO(bookEntity);

        assertNotNull(result);
        assertEquals(bookEntity.getTitle(), result.getTitle());
        assertEquals(bookEntity.getPublisher(), result.getPublisher());
        assertEquals(bookEntity.getReleaseDate().getTime(), result.getReleaseDate());

        //assertNotNull(bookEntity.getAuthor());
        if (bookEntity.getAuthor() != null) {
            Author author = bookEntity.getAuthor();
            assertEquals(author.getFirstName(), result.getAuthorFirstName());
            assertEquals(author.getLastName(), result.getAuthorLastName());
            assertEquals(author.getBirthday(), result.getAuthorBirthday());
        }

        if (bookEntity.getChapters() != null) {
             assertNotNull(result.getChapterTitles());
            assertEquals(bookEntity.getChapters().size(), result.getChapterTitles().size());


            //TODO: Check Content of Chapters...
            ListIterator<Chapter> chapterListIterator = bookEntity.getChapters().listIterator();
            ListIterator<String> stringListIterator = result.getChapterTitles().listIterator();

            while(chapterListIterator.hasNext() && stringListIterator.hasNext()){
                Chapter chapter = chapterListIterator.next();
                String string = stringListIterator.next();
                assertEquals(chapter.getTitle(), string);

            }


        }

        //TODO: Check BookType
        if (bookEntity.getClass() == Novel.class) {
            assertEquals(BookTypeDTO.NOVEL, result.getBookType());
        }
        if (bookEntity.getClass() == NonFictionBook.class) {
            assertEquals(BookTypeDTO.NON_FICTION, result.getBookType());
        }

        //Check BoundType
        assertEquals(BookBoundEnum.PAPER_BACK, result.getBookBound());
    }

    @Test
    public void testMapDTO2BookEntity() {
        Book result = mapper.mapDTO2Entity(bookDTO);

        assertNotNull(result);
        assertEquals(bookDTO.getTitle(), result.getTitle());
        assertEquals(bookDTO.getPublisher(), result.getPublisher());
        assertEquals(bookDTO.getReleaseDate(), result.getReleaseDate().getTime());

        assertNotNull(result.getAuthor());
        assertEquals(bookDTO.getAuthorFirstName(), result.getAuthor().getFirstName());
        assertEquals(bookDTO.getAuthorLastName(), result.getAuthor().getLastName());
        assertEquals(bookDTO.getAuthorBirthday(), result.getAuthor().getBirthday());

        assertNotNull(result.getChapters());
        assertEquals(bookDTO.getChapterTitles().size(), result.getChapters().size());
        //TODO: Check Content of Chapters...
        ListIterator<Chapter> chapterListIterator =  result.getChapters().listIterator();
        ListIterator<String> stringListIterator =bookDTO.getChapterTitles().listIterator();
        while(chapterListIterator.hasNext() && stringListIterator.hasNext()){
            Chapter chapter = chapterListIterator.next();
            String string = stringListIterator.next();
            assertEquals(chapter.getTitle(), string);

        }
        //TODO: Check instanceof result - Novel or NonFiction...

        if (bookDTO.getBookType() == BookTypeDTO.NON_FICTION) {
            assertEquals(NonFictionBook.class, result.getClass());
            //assertTrue(result instanceof NonFictionBook);
        }

        if (bookDTO.getBookType() == BookTypeDTO.NOVEL) {
            assertEquals(Novel.class,result.getClass());
            //assertTrue(result instanceof Novel);
        }
        if(BookBoundEnum.HARD_COVER == bookDTO.getBookBound()) {
            assertEquals(BookBound.HARD_COVER, result.getBookBound());
        }
        if(BookBoundEnum.PAPER_BACK == bookDTO.getBookBound()){
            assertEquals(BookBound.PAPER_BACK, result.getBookBound());
        }
    }

    @Test
    public void testMapNull2BookEntity() {
        assertNull(mapper.mapDTO2Entity(null));
    }

    @Test
    public void testMapNull2BookDTO() {
        assertNull(mapper.mapEntity2DTO(null));
    }

    @Test
    public void testMapNullAuthor2BookDTO() {
        bookEntity.setAuthor(null);

        BookDTO result = mapper.mapEntity2DTO(bookEntity);

        assertNotNull(result);
        assertEquals(bookEntity.getTitle(), result.getTitle());
        assertEquals(bookEntity.getPublisher(), result.getPublisher());
        assertEquals(bookEntity.getReleaseDate().getTime(), result.getReleaseDate());

        assertNull(bookEntity.getAuthor());

        if (bookEntity.getChapters() != null) {
            // assertNotNull(result.getChapterTitles());
            // assertEquals(bookEntity.getChapters().size(), result.getChapterTitles().size());
            //TODO: Check Content of Chapters...
        }

        //TODO: Check content of BookDTOType - Novel or NonFiction...
    }

    @Test
    public void testMapEmptyAuthor2BookEntity() {
        bookDTO.setAuthorBirthday(null);
        bookDTO.setAuthorFirstName(null);
        bookDTO.setAuthorLastName(null);

        Book result = mapper.mapDTO2Entity(bookDTO);

        assertNotNull(result);
        assertEquals(bookDTO.getTitle(), result.getTitle());
        assertEquals(bookDTO.getPublisher(), result.getPublisher());
        assertEquals(bookDTO.getReleaseDate(), result.getReleaseDate().getTime());

        assertNull(result.getAuthor());

//        assertNotNull(result.getChapters());
//        assertEquals(bookDTO.getChapterTitles().size(), result.getChapters().size());
        //TODO: Check Content of Chapters...

        //TODO: Check instanceof result - Novel or NonFiction...
    }

}
