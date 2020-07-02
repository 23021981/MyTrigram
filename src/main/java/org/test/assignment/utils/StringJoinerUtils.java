package org.test.assignment.utils;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
/*
 * Author : Atul Kumar
 * */
public class StringJoinerUtils {

    public static String joinText(List<String> messages, String deliminator){
        return messages.stream().collect(Collectors.joining(deliminator));
    }

    public static String joinTextWithNull(List<String> messages, String deliminator, String useForNull){
        String messageText = messages.stream().map(message-> {
            if(StringUtils.isEmpty(message)) return useForNull;
            return message;}).collect(Collectors.joining(deliminator));
        return messageText;
    }
}
