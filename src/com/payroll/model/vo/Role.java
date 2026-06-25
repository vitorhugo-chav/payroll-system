package com.payroll.model.vo;

public record Role(String value) {
    public Role {
        value = value.trim();
    }
}
