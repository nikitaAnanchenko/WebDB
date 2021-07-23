package model;

import java.sql.*;

public class Book implements Identifiable {
    private int id;
    private String bookName;
    private String author;
    private int price;
    private Date releaseDate;

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public String getBookName(){return bookName;}
    public void setBookName(String bookName) {this.bookName = bookName;}

    public String getAuthor(){return author;}
    public void setAuthor(String author) {this.author = author;}

    public int getPrice(){return price;}
    public void setPrice(int price) {this.price = price;}


    public Date getDate() {return releaseDate;}
    public void setDate(Date releaseDate) {this.releaseDate = releaseDate;}

}
