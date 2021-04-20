package com.joebrooks.whoareyou.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Log {

    private boolean state;
    private Date time;

}
