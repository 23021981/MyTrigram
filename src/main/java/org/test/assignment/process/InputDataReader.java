package org.test.assignment.process;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.assignment.common.StringConstant;
import org.test.assignment.exception.InputDataException;
import org.test.assignment.utils.StringJoinerUtils;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.IntStream;

/*
 * Author : Atul Kumar
 * */
public class InputDataReader {

    private static final Logger logger = LoggerFactory.getLogger(InputDataReader.class);

    public String read(InputStream is) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String record = inputStream.readLine();
        while (record != null) {
            builder.append(record + StringConstant.NEW_LINE);
            record = inputStream.readLine();
        }
        return builder.toString();
    }

    public boolean generate(File file) throws InputDataException {
        boolean isGenerated= false;
        try(FileInputStream fis = new FileInputStream(file)) {
            isGenerated = generate(fis);
        } catch (FileNotFoundException e) {
            throw new InputDataException("ERROR002", e.getMessage());
        } catch (IOException e) {
            throw new InputDataException("ERROR001", e.getMessage());
        } finally {
            return isGenerated;
        }
    }

    public boolean generate(InputStream is) {
        boolean isGenerated= false;
        try {
            KeyGenerator store = new KeyGenerator();
            String text = read(is);
            store.addKeys(text);
            isGenerated= true;
            logger.info("========Original========");
            logger.info(text);
            consoleOutPut(store);
        } catch (IOException e) {
            throw new InputDataException("ERROR001", e.getMessage());
        } finally {
            return isGenerated;
        }
    }

    private void consoleOutPut(KeyGenerator store){

        logger.info(store.toString());
        logger.info("=========Trigram=======");

        List<String> messages = wrapText(new Processor(store).processText(), 80);
        logger.info(StringJoinerUtils.joinText(messages, StringConstant.NEW_LINE));
    }
    private List<String> wrapText(String text, int len) {
        if (StringUtils.isEmpty(text)) {
            return Collections.emptyList();
        }

        if (len <= 0 || text.length() <= len) {
            return Arrays.asList(text);
        }

        return buildLine(text.toCharArray(), len);
    }

    private List<String> buildLine(char[] chars, int len){
        List<String> lines = new ArrayList<>();
        StringBuffer line = new StringBuffer();
        StringBuffer word = new StringBuffer();

        IntStream.range(0, chars.length).forEach(i -> {
            word.append(chars[i]);
            if (chars[i] == ' ') {
                if ((line.length() + word.length()) > len) {
                    lines.add(line.toString());
                    line.delete(0, line.length());
                }
                line.append(word);
                word.delete(0, word.length());
            }
        });


        if (StringUtils.isNotEmpty(word.toString())) {
            if ((line.length() + word.length()) > len) {
                lines.add(line.toString());
                line.delete(0, line.length());
            }
            line.append(word);
        }

        if (StringUtils.isNotEmpty(line.toString())) {
            lines.add(line.toString());
        }

        return lines;
    }
}
