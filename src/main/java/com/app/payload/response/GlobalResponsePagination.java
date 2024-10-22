package com.app.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponsePagination {
    Long totalItem;
    Integer totalPage;
    Integer currentPage;
    Object data;
}
