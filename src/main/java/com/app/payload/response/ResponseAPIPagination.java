package com.app.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIPagination {
    Long totalItem; // 1000
    Integer totalPage; // 100
    Integer currentPage; // 1
    Integer status;
    String message;
    Object data; // 10 record dau
    String error;
}


// api get product
// duoi database 1000 product
// chi 10 product
