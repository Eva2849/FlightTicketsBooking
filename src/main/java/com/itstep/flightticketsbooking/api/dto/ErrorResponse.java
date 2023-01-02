package com.itstep.flightticketsbooking.api.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String status = "error";
}
