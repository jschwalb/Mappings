package com.senacor.knowledgetalks.mappingframeworks;

import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;

/**
 * Created by jschwalb on 25.04.14.
 */
public class Main {

    public static void main(String[] args) {
        Novel novel = new Novel();
        novel.setTitle("Ulf im Walde");

        Novel novel2 = new Novel();
        novel2.setTitle("Ulf im Walde");

        NonFictionBook nfb = new NonFictionBook();
        nfb.setTitle("Ulf im Walde");

        System.out.println(novel.equals(novel2));
        System.out.println(novel.equals(nfb));
    }

}
