package com.senacor.knowledgetalks.mappingframeworks.performance;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by phuebl on 30.04.14.
 */
public class SingleTester {
    private final List<Mapper> mapper;
    List<Book> books;

    List<BookDTO> bookDTOs;

    Map<Mapper, String> resultMap;

    public SingleTester(List<Mapper> mapper, int numberOfObjects) {
        this.books = new ArrayList<Book>();
        this.bookDTOs = new ArrayList<BookDTO>();
        generateObjects(numberOfObjects);

        this.mapper = mapper;
        this.resultMap = new HashMap<Mapper, String>();
    }

    public void generateObjects(int numberOfObjects) {
        for (int i = 0; i < numberOfObjects; i++) {
            this.books.add(BookGenerator.generateRandomBook());
            this.bookDTOs.add(BookGenerator.generateRandomBookDTO());
        }

    }


    public void startTest() {

        for (Mapper mapper : this.mapper) {
            List<Long> mappingEntityToBookDTOTime = new ArrayList<Long>();
            List<Long> mappingDTOToBookEntityTime = new ArrayList<Long>();
            for (Book book : books) {
                long startTime = System.nanoTime();
                mapper.mapEntity2DTO(book);
                long endTime = System.nanoTime();
                mappingEntityToBookDTOTime.add(endTime - startTime);
            }

            for (BookDTO bookDTO : bookDTOs) {
                long startTime = System.nanoTime();
                mapper.mapDTO2Entity(bookDTO);
                long endTime = System.nanoTime();
                mappingDTOToBookEntityTime.add(endTime - startTime);
            }

            long avgToBookDTO = 0l;
            for(Long val : mappingEntityToBookDTOTime){
                avgToBookDTO+=val;
            }
            avgToBookDTO = avgToBookDTO/mappingEntityToBookDTOTime.size();


            long avgToBookEntity = 0l;
            for(Long val : mappingDTOToBookEntityTime){
                avgToBookEntity+=val;
            }
            avgToBookEntity = avgToBookEntity/mappingDTOToBookEntityTime.size();


            System.out.println(mapper.getMapperName()+":\t Mapping DTO 2 E:"+ avgToBookEntity+" Mapping E 2 DTO: "+avgToBookDTO);
        }


    }


}
