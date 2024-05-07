package com.myboard.learning2.dto;

public class P_JPQL_EX2_DTO1 {
    private int productCode;
    private int cnt;
    private int optionCnt;
    private int minPrice;
    private int maxPrice;

    // Constructor
    public P_JPQL_EX2_DTO1(int productCode, int cnt, int optionCnt, int minPrice, int maxPrice) {
        this.productCode = productCode;
        this.cnt = cnt;
        this.optionCnt = optionCnt;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    // Getter / Setter

    public int getProductCode() {
        return productCode;
    }
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }
    public int getCnt() {
        return cnt;
    }
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    public int getOptionCnt() {
        return optionCnt;
    }
    public void setOptionCnt(int optionCnt) {
        this.optionCnt = optionCnt;
    }
    public int getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }
    public int getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    // ToString
    @Override
    public String toString() {
        return "P_JPQL_EX2_DTO1 [productCode=" + productCode + ", cnt=" + cnt + ", optionCnt=" + optionCnt
                + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
    }
}
