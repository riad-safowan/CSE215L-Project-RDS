package src.com.nsu.rds;

import src.com.nsu.rds.ui.LoginUI;
import src.com.nsu.rds.utils.Utils;

public class Main {

    public static void main(String[] args) {

        Utils.initializeWithDummyData();

        LoginUI.showLoginScreen();
    }
}
