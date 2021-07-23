package model;

import java.sql.*;
import java.util.List;

public class Orders implements Identifiable {
    private int id;
    private List<Book> book;
    private List<Seller> seller;
    private int purchaseVolume;
    private Timestamp date;

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public List<Book> getBook() {return book;}
    public void setBook(List<Book> book) {this.book = book;}

    public List<Seller> getSeller() {return seller;}
    public void setSeller(List<Seller> seller) {this.seller = seller;}

    public int getPurchaseVolume(){return purchaseVolume;}
    public void setPurchaseVolume(int purchaseVolume) {this.purchaseVolume = purchaseVolume;}

    public Timestamp getDate() {return date;}
    public void setDate(Timestamp date) {this.date = date;}
}