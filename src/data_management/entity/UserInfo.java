package data_management.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;

public abstract class UserInfo {
    private static int counter = 1;

    protected int id;
    protected String name;
    protected int age;
    protected String role;
    protected LocalDate registrationDate;
    protected boolean isBlacklisted = false;
    protected String hashedPassword;

    public UserInfo(String name, int age, String role, String password) {
        this.id = counter;
        this.name = name;
        this.age = age;
        this.role = role;
        this.registrationDate = LocalDate.now();

        this.hashedPassword = PasswordUtils.hashPassword(password);

        counter++;
    }

    // Getters
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getAge() { return this.age; }
    public String getRole() { return this.role; }
    public LocalDate getRegistrationDate() { return this.registrationDate; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    // Password management
    public void resetPassword(String newPassword) {
        this.hashedPassword = PasswordUtils.hashPassword(newPassword);
    }

    public boolean checkPassword(String plainPassword) {
        return PasswordUtils.checkPassword(plainPassword, this.hashedPassword);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Age: %d, Role: %s", 
                              this.id, this.name, this.age, this.role);
    }

    // Utility class for password hashing
    private class PasswordUtils {
        private static String hashPassword(String plainPassword) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = md.digest(plainPassword.getBytes());
                return Base64.getEncoder().encodeToString(hashBytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        private static boolean checkPassword(String plainPassword, String hashedPassword) {
            return hashPassword(plainPassword).equals(hashedPassword);
        }
    }
}


