package data_management.entity;

import java.time.LocalDate;

public abstract class UserInfo {
    private static int counter = 1;

    protected int id;
    protected String name;
    protected int age;
    protected String role;
    protected LocalDate registerationData;
    protected boolean isBlacklisted = false;

    public UserInfo(String name, int age, String role) {
        this.id = counter;
        this.name = name;
        this.age = age;
        this.role = role;
        this.registerationData = LocalDate.now();
        counter++;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getRole() {
        return this.role;
    }

    public LocalDate getRegisterationData(){
        return this.registerationData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Age: %d, Role: %s", this.id, this.name, this.age, this.role);
    }
}