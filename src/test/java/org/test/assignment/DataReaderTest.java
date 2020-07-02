package org.test.assignment;

import org.junit.Test;
import org.test.assignment.process.InputDataReader;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DataReaderTest {

    @Test
    public void testGenerateStream(){
        boolean isGenerated = new InputDataReader().generate( DataReaderTest.class.getResourceAsStream("/simple"));
        assertThat(isGenerated, equalTo(true));

    }

    @Test
    public void testComplexStream(){
        boolean isGenerated = new InputDataReader().generate(DataReaderTest.class.getResourceAsStream("/complex"));
        assertThat(isGenerated, equalTo(true));
    }

    @Test
    public void testEmptyStream(){
        boolean isGenerated = new InputDataReader().generate(DataReaderTest.class.getResourceAsStream("/empty"));
        assertThat(isGenerated, equalTo(true));
    }

    @Test
    public void testStoryStream(){
        boolean isGenerated = new InputDataReader().generate(DataReaderTest.class.getResourceAsStream("/story"));
        assertThat(isGenerated, equalTo(true));
    }

}
