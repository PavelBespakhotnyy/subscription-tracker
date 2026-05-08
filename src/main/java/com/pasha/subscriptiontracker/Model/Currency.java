package com.pasha.subscriptiontracker.Model;

public class Currency {
    private Integer id;
    private String code;
    private String symbol;

    public Currency() {
    }

    public Currency(Integer id, String code, String symbol) {
        this.id = id;
        this.code = code;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
