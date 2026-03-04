package data_management.entity;

public abstract class UserInfo {
    public static int counter = 1;

    public int id;
    public String name;
    public int age;
    public String role;

    public UserInfo(String name, int age, String role) {
        this.id = counter;
        this.name = name;
        this.age = age;
        this.role = role;
        counter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void main(String[] args) {
        System.out.println("abc");
    }
}


