package com.senacor.knowledgetalks.mappingframeworks.entities;

import com.codiform.moo.annotation.Property;
import lombok.Data;

import java.util.Date;

@Data
public class Author {

    private String firstName;
    private String lastName;
    private Date birthday;

}
