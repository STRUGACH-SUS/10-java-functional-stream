package com.example.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<MailItem<T>> {
    private final Map<String, List<T>> mailBox = new HashMap<>() {
        @Override
        public List<T> get(Object key) {
            return super.getOrDefault(key, new ArrayList<>());
        }
    };

    @Override
    public void accept(MailItem<T> item) {
        String to = item.getTo();
        T payload = item.getPayload();

        mailBox.computeIfAbsent(to, k -> new ArrayList<>()).add(payload);
    }

    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }
}