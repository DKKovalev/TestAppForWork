package com.playground.dkkovalev.testappforwork;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements com.playground.dkkovalev.testappforwork.View, CustomRecyclerAdapter.OnRecyclerItemClickListener, View.OnClickListener {

    private static final int PERMISSION_REQUEST_READ_PHONE_STATE = 1;
    private RecyclerView recyclerView;
    private Button buttonFetch;
    private Button buttonDetails;

    private Presenter presenter;

    private ArrayList<User> users;

    private int id = 0;

    public static ListFragment newInstance() {
        ListFragment listFragment = new ListFragment();
        return listFragment;
    }

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setupUI(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (presenter == null) {
            presenter = new Presenter();
            presenter.attachView(this);
        }

        grantPermission();

        //sendData();
    }

    @Override
    public void showListOfUsers(ArrayList<User> users) {

        CustomRecyclerAdapter customRecyclerAdapter = new CustomRecyclerAdapter(users);
        customRecyclerAdapter.setOnRecyclerItemClickListener(ListFragment.this);

        recyclerView.setAdapter(customRecyclerAdapter);
    }

    @Override
    public void showDetailedInfo(User user) {
        FragmentManager fragmentManager = getFragmentManager();
        InfoDialogFragment infoDialogFragment = InfoDialogFragment.newInstance(user);
        infoDialogFragment.show(fragmentManager, "Info");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendInfo(Info info) {

    }


    @Override
    public void onClick(View view, int pos) {
        id = pos + 1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fetch:

                users = presenter.fetchUsers();
                presenter.showListOfUsers(users);

                break;
            case R.id.button_details:

                if (id != 0) {
                    presenter.fetchDetailedInfo(id);
                } else {
                    presenter.showToast("Select user");
                }

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendData();
                } else {
                    presenter.showToast("Cannot send data");
                }
                return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setupUI(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buttonFetch = (Button) view.findViewById(R.id.button_fetch);
        buttonFetch.setOnClickListener(this);

        buttonDetails = (Button) view.findViewById(R.id.button_details);
        buttonDetails.setOnClickListener(this);

    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_PHONE_STATE)) {

            } else {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_PHONE_STATE);
            }
        }
    }


    private void sendData() {
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        Info info = new Info();
        info.setMessage("Hello world");
        info.setUuid(telephonyManager.getDeviceId());

        presenter.sendInfo(info);

    }
}
