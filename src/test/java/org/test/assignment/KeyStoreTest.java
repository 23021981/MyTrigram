package org.test.assignment;

import org.junit.Test;
import org.test.assignment.process.InputDataReader;
import org.test.assignment.process.KeyGenerator;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class KeyStoreTest {
    @Test
    public void testAddKeysFromSimpleInputFile() throws IOException {
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/simple")));
        assertThat(store.getSize(), equalTo(8));
        assertThat(store.getList(Arrays.asList("1","2")), equalTo(Arrays.asList("3")));
        assertThat(store.getList(Arrays.asList("2","3")), equalTo(Arrays.asList("4")));
        assertThat(store.getList(Arrays.asList("3","4")), equalTo(Arrays.asList("5")));
        assertThat(store.getList(Arrays.asList("4","5")), equalTo(Arrays.asList("6")));
        assertThat(store.getList(Arrays.asList("5","6")), equalTo(Arrays.asList("7")));
        assertThat(store.getList(Arrays.asList("6","7")), equalTo(Arrays.asList("8")));
        assertThat(store.getList(Arrays.asList("7","8")), equalTo(Arrays.asList("9")));
        assertThat(store.getList(Arrays.asList("8","9")), equalTo(Arrays.asList("10")));
    }

    @Test
    public void testAddMultiKeysFromComplexInputFile() throws IOException {
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/complex")));
        assertThat(store.getSize(), equalTo(3));
        assertThat(store.getList(Arrays.asList("1","2")), equalTo(Arrays.asList("3", "4")));
        assertThat(store.getList(Arrays.asList("2","3")), equalTo(Arrays.asList("1")));
        assertThat(store.getList(Arrays.asList("3","1")), equalTo(Arrays.asList("2")));
    }

    @Test
    public void testGetRootKeyFromSimpleInputFile() throws IOException {
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/simple")));
        assertThat(store.getRootKey(), equalTo(Arrays.asList("1","2")));
    }

    @Test
    public void testClear() throws IOException {
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/simple")));
        assertThat(store.getSize(), equalTo(8));
        store.clear();
        assertThat(store.getSize(), equalTo(0));
    }

    @Test
    public void testIsContain() throws IOException {
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/simple")));
        assertTrue(store.isContain(Arrays.asList("1","2")));
    }

    @Test
    public void testAddKeyForPunctuationInput() throws IOException{
        KeyGenerator store = new KeyGenerator();
        store.addKeys(new InputDataReader().read(KeyStoreTest.class.getResourceAsStream("/punctuation")));
        assertThat(store.getSize(), equalTo(4));
        assertThat(store.getList(Arrays.asList("1","2,")), equalTo(Arrays.asList("3;")));
        assertThat(store.getList(Arrays.asList("2,","3;")), equalTo(Arrays.asList("4")));
        assertThat(store.getList(Arrays.asList("3;","4")), equalTo(Arrays.asList("5")));
        assertThat(store.getList(Arrays.asList("4","5")), equalTo(Arrays.asList("6")));
    }
}
