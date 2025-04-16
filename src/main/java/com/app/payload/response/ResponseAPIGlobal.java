package com.app.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIGlobal {
    Integer status;
    String message;
    Object data;
    String error;

}
