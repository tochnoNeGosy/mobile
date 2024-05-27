package com.example.rightmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonList = findViewById(R.id.buttonListtBooks);
        Button buttonWork = findViewById(R.id.buttonAddBook);
        Button buttonFilter = findViewById(R.id.buttonFilterBook);

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListBookFragment listBookFragment = new ListBookFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, listBookFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        buttonWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkBookFragment workBookFragment = new WorkBookFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, workBookFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent intent = new Intent(v.getContext(), FilerBooksActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    void UpdateBook(Book book){
        WorkBookFragment workBookFragment = WorkBookFragment.newInstance(book);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView, workBookFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}