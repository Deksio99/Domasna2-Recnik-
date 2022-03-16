package com.example.dictionaryapk;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    TextView tv_text;
    private static final String[] MKD = new String[] {
            "Јac", "I", "Јас сум", "I am","Ние двајцата", "Both of us", "Тој", "He",
            "Тој и таа", "He and she", "Тие двајцата", "They both", "Маж", "Man",
            "Жена", "Woman", "Дете", "Child", "Една фамилија","A family","Мојата фамилија", "My family",
            "Mојата фамилија е овде","Му family is here","Ти си овде", "You are here",
            "Tоj е овде и таа е овде", "Не is here and she is here", "Ние сме овде", "We are here",
            "Вие сте овде", "You are here","Тие сите се овде","They are all here"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView word = findViewById(R.id.word);
        //EditText word = findViewById(R.id.word);
        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MKD);
        word.setAdapter(adapter);

        tv_text = (TextView) findViewById(R.id.tv_text);
        String text ="";
        try {
            InputStream is = getAssets().open("eng.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            }
        tv_text.setText(text);

            }



    public void onLookUp(View view) {
        EditText word = findViewById(R.id.word);
        String theword = word.getText().toString();
        String defeition = findViewById(theword);
        TextView thedef = findViewById(R.id.tv_text);

        if (defeition!=null)
            thedef.setText(defeition);
        else thedef.setText("Word not found");
    }

    private String findViewById(String theword) {
        InputStream input = getResources().openRawResource(R.raw.mkd);

        Scanner scan = new Scanner(input);
        while(scan.hasNext()){
            String line = scan.nextLine().trim();
            theword = theword.trim();
            String[] pieces = line.split(" - ");

            if (pieces[0].equalsIgnoreCase(theword)){
                  return pieces[1];
            }
                else if (pieces[1].equalsIgnoreCase(theword)){
                return pieces[0];
                }
            }
        return null;
    }
}
