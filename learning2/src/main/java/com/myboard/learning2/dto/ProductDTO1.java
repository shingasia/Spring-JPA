package com.myboard.learning2.dto;

public class ProductDTO1 {
    private String name;
    private int cnt1;
    private int cnt2;

    public ProductDTO1(String name, int cnt1, int cnt2) {
        this.name = name;
        this.cnt1 = cnt1;
        this.cnt2 = cnt2;
    }

    // Getter / Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCnt1() {
        return cnt1;
    }
    public void setCnt1(int cnt1) {
        this.cnt1 = cnt1;
    }
    public int getCnt2() {
        return cnt2;
    }
    public void setCnt2(int cnt2) {
        this.cnt2 = cnt2;
    }
    // ToString
    @Override
    public String toString() {
        return "ProductDTO1 [name=" + name + ", cnt1=" + cnt1 + ", cnt2=" + cnt2 + "]";
    }
}
