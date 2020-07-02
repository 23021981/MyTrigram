package org.test.assignment.process;

import org.test.assignment.common.StringConstant;
import org.test.assignment.utils.StringJoinerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Author : Atul Kumar
 * */
public class Processor {

    private KeyGenerator store;

    public Processor(KeyGenerator store) {
        this.store = store;
    }

    public String processText() {
        StringBuilder text = new StringBuilder();
        List<String> last = new ArrayList<>();
        last.addAll(store.getRootKey());
        text.append(StringJoinerUtils.joinText(last, StringConstant.BLANK_SPACE) + StringConstant.BLANK_SPACE);
        while (store.isContain(last)) {
            List<String> options = store.getList(last);
            int which = (int)(Math.random() * options.size());
            String word = options.get(which);
            text.append(word + StringConstant.BLANK_SPACE);
            last.remove(0);
            last.add(word);
        }
        return text.toString();
    }

}
