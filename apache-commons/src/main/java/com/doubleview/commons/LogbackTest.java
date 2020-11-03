package com.doubleview.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("LogbackTest");
        logger.debug("bbb");
    }
}
