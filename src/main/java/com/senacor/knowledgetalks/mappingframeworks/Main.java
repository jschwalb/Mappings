package com.senacor.knowledgetalks.mappingframeworks;

import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;
import com.senacor.knowledgetalks.mappingframeworks.mappers.Mapper;
import com.senacor.knowledgetalks.mappingframeworks.mappers.dozer.DozerMapper;
import com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten.HandwrittenMapper;
import com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper.Modelmapper;
import com.senacor.knowledgetalks.mappingframeworks.mappers.orika.OrikaMapper;
import com.senacor.knowledgetalks.mappingframeworks.performance.SingleTester;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long startTimeGen = System.nanoTime();
        List<Mapper>  mapper = new LinkedList<Mapper>();
        mapper.add(new HandwrittenMapper());
        mapper.add(new OrikaMapper());
        mapper.add(new Modelmapper());
        mapper.add(new DozerMapper());

        SingleTester tester = new SingleTester(mapper, 1000) ;
        long endTimeGen = System.nanoTime();

        System.out.println("GenerationTime: " + (endTimeGen - startTimeGen));

        long startTime = System.nanoTime();
        List<String> resultReport = tester.startTest();
        long endTime = System.nanoTime();

        System.out.println("TestTime: " + (endTime - startTime));
        for(String report :resultReport){
            System.out.println(report);
        }
    }


}
