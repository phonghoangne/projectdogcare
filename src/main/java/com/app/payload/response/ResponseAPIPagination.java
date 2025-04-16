package com.app.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIPagination {
    Long totalItem;
    Integer totalPage;
    Integer currentPage;
    Integer status;
    String message;
    Object Data;
    String error;

}
