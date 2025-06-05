package Controller;

import javax.swing.JOptionPane;

public class TransaksiPopup {

    public static void tampilkanPopupPembayaran(double harga, int jumlah) {
        double totalHarga = harga * jumlah;
        double diskon = (jumlah > 5) ? totalHarga * 0.1 : 0;
        double totalBayar = totalHarga - diskon;

        JOptionPane.showMessageDialog(null,
            "Total Harga: Rp" + totalHarga +
            "\nDiskon: Rp" + diskon +
            "\nTotal Bayar: Rp" + totalBayar,
            "Rincian Pembayaran",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
