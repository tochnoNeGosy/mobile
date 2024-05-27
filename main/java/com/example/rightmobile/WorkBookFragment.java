package com.example.rightmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WorkBookFragment extends Fragment {

    private static final String ARG_PARAM = "book";

    private Book book;
    private EditText editTextAuthor;
    private EditText editTextGenre;
    private EditText editTextQuantity;

    public WorkBookFragment() {

    }

    public static WorkBookFragment newInstance(Book book) {
        WorkBookFragment fragment = new WorkBookFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_book, container, false);

        editTextAuthor = view.findViewById(R.id.editTextTextAuthor);
        editTextGenre = view.findViewById(R.id.editTextTextGenre);
        editTextQuantity = view.findViewById(R.id.editTextTextQuantity);
        Button button = view.findViewById(R.id.buttonAddUpdateBook);

        if (book != null){
            editTextAuthor.setText(book.getAuthor());
            editTextGenre.setText(book.getGenre());
            editTextQuantity.setText(String.valueOf(book.getQuantity()));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = editTextAuthor.getText().toString();
                String genre = editTextGenre.getText().toString();
                int quantity = Integer.parseInt(editTextQuantity.getText().toString());

                BookDbHelper dbHelper = new BookDbHelper(getActivity());
                FragmentManager manager = getActivity().getSupportFragmentManager();
                if(book != null){
                    Book newBook = new Book(book.getId(), author, genre, quantity);
                    dbHelper.UpdateBook(newBook);
                    Toast.makeText(getActivity(), "Book updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Book newBook = new Book(author, genre, quantity);
                    dbHelper.AddBook(newBook);
                    Toast.makeText(getActivity(), "Book added", Toast.LENGTH_SHORT).show();
                }
                manager.popBackStack();
            }
        });

        return view;
    }
}