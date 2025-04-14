package com.athar.food_reservation.common;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int size;
    private int number;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;

}
