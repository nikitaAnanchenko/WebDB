package model;

import java.util.List;

public class Seller implements Identifiable{
    private int id;
    private String sellerName;
    private List<Positions> position;
    private String phone;
    private String address;
    private String gender;

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public String getSellerName(){return sellerName;}
    public void setSellerName(String sellerName) {this.sellerName = sellerName;}

    public List<Positions> getPosition() {return position;}
    public void setPosition(List<Positions> position) {this.position = position;}

    public String getPhone(){return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getAddress(){return address;}
    public void setAddress(String address) {this.address = address;}

    public String getGender(){return gender;}
    public void setGender(String gender) {this.gender = gender;}
}
