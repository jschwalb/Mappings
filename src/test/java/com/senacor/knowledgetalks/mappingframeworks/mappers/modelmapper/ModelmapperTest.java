package com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

public class ModelmapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new Modelmapper();
    }

}
