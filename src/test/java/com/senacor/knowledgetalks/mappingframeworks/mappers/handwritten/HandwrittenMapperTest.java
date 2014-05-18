package com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

public class HandwrittenMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new HandwrittenMapper();
    }

}
