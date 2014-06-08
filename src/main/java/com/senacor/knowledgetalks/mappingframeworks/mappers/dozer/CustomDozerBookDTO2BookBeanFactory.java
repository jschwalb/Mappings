package com.senacor.knowledgetalks.mappingframeworks.mappers.dozer;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.Book;
import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;
import org.dozer.BeanFactory;

/**
 * Created by phuebl on 08.06.14.
 */
public class CustomDozerBookDTO2BookBeanFactory implements BeanFactory {

    @Override
    public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
        if (source == null) {
            return null;
        } else if (source instanceof BookDTO) {
            BookDTO sourceBookDTO = (BookDTO) source;


            switch (sourceBookDTO.getBookType()){
                case NON_FICTION:
                    return new NonFictionBook();
                case NOVEL:
                    return new Novel();
                default:
                    return new Book();
            }
        }

        return new Book();
    }
}
