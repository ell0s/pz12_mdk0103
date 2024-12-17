package com.example.pz12_mdk0103;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText ageInput;
    private TextView userInfo;
    private ImageView userImage;
    private ArrayList<User> userList;
    private ArrayAdapter<String> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        userInfo = findViewById(R.id.userInfo);
        ListView userListView = findViewById(R.id.userList);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);

        userList = new ArrayList<>();
        ArrayList<String> userNames = new ArrayList<>();
        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNames);
        userListView.setAdapter(userAdapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                int age = Integer.parseInt(ageInput.getText().toString());
                User newUser = new User(name, age);

                userList.add(newUser);
                userNames.add(name);
                userAdapter.notifyDataSetChanged();


            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userList.isEmpty()) {
                    User lastUser = userList.get(userList.size() - 1);
                    String info = "Имя: " + lastUser.getName() + "\nВозраст: " + lastUser.getAge();
                    userInfo.setText(info);


                } else {
                    userInfo.setText("Нет сохраненных данных!");
                }
            }
        });

        userListView.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = userList.get(position);
            String info = "Имя: " + selectedUser.getName() + "\nВозраст: " + selectedUser.getAge();
            userInfo.setText(info);

        });
    }
}


