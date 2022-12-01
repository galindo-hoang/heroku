package com.example.backend.common.search;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseSearchCriteria {
    private int pageIndex;
    private int pageSize;
    private String orderBy;
    private Boolean orderAsc;
    private Boolean paginated;
}
