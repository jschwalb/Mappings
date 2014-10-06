package com.senacor.knowledgetalks.mappingframeworks.mappers.moo;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;
import com.senacor.knowledgetalks.mappingframeworks.mappers.handwritten.HandwrittenMapper;
import org.junit.Ignore;

public class MooMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new MooMapper();
    }

}
