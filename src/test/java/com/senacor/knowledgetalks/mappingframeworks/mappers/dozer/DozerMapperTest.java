package com.senacor.knowledgetalks.mappingframeworks.mappers.dozer;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

public class DozerMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new DozerMapper();
    }

}
