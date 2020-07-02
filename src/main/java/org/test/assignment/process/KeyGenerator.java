package org.test.assignment.process;

import org.apache.commons.lang.StringUtils;
import org.test.assignment.common.StringConstant;
import org.test.assignment.utils.StringJoinerUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Author : Atul Kumar
 * */
public class KeyGenerator {

    private List<String> first = Collections.emptyList();
    private Map<String, List<String>> trigram = Collections.emptyMap();

    public KeyGenerator() {
    }

    /**
     * Creates a map of keys and accepted values for each key.
     *
     * @param text
     * @throws IOException
     */
    public void addKeys(String text) throws IOException {
        String[] words = text.replaceAll(StringConstant.NEW_LINE, " ").split(" ");

        List<String> wordList = Arrays.asList(words).stream()
                .filter(string->string.trim().length()>0).collect(Collectors.toList());
        if (wordList.size() < 3) {
            return;
        }
        first = Arrays.asList(wordList.get(0), wordList.get(1));
        trigram = new HashMap<String, List<String>>();

        IntStream.range(0, wordList.size() - 2).forEach(i -> {
            String key = wordList.get(i) + StringConstant.ZERO + wordList.get(i + 1);
            List<String> list = trigram.get(key);
            if (Objects.isNull(list)) {
                trigram.put(key, (list = new ArrayList<String>()));
            }
            list.add(wordList.get(i + 2));
        });
    }

    /**
     * Clear the map.
     */
    public void clear() {
        first = Collections.emptyList();
        trigram = Collections.emptyMap();
    }

    /**
     * Returns the size of the map.
     *
     * @return
     */
    public int getSize() {
        return trigram.size();
    }

    /**
     * Returns the first two words in the file which acts as the root.
     *
     * @return
     */
    public List<String> getRootKey() {
        return first;
    }

    /**
     * Given a key, this returns a list of acceptable words that can follow the key.
     *
     * @param key
     * @return
     */
    public List<String> getList(List<String> key) {
        return trigram.get(StringJoinerUtils.joinTextWithNull(key, StringConstant.ZERO, StringUtils.EMPTY));
    }

    public boolean isContain(List<String> key) {
        return trigram.containsKey(StringJoinerUtils.joinTextWithNull(key, StringConstant.ZERO, StringUtils.EMPTY));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> set : trigram.entrySet()) {
            String[] key = set.getKey().split(StringConstant.ZERO);
            String keyPart = StringJoinerUtils.joinTextWithNull(Arrays.asList(key),
                    StringConstant.BLANK_SPACE,StringConstant.HYPHEN);
            String valuePart = StringJoinerUtils.joinTextWithNull(set.getValue(),
                    StringConstant.COMMA,StringUtils.EMPTY);
            builder.append("\""+keyPart + "\""+"=>[" + valuePart + "]"+StringConstant.NEW_LINE);
        }
        return builder.toString();
    }

}
