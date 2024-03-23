package com.group.libraryapp.dto.fruit.response;

public class FruitSaleResponse {
    private int salesAmount;
    private int notSalesAmount;

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
