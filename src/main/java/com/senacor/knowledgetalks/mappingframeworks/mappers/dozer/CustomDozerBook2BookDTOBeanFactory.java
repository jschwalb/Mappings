package com.senacor.knowledgetalks.mappingframeworks.mappers.dozer;

import com.senacor.knowledgetalks.mappingframeworks.dtos.BookDTO;
import com.senacor.knowledgetalks.mappingframeworks.dtos.BookTypeDTO;
import com.senacor.knowledgetalks.mappingframeworks.entities.NonFictionBook;
import com.senacor.knowledgetalks.mappingframeworks.entities.Novel;
import org.dozer.BeanFactory;

/**
 * Created by phuebl on 08.06.14.
 */
public class CustomDozerBook2BookDTOBeanFactory implements BeanFactory {

    @Override
    public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
        if (source == null) {
            return null;
        } else if (source instanceof Novel) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookType(BookTypeDTO.NOVEL);
            return bookDTO;
        } else if (source instanceof NonFictionBook) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookType(BookTypeDTO.NON_FICTION);
            return bookDTO;
        } else {
            return new BookDTO();
        }
    }
}
