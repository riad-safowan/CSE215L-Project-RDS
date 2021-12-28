package src.com.nsu.rds.data.repositories;

import src.com.nsu.rds.models.User;
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
    private ArrayList<User> users = new ArrayList<>();

    void init() {
        users.addAll(List.of(
                new User("riad", "12345", true),
                new User("abir", "12345", true),
                new User("rubayet", "12345", false)
        ));
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> newList = new ArrayList<>();
        try {
            File myObj = new File(Const.ALL_USER_LIST);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNext()) {
                newList.add(new User(myReader.next(), myReader.next(), !Objects.equals(myReader.next(), "false")));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newList;
    }

    public void setUsers(ArrayList<User> users) {
//                init();
        try {
            FileWriter myWriter = new FileWriter(Const.ALL_USER_LIST);
            for (User u : users) {
                myWriter.write(u.getName() + " " + u.getPassword() + " "+u.isAdmin()+"\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}