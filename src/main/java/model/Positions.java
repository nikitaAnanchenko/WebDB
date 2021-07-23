package model;

public class Positions implements Identifiable {
    private int id;
    private String namePosition;
    private int salary;

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public String getNamePosition(){return namePosition;}
    public void setNamePosition(String namePosition) {this.namePosition = namePosition;}

    public int getSalary(){return salary;}
    public void setSalary(int salary) {this.salary = salary;}

}
