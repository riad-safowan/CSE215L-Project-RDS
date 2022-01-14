package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.data.models.Fee;
import src.com.nsu.rds.data.models.Student;
import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.utils.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminRepository {
    public static ArrayList<User> users = new ArrayList<>();

    public static void init() {
        users.addAll(List.of(
                new User("a", "1", true, "Developer", "Test", "Admin")
        ));
        setUsers(users);
        setFees(new Fee(6500, 3000, 2500, 1500, 2500, 10));
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> newList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Const.ALL_USER_LIST))) {
            while (true) {
                newList.add((User) inputStream.readObject());
            }
        } catch (EOFException e) {
            return newList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static void setUsers(ArrayList<User> users) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Const.ALL_USER_LIST))) {
            for (User u : users) {
                outputStream.writeObject(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User u) throws Exception {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (Objects.equals(user.getUserId(), u.getUserId()))
                throw new Exception("UserID already exits! ");
        }
        users.add(u);
        setUsers(users);
    }

    public static ArrayList<User> getAdmins() {
        ArrayList<User> allUsers = getUsers();
        ArrayList<User> admins = new ArrayList<>();

        for (User u : allUsers) {
            if (u.isAdmin()) admins.add(u);
        }
        return admins;
    }

    public static ArrayList<User> getStudents() {
        ArrayList<User> allUsers = getUsers();
        ArrayList<User> students = new ArrayList<>();

        for (User u : allUsers) {
            if (!u.isAdmin()) students.add(u);
        }
        return students;
    }

    public static void removeUser(String id) {
        ArrayList<User> list = getUsers();
        int index = 0;
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getUserId(), id)) {
                index = i;
                found = true;
            }
        }
        if (found) {
            list.remove(index);
            System.out.println("removed");
        }
        setUsers(list);
    }

    public static void setFees(Fee fee) {
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_FEES_LIST);
            myWriter.write(fee.getCreditFee() + " " + fee.getActivityFee() + " " + fee.getComputerLabFee() + " " + fee.getLibraryFee() + " " + fee.getScienceLabFee() + " " + fee.getWaiver());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Fee getFees() {
        Fee fee = new Fee();
        try {
            File myObj = new File(Const.ALL_FEES_LIST);
            Scanner myReader = new Scanner(myObj);
            fee = new Fee(myReader.nextDouble(), myReader.nextDouble(), myReader.nextDouble(), myReader.nextDouble(), myReader.nextDouble(), myReader.nextDouble());
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fee;
    }

    public static void updateAdminPassword(String userId, String newP) {
    }
}
