package com.google.engedu.wordladder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.engedu.worldladder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajacob2609 on 6/19/2017.
 */

public class SolverActivity extends AppCompatActivity {
    String[] words;
    List<EditText> allEds = new ArrayList<EditText>();
    int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_solver);
        Intent intent = getIntent();
        words = intent.getStringArrayExtra("strings");
        TextView startWordView = (TextView) findViewById(R.id.startTextView);
        TextView endWordView = (TextView) findViewById(R.id.endTextView);
        startWordView.setText("PLAY");
        //endWordView.setText(words[words.length-1].toString().replace("[", "").replace("]", ""));
        no = words.length - 2;
        EditText edttext;
        TextView tv = new TextView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setText(words[0]);
        int j=0;
        tv.setId(j);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,34);
        tv.setTextColor(Color.parseColor("#000000"));
        layout.addView(tv);
        j++;
        for (int i = 0; i < no; ++i) {




            edttext = new EditText(this);
            allEds.add(edttext);
            edttext.setId(i);
            edttext.setLayoutParams(params);
            edttext.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            layout.addView(edttext);
        }
        tv = new TextView(this);
        tv.setId(j);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,34);
        tv.setText(words[words.length-1]);
        tv.setTextColor(Color.parseColor("#000000"));
        layout.addView(tv);

        final Button button = (Button) findViewById(R.id.solveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for (int i = 0; i < no; ++i)
                    allEds.get(i).setText(words[i + 1]);
            }
        });

        final String[] strings = new String[words.length];
        final PathDictionary obj=new PathDictionary();
        final Button checkButton = (Button) findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                for (int i = 0; i < no; ++i)
                    strings[i+1] = allEds.get(i).getText().toString();
                strings[0]=words[0];
                strings[no+1]=words[words.length-1];
                for(int i=1;i<=no;++i)
                    if(obj.isWord(strings[i])&& obj.neighbours(strings[i]).contains(strings[i-1]) )
                        allEds.get(i-1).setTextColor(Color.parseColor("#00ff00"));
                    else
                        allEds.get(i-1).setTextColor(Color.parseColor("#ff0000"));

            }
        });
    }
}
