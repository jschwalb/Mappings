package com.senacor.knowledgetalks.mappingframeworks.performance;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public List<String> startTest() {

        List<String> resultReport = new ArrayList<String>();
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


            resultReport.add( mapper.getMapperName()+":\t Mapping DTO_2_E:\t"+ avgToBookEntity+" ns \tMapping E_2_DTO:\t"+avgToBookDTO+" ns");
            System.gc();
        }

        return resultReport;
    }


}
