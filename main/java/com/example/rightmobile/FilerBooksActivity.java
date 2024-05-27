package com.example.rightmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class FilerBooksActivity extends AppCompatActivity {
    private EditText editText;
    private List<Book> books;
    private ArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filer_books);

        editText = findViewById(R.id.editTextTextFilterGenre);
        Button button = findViewById(R.id.buttonFilterFilter);
        listView = findViewById(R.id.ListViewFilteredBooks);

        books = new ArrayList<Book>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
        listView.setAdapter(adapter);

        BookDbHelper dbHelper = new BookDbHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genre = editText.getText().toString();
                List<Book> filteredBooks = dbHelper.FilterBookByGenre(genre);
                books.clear();
                books.addAll(filteredBooks);
                adapter.notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Filtered", Toast.LENGTH_SHORT).show();;
            }
        });
    }
}