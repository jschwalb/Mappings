package com.senacor.knowledgetalks.mappingframeworks.mappers.jmapper;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;

/**
 * Created by jschwalb on 21.08.14.
 */
public class JMapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new JMapperImpl();
    }

}
