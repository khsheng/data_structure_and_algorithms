package data_management.entity;

public class Staff extends UserInfo {
    protected  String position;

    public Staff(String userName, String name, int age, String position, String password) {
        super(userName, name, age, "staff", password);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Age: %d, Position: %s", this.id, this.name, this.age, this.position);
    }

}
