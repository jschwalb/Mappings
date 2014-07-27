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

/**
 * Created by jschwalb on 25.04.14.
 */
public class Main {

    public static void main2(String[] args) {
        Novel novel = new Novel();
        novel.setTitle("Ulf im Walde");

        Novel novel2 = new Novel();
        novel2.setTitle("Ulf im Walde");

        NonFictionBook nfb = new NonFictionBook();
        nfb.setTitle("Ulf im Walde");

        System.out.println(novel.equals(novel2));
        System.out.println(novel.equals(nfb));
    }


    public static void main(String[] args) {



        long startTimeGen = System.nanoTime();
        List<Mapper>  mapper = new LinkedList<Mapper>();
        mapper.add(new Modelmapper());
        mapper.add(new HandwrittenMapper());
        mapper.add(new OrikaMapper());
        mapper.add(new DozerMapper());

        SingleTester tester = new SingleTester(mapper, 100000) ;
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
