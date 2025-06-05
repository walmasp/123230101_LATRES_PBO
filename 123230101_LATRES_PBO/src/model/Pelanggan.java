package model;

public class Pelanggan {
    private int id;
    private String nama;

    public Pelanggan(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
}
