package src.com.nsu.rds;

import src.com.nsu.rds.ui.LoginUI;
import src.com.nsu.rds.utils.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        Utils.populateWithDummyData();

        LoginUI.showLoginScreen();
    }
}
