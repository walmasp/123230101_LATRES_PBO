package model;

public class obat {
    private int id;
    private String nama;
    private double harga;

    public obat(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
}
