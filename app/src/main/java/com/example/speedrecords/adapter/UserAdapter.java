package com.example.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedrecords.R;
import com.example.speedrecords.model.User;

import org.w3c.dom.Text;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;
    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        holder.resultTextView.setText(user.result+" KM/H");
        holder.distanceandtimeTextView.setText(user.distance+" METERS, "+user.time+" SECONDS");
        if(Double.parseDouble(user.result) > 80){
            holder.pig.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView resultTextView;
        TextView distanceandtimeTextView;
        ImageView pig;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.resultTextView = itemView.findViewById(R.id.result_textView);
            this.distanceandtimeTextView = itemView.findViewById(R.id.distanceandtime_textView);
            this.pig = itemView.findViewById(R.id.pig_imageView);
        }
    }
}
