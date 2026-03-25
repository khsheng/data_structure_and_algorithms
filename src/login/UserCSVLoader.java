package login;

import java.io.BufferedReader;
import java.io.FileReader;
import data_management.entity.*;
import data_management.service.UserDataService;

public class UserCSVLoader {

    public static void load(String filePath, UserDataService userService) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String role = data[0];
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String extra = data[3];

                if (role.equalsIgnoreCase("student")) {
                    userService.add(new Student(name, age, extra));

                } else if (role.equalsIgnoreCase("staff")) {
                    userService.add(new Staff(name, age, extra));
                }
            }

        } catch (Exception e) {
            System.out.println("CSV Error: " + e.getMessage());
        }
    }
    public static void saveAll(String filePath, data_management.service.UserDataService userService) {

    try (java.io.FileWriter fw = new java.io.FileWriter(filePath)) {

        // write header
        fw.write("role,name,age,extra\n");

        for (int i = 0; i < data_management.service.UserDataService.userList.len(); i++) {

            data_management.entity.UserInfo user = data_management.service.UserDataService.userList.get(i);

            String line = "";

            if (user instanceof data_management.entity.Student) {
                data_management.entity.Student s = (data_management.entity.Student) user;
                line = "student," + s.getName() + "," + s.getAge() + "," + s.getProgram();

            } else if (user instanceof data_management.entity.Staff) {
                data_management.entity.Staff s = (data_management.entity.Staff) user;
                line = "staff," + s.getName() + "," + s.getAge() + "," + s.getPosition();
            }

            fw.write(line + "\n");
        }

    } catch (Exception e) {
        System.out.println("CSV Save Error: " + e.getMessage());
    }
}
}