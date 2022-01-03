package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.data.models.User;
import src.com.nsu.rds.utils.Const;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserRepository {
    public static ArrayList<User> users = new ArrayList<>();

    public static void init() {
        users.addAll(List.of(
                new User("2112312", "1", true, "Developer", "Test", "Admin"),
                new User("2112313", "1", false, "Developer", "Test", "User")
        ));
        setUsers(users);
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_USER_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new User(myReader.next(), myReader.next(), !Objects.equals(myReader.next(), "false"), myReader.next(), myReader.next(), myReader.next()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public static void setUsers(ArrayList<User> users) {
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_USER_LIST);
            for (User u : users) {
                myWriter.write(u.getUserId() + " " + u.getPassword() + " " + u.isAdmin() + " " + u.getAddedBy() + " " + u.getFullName() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User u) throws Exception {
        ArrayList<User> users = getUsers();
        for (User user: users){
            if (Objects.equals(user.getUserId(), u.getUserId()))
                throw new Exception("UserID already exits! ");
        }
        users.add(u);
        setUsers(users);
    }
}
