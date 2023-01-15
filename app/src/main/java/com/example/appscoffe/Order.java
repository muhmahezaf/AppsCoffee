package com.example.appscoffe;

public class Order {

    public static final String NAMA_TABEL = "coffeshop";
    public static final String KOLOM_ID = "id";
    public static final String KOLOM_COFFE = "coffe";
    public static final String KOLOM_TIMESTAMP = "timestamp";

    //query CREATE TABLE
    public static final String CREATE_TABLE = "CREATE TABLE " + NAMA_TABEL
            + "(" + KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KOLOM_COFFE + " TEXT, "
            + KOLOM_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private int id;
    private String coffe;
    private String timestamp;

    public Order(int id, String coffe, String timestamp) {
        this.id = id;
        this.coffe = coffe;
        this.timestamp = timestamp;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoffe() {
        return coffe;
    }

    public void setOrder(String coffe) {
        this.coffe = coffe;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}