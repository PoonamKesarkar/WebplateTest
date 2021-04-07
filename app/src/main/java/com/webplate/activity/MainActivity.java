package com.webplate.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.webplate.R;
import com.webplate.database.DatabaseClient;
import com.webplate.model.UserData;
import com.webplate.util.CommonMethods;
import com.webplate.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.webplate.util.Constant.INTERNET_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerUserList)
    RecyclerView recyclerUserList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    UserViewModel userModel;
    String TAG = this.getClass().getName();
    private ArrayList<UserData> userlist;
    private UserAdapter adapter;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("User List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        userlist = new ArrayList<>();
        progressBar = new ProgressDialog(this);
        adapter = new UserAdapter(this, userlist);
        LinearLayoutManager verticalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerUserList.setLayoutManager(verticalLayoutManagaer);
        recyclerUserList.setAdapter(adapter);
        userModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);
        if (CommonMethods.isInternetConnection(this)) {
            loadUserApiData();
        } else {
           // getUserData();
            userModel.concertViewModel(DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .userDao()).observe(this, new Observer<PagedList<UserData>>() {
                @Override
                public void onChanged(PagedList<UserData> list) {
                    if (list.size() > 0) {
                        userlist.clear();
                        userlist.addAll(list);
                        adapter.notifyDataSetChanged();
                    } else {
                        Intent i = new Intent(MainActivity.this, InternetActivity.class);
                        startActivityForResult(i, INTERNET_REQUEST_CODE);
                    }
                }
            });

        }
    }

    private void loadUserApiData() {
        userModel.getUserData();
        userModel.error().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "error aBoolean " + aBoolean);
                if (aBoolean) {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, InternetActivity.class);
                    startActivityForResult(i, INTERNET_REQUEST_CODE);
                }


            }
        });
        userModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "isLoading aBoolean " + aBoolean);
                if(aBoolean){
                    progressBar.setMessage("Loading");
                    progressBar.show();
                }else{
                    progressBar.dismiss();
                }

            }
        });
        userModel.userDataReceived().observe(this, new Observer<ArrayList<UserData>>() {
            @Override
            public void onChanged(ArrayList<UserData> userList) {
                if (userList != null) {
                    userlist.addAll(userList);
                }
                adapter.notifyDataSetChanged();
                if (userList.size() != 0)
                    isertListOnDatabase();
            }
        });

    }

    private void isertListOnDatabase() {
        class SaveData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {


                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(userlist);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_LONG).show();
            }
        }
        SaveData st = new SaveData();
        st.execute();
    }

    private void getUserData() {
        class GetUserList extends AsyncTask<Void, Void, List<UserData>> {

            @Override
            protected List<UserData> doInBackground(Void... voids) {
                List<UserData> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAllData();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<UserData> list) {
                super.onPostExecute(list);
                if (list.size() > 0) {
                    userlist.clear();
                    userlist.addAll(list);
                    adapter.notifyDataSetChanged();
                } else {
                    Intent i = new Intent(MainActivity.this, InternetActivity.class);
                    startActivityForResult(i, INTERNET_REQUEST_CODE);
                }
            }
        }

        GetUserList gt = new GetUserList();
        gt.execute();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTERNET_REQUEST_CODE) {
            loadUserApiData();
        }
    }

}