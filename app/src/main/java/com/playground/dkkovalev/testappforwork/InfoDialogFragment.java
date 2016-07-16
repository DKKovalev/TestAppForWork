package com.playground.dkkovalev.testappforwork;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public class InfoDialogFragment extends DialogFragment {

    public static InfoDialogFragment newInstance(User user) {
        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        infoDialogFragment.setArguments(args);
        return infoDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_info, null);
        setupUI(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setupUI(View view) {

        User user = (User)getArguments().getSerializable("user");

        TextView name = (TextView)view.findViewById(R.id.user_name_info);
        TextView createdAt = (TextView)view.findViewById(R.id.user_created);
        TextView info = (TextView)view.findViewById(R.id.user_info);

        name.setText("User: " + user.getName() + " " + user.getSurname());
        createdAt.setText("Created at: " + user.getCreated_at());
        info.setText("Info: " + user.getInfo());
    }
}
