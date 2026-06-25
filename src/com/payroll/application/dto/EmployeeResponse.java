package com.payroll.application.dto;

public record EmployeeResponse(String name, String role) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String role;

        public Builder name(String name) { this.name = name; return this; }
        public Builder role(String role) { this.role = role; return this; }
        public EmployeeResponse build() { return new EmployeeResponse(name, role); }
    }
}
