package dev.zheng.entities;

public class Employee {
    private int id;
    private String fname;
    private String lname;
    private long dob;
    private String title;
    private String department;

    public Employee(int id, String fname, String lname, long dob, String title, String department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.title = title;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
