package com.example.rightmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class ListBookFragment extends Fragment {
    private ListView listView;
    ArrayAdapter adapter;
    List<Book> books;

    public ListBookFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_book, container, false);

        BookDbHelper dbHelper = new BookDbHelper(getActivity());
        listView = view.findViewById(R.id.ListViewAllBooks);
        books = dbHelper.GetAllBooks();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, books);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = books.get(position);
                try {
                    ((MainActivity)getActivity()).UpdateBook(book);
                }
                catch (Exception ex){
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        return view;
    }
}