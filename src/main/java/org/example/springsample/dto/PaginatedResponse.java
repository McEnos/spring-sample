package org.example.springsample.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedResponse<T> {
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private List<T> content;
}
