package com.group.libraryapp.dto.user.response;

public class FruitSaleResponse {
    private final int salesAmount;
    private final int notSalesAmount;

    public FruitSaleResponse(int salesAmount, int notSalesAmount) {
        this.salesAmount = salesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public int getNotSalesAmount() {
        return notSalesAmount;
    }
}
