package com.joebrooks.whoareyou.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private int code;
    private String result;
}
