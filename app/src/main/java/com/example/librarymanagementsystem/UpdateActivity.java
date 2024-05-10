package com.example.librarymanagementsystem;

import static com.example.librarymanagementsystem.MyDatabaseHelper.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
 EditText title_input, author_input;
 Button update_Button,delete_Button;
 String id,title,author;
    private Context context;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        update_Button = findViewById(R.id.Update_Button);
        delete_Button = findViewById(R.id.delete_Button);

        update_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, title, author);
            }
        });
        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getandSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
       getIntent().hasExtra("author") ){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");

            //setting intent data
            title_input.setText(title);
            author_input.setText(author);
        }else{

            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();

        }
    }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete ALl?");
            builder.setMessage("Are you sure u want to delete all data");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(InfoActivity.this);
                    myDB.deleteAll();
                    //refresh activity
                    Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                }
            });
            builder.create().show();
        }
}