package com.myboard.learning2.dto;

public class P_JPQL_EX2_DTO2 {
    private int productCode;
    private String optionCode1;
    private String optionCode2;
    private String productName;
    private int totalSales;

    // Constructor
    public P_JPQL_EX2_DTO2(int productCode, String optionCode1, String optionCode2, String productName,
            int totalSales) {
        this.productCode = productCode;
        this.optionCode1 = optionCode1;
        this.optionCode2 = optionCode2;
        this.productName = productName;
        this.totalSales = totalSales;
    }

    // Getter /  Setter
    public int getProductCode() {
        return productCode;
    }
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }
    public String getOptionCode1() {
        return optionCode1;
    }
    public void setOptionCode1(String optionCode1) {
        this.optionCode1 = optionCode1;
    }
    public String getOptionCode2() {
        return optionCode2;
    }
    public void setOptionCode2(String optionCode2) {
        this.optionCode2 = optionCode2;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
    
    // ToString
    @Override
    public String toString() {
        return "P_JPQL_EX2_DTO2 [productCode=" + productCode + ", optionCode1=" + optionCode1 + ", optionCode2="
                + optionCode2 + ", productName=" + productName + ", totalSales=" + totalSales + "]";
    }
}
