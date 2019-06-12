package com.openbank.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ErrorDetails {

    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String developerMessage;
}

