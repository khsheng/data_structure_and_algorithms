package data_management.entity;

public abstract class UserInfo {
    public static int counter = 1;

    private int id;
    private String name;
    private int age;
    private String role;

    public UserInfo(String name, int age, String role) {
        this.id = counter;
        this.name = name;
        this.age = age;
        this.role = role;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        System.out.println("abc");
    }
}


