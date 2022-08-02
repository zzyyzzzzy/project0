package dev.zheng.entities;

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

}
