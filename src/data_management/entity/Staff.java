package data_management.entity;

public class Staff extends UserInfo {
    protected  String position;

    public Staff(String name, int age, String position) {
        super(name, age, "staff");
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static void main(String[] args) {
        System.out.println("abc");
    }
}
