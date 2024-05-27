package com.example.rightmobile;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String author;
    private String genre;
    private int quantity;

    public Book(int id, String author, String genre, int quantity){
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }
    public Book(String author, String genre, int quantity){
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Author:" + author +
                " Genre:" + genre +
                " Quantity:" + String.valueOf(quantity);
    }
}
