package com.playground.dkkovalev.testappforwork;

import java.util.ArrayList;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public interface View {
    void showListOfUsers(ArrayList<User> users);

    void showDetailedInfo(User user);

    void showToast(String message);

    void sendInfo(Info info);
}
