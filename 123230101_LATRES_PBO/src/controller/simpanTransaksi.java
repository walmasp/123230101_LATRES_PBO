package Controller;

import controller.TransaksiController;
import model.Pelanggan;
import model.Transaksi;
import model.obat;
import java.time.LocalDate;
import javax.swing.*;

public class simpanTransaksi {

    public static void handleSimpan(
        JTextField namaField,
        JTextField obatField,
        JTextField hargaField,
        JTextField jumlahField,
        JFrame parentFrame
    ) {
        try {
            String user = namaField.getText();
            String obat = obatField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int jumlah = Integer.parseInt(jumlahField.getText());

            double total = harga * jumlah;
            double diskon = jumlah > 5 ? total * 0.1 : 0;
            double totalBayar = total - diskon;

            int id = 0; // placeholder, atau generate dari DB nanti
            Transaksi t = new Transaksi(id, user, obat, harga, jumlah);
            t.setTotalHarga(total);
            t.setDiskon(diskon);
            t.setTotalBayar(totalBayar);
            t.setTanggal(LocalDate.now());

            TransaksiController controller = new TransaksiController();
            controller.simpanTransaksi(t);

            JOptionPane.showMessageDialog(parentFrame, "Transaksi berhasil disimpan.");
            namaField.setText("");
            obatField.setText("");
            hargaField.setText("");
            jumlahField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentFrame, "Harga dan Jumlah harus berupa angka.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame, "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
