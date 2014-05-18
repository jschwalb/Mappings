package com.senacor.knowledgetalks.mappingframeworks.mappers.orikamapper;

import com.senacor.knowledgetalks.mappingframeworks.mappers.AbstractMapperTest;
import com.senacor.knowledgetalks.mappingframeworks.mappers.modelmapper.Modelmapper;
import com.senacor.knowledgetalks.mappingframeworks.mappers.orika.OrikaMapper;

public class OrikamapperTest extends AbstractMapperTest {

    @Override
    protected void initializeMappers() {
        mapper = new OrikaMapper();
    }

}
