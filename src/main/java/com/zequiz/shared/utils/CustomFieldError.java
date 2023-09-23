package com.zequiz.shared.utils;

import lombok.Data;

@Data
public class CustomFieldError {
    private String fieldname;
    private String errorMessage;

    public CustomFieldError(String fieldname, String errorMessage) {
        this.fieldname = fieldname;
        this.errorMessage = errorMessage;
    }
}
