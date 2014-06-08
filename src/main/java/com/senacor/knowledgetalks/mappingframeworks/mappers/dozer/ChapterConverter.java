package com.senacor.knowledgetalks.mappingframeworks.mappers.dozer;

import com.senacor.knowledgetalks.mappingframeworks.entities.Chapter;
import org.dozer.DozerConverter;

/**
 * Created by phuebl on 07.06.14.
 */
public class ChapterConverter  extends DozerConverter<Chapter, String> {



    public ChapterConverter() {
        super(Chapter.class, String.class);
    }

    @Override
    public String convertTo(Chapter source, String destination) {
        return source.getTitle();
    }

    @Override
    public Chapter convertFrom(String source, Chapter destination) {
        if(destination == null){
            destination = new Chapter();
        }
        destination.setTitle(source);
        return destination;
    }
}