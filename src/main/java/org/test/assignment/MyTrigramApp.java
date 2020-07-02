package org.test.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.assignment.exception.InputDataException;
import org.test.assignment.process.InputDataReader;

import java.io.File;

/*
* Author : Atul Kumar
* */
public class MyTrigramApp {

    private static final String FILENAME= "/stories";
    private static final Logger logger = LoggerFactory.getLogger(MyTrigramApp.class);

    /**
     * @param args
     */
    public static void main(String[] args) throws InputDataException {
        if (args.length < 1) {
            logger.info("I have store one story as a built-in file in classpath:"+ FILENAME);
            new InputDataReader().generate(MyTrigramApp.class.getResourceAsStream(FILENAME));
        } else {
            new InputDataReader().generate(new File(args[0]));
        }
    }
}
