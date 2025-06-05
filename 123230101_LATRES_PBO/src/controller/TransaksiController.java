package controller;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Transaksi;

public class TransaksiController {

    public void simpanTransaksi(Transaksi t) {
        String sql = "INSERT INTO transaksi (user, obat, harga, jumlah_beli, tanggal, total_harga, diskon, total_bayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/apotek?zeroDateTimeBehavior=CONVERT_TO_NULL",
                 "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getUser());
            stmt.setString(2, t.getObat());
            stmt.setDouble(3, t.getHarga());
            stmt.setInt(4, t.getJumlahBeli());
            stmt.setDate(5, java.sql.Date.valueOf(t.getTanggal()));
            stmt.setDouble(6, t.getTotalHarga());
            stmt.setDouble(7, t.getDiskon());
            stmt.setDouble(8, t.getTotalBayar());

            stmt.executeUpdate();
            System.out.println("Transaksi berhasil disimpan.");
        } catch (Exception e) {
            System.err.println("Gagal menyimpan transaksi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Transaksi> getTransaksiHariIni() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi WHERE tanggal = CURDATE()";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apotek?zeroDateTimeBehavior=CONVERT_TO_NULL",
                "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaksi t = new Transaksi(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getString("obat"),
                    rs.getDouble("harga"),
                    rs.getInt("jumlah_beli")
                );
                // set ulang field lainnya jika diperlukan
                t.setTotalHarga(rs.getDouble("total_harga"));
                t.setDiskon(rs.getDouble("diskon"));
                t.setTotalBayar(rs.getDouble("total_bayar"));
                t.setTanggal(rs.getDate("tanggal").toLocalDate());

                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void hapusTransaksiById(int id) {
        String sql = "DELETE FROM transaksi WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apotek?zeroDateTimeBehavior=CONVERT_TO_NULL",
                "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Transaksi berhasil dihapus.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTransaksi(int id, String user, String obat, double harga, int jumlah, double total, double diskon, double totalBayar) {
        String sql = "UPDATE transaksi SET user = ?, obat = ?, harga = ?, jumlah_beli = ?, total_harga = ?, diskon = ?, total_bayar = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/apotek?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, obat);
            stmt.setDouble(3, harga);
            stmt.setInt(4, jumlah);
            stmt.setDouble(5, total);
            stmt.setDouble(6, diskon);
            stmt.setDouble(7, totalBayar);
            stmt.setInt(8, id);
            stmt.executeUpdate();

            System.out.println("Transaksi berhasil diupdate.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
