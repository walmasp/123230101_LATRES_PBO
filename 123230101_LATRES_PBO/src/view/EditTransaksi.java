package view;

import controller.TransaksiController;
import javax.swing.*;
import model.Transaksi;

public class EditTransaksi extends JFrame {

    private final TransaksiController controller = new TransaksiController();
    private final Transaksi transaksi;
    private final Runnable onSuccess; // untuk refresh data

    private JTextField txtPelanggan, txtObat, txtHarga, txtJumlah;
    private JButton btnSimpan;

    public EditTransaksi(Transaksi transaksi, Runnable onSuccess) {
        this.transaksi = transaksi;
        this.onSuccess = onSuccess;
        initUI();
        setLocationRelativeTo(null); // tampil di tengah layar
        setVisible(true);
    }

    private void initUI() {
        setTitle("Edit Transaksi");
        setSize(300, 250);
        setLayout(null);

        JLabel lblPelanggan = new JLabel("User:");
        JLabel lblObat = new JLabel("Obat:");
        JLabel lblHarga = new JLabel("Harga:");
        JLabel lblJumlah = new JLabel("Jumlah:");

        txtPelanggan = new JTextField(transaksi.getUser());
        txtObat = new JTextField(transaksi.getObat());
        txtHarga = new JTextField(String.valueOf(transaksi.getHarga()));
        txtJumlah = new JTextField(String.valueOf(transaksi.getJumlahBeli()));

        btnSimpan = new JButton("Simpan");

        lblPelanggan.setBounds(20, 20, 80, 25);
        lblObat.setBounds(20, 55, 80, 25);
        lblHarga.setBounds(20, 90, 80, 25);
        lblJumlah.setBounds(20, 125, 80, 25);

        txtPelanggan.setBounds(110, 20, 150, 25);
        txtObat.setBounds(110, 55, 150, 25);
        txtHarga.setBounds(110, 90, 150, 25);
        txtJumlah.setBounds(110, 125, 150, 25);
        btnSimpan.setBounds(90, 170, 100, 30);

        add(lblPelanggan);
        add(lblObat);
        add(lblHarga);
        add(lblJumlah);
        add(txtPelanggan);
        add(txtObat);
        add(txtHarga);
        add(txtJumlah);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanPerubahan());
    }

    private void simpanPerubahan() {
        try {
            String pelanggan = txtPelanggan.getText();
            String obat = txtObat.getText();
            double harga = Double.parseDouble(txtHarga.getText());
            int jumlah = Integer.parseInt(txtJumlah.getText());

            double total = harga * jumlah;
            double diskon = jumlah > 5 ? total * 0.1 : 0;
            double totalBayar = total - diskon;

            controller.updateTransaksi(
                transaksi.getId(), pelanggan, obat, harga, jumlah, total, diskon, totalBayar
            );

            JOptionPane.showMessageDialog(this, "Transaksi berhasil diperbarui!");
            if (onSuccess != null) onSuccess.run(); // refresh tampilan
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka.");
        }
    }
}
