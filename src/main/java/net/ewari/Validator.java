package net.ewari;

import java.util.ArrayList;

public class Validator {


    public boolean validate(User data, User attempt) {
        // very simple and clean way of checking if usernames and passwords match
        return data.getUsername().equals(attempt.getUsername()) &&
                data.getPassword().equals(attempt.getPassword());
    }

    public boolean validate(ArrayList<User> dataList, User attempt) {
        // a bit more advanced version of validate that checks it by querying a list
        boolean isValid = false;
        for (User data : dataList) {
            if (data.getUsername().equals(attempt.getUsername()) &&
                    data.getPassword().equals(attempt.getPassword())) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
