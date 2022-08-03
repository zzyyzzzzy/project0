package dev.zheng.entities;

import java.util.Objects;

public class Expense {
    // new enum for type - optional
    private int id;
    private int employeeId;
    private double amount;
    private Status status;
    private  String description;

    public Expense(int id, int employeeId, double amount, Status status, String description) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployee(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", employee=" + employeeId +
                ", amount=" + amount +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return employeeId == expense.employeeId && Double.compare(expense.amount, amount) == 0
                && status == expense.status && description.equals(expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, amount, status, description);
    }
}
