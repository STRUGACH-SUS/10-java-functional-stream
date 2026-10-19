package com.example.task05;

public interface MailItem<T> {
    String getTo();
    T getPayload();
}