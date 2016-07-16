package com.playground.dkkovalev.testappforwork;

import java.util.ArrayList;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public class Presenter extends AbstractPresenter<View> implements UserFetcher {

    public void showListOfUsers(ArrayList<User> users) {
        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                view.showListOfUsers(users);
            }
        }
    }

    public ArrayList<User> fetchUsers() {
        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                return new NetworkHandler(view).getUser();
            }
        }
        return null;
    }

    public void fetchDetailedInfo(int id) {
        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                NetworkHandler networkHandler = new NetworkHandler(view);
                networkHandler.setUserFetcher(this);
                networkHandler.getDetailedInfo(id);
            }
        }
    }



    public void showToast(String message) {
        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                view.showToast(message);
            }
        }
    }

    public void sendInfo(Info info){
        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                NetworkHandler networkHandler = new NetworkHandler(view);
                networkHandler.sendInfo(info);
                view.sendInfo(info);
            }
        }
    }

    @Override
    public void fetchUser(User user) {

        if (isViewAttached()) {
            View view = getView();
            if (view != null) {
                view.showDetailedInfo(user);
            }
        }
    }
}
