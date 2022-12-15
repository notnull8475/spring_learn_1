package ru.gb.SpringOne.exceptions;

import lombok.Data;

import java.util.List;

@ Data
public class ValidationException extends RuntimeException {
    private List<String> errorsFieldsMessages;

    public ValidationException(List<String> errorsFieldsMessages) {
        super(String.join(",", errorsFieldsMessages));
        this.errorsFieldsMessages = errorsFieldsMessages;
    }
}
