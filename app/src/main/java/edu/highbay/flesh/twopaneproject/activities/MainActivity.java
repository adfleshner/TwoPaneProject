package edu.highbay.flesh.twopaneproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.highbay.flesh.twopaneproject.R;
import edu.highbay.flesh.twopaneproject.fragments.LeftFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new LeftFragment()).commit();
    }
}
