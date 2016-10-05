package com.example.linhndg.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    public static final String TODONO = "toDoNo";

    ArrayList<String> toDoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        final int toDoNo = (Integer)getIntent().getExtras().get(TODONO);
        final EditText editText = (EditText)findViewById(R.id.editText);
        Button button = (Button)findViewById(R.id.SaveBtn);
        toDoList = getIntent().getStringArrayListExtra("ListItem");
        editText.setText(toDoList.get(toDoNo), TextView.BufferType.EDITABLE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               toDoList.set(toDoNo,editText.getText().toString());
                File filesDir = getFilesDir();
                File todoFile = new File(filesDir, "todo.txt");
                try{
                    FileUtils.writeLines(todoFile, toDoList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
    });
    }
}
