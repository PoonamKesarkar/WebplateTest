package com.webplate.activity;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webplate.R;
import com.webplate.model.UserData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class UserAdapter  extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<UserData> userList;
    private Context context;
    public UserAdapter(Context context, ArrayList<UserData> userlist) {
        this.userList = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(contentView);

    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, int i) {
       holder.txtFirstLetter.setText(String.valueOf(userList.get(i).getName().charAt(0)));
        holder.txtname.setText(userList.get(i).getName());
        holder.txtUserName.setText(userList.get(i).getUsername());
        holder.txtEmail.setText(userList.get(i).getEmail());
        holder.txtPhone.setText(userList.get(i).getPhone());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.txtFirstLetter.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


   public class ViewHolder extends RecyclerView.ViewHolder {
       @BindView(R.id.txtfirstletter)
       TextView txtFirstLetter;
       @BindView(R.id.txtname)
       TextView txtname;
       @BindView(R.id.txtUserName)
       TextView txtUserName;
       @BindView(R.id.txtEmail)
       TextView txtEmail;
       @BindView(R.id.txtPhone)
       TextView txtPhone;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
       }
   }

}
