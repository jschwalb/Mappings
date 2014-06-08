package com.senacor.knowledgetalks.mappingframeworks.mappers.moo;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;
import com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten.HandwrittenMapper;

public class MooMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new MooMapper();
    }

}
