package com.group.libraryapp.dto.fruit.request;

import java.time.LocalDate;

public class FruitCreateRequest {
    private String name;
    private LocalDate warehousingDate;
    private Long price;

    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public Long getPrice() {
        return price;
    }
}
