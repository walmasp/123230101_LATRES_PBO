package model;

import java.time.LocalDate;

public class Transaksi {
    private int id;
    private String user;      
    private String obat;      
    private int jumlahBeli;
    private double harga; 
    private double totalHarga;
    private double diskon;
    private double totalBayar;
    private LocalDate tanggal;

    // Constructor sekarang harus menerima harga sebagai parameter karena obat sudah String
    public Transaksi(int id, String user, String obat, double harga, int jumlahBeli) {
        this.id = id;
        this.user = user;
        this.obat = obat;
        this.harga = harga;
        this.jumlahBeli = jumlahBeli;
        this.totalHarga = harga * jumlahBeli;
        this.diskon = (jumlahBeli > 5) ? totalHarga * 0.1 : 0;
        this.totalBayar = totalHarga - diskon;
        this.tanggal = LocalDate.now();
    }

    public void setTotalHarga(double total) { this.totalHarga = total; }
    public void setDiskon(double diskon) { this.diskon = diskon; }
    public void setTotalBayar(double bayar) { this.totalBayar = bayar; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getObat() {
        return obat;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public double getDiskon() {
        return diskon;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }
}
