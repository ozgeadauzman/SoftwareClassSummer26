package org.firstinspires.ftc.teamcode.FRQs;

public class Account {

    private String username;

    public Account(String requestedName) {
        if (isAvailable(requestedName)) {
            username = requestedName;
        }
    }


    public static boolean isAvailable(String str) {
        //finish this later but for now:
        return false;
    }


    public String getShortenedName() {
        //finish this later but for now:
        return ".";
    }
}
