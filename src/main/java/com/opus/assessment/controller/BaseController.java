package com.opus.assessment.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.opus.assessment.utils.AppConstant.*;

public abstract class BaseController {
    public static final Instant TIMESTAMP = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();


    public ResponseEntity<?> getResponse(String statusType, String message, HttpStatus status) {
        if (statusType.equals(ERROR))
            return ResponseEntity.status(status).body(
                    ImmutableMap.of(statusType, ImmutableMap.of(STATUS, status, MESSAGE, message)));

        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS, status, STATUS_TYPE, statusType, MESSAGE, message));

    }
    protected ResponseEntity<?> getResponse(String statusType, Object data, HttpStatus status) {
        if (statusType.equals(ERROR))
            return ResponseEntity.status(status).body(
                    ImmutableMap.of(statusType, ImmutableMap.of(STATUS, status,  DATA, data)));

        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS_TYPE, statusType, STATUS, status, DATA, data));
    }
}