package com.playground.dkkovalev.testappforwork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private ArrayList<User> users;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private int selectedPosition = -1;

    public CustomRecyclerAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resycler_item, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.textView.setText(user.getName() + " " + user.getSurname());
        holder.textView.setSelected(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.user_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onRecyclerItemClickListener!=null){
                onRecyclerItemClickListener.onClick(view, getAdapterPosition());
            }
            selectedPosition = getLayoutPosition();
            notifyItemChanged(selectedPosition);
        }
    }

    public interface OnRecyclerItemClickListener {
        void onClick(View view, int pos);
    }
}
