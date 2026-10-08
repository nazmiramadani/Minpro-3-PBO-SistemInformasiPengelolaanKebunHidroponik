package controller;

import java.util.ArrayList;
import model.Tanaman;
import model.TanamanBuah;
import model.TanamanSayur;
import view.TanamanView;

public class TanamanController {
    private ArrayList<Tanaman> daftarTanaman;
    private TanamanView view;

    public TanamanController(TanamanView view) {
        this.daftarTanaman = new ArrayList<>();
        this.view = view;

        //DUMMY DATA
        daftarTanaman.add(new TanamanSayur(1, "Selada Hijau", "Grade A", "NFT", "Sangat Renyah", 7));
        daftarTanaman.add(new TanamanBuah(2, "Tomat Cherry", "Grade B", "Dutch Bucket", 10, true));
    }

//TAMBAH
    public void tambahTanaman() {
        int id = view.inputAngka("ID Tanaman (Angka): ", 1);

        if (cariTanaman(id) != null) {
            view.tampilkanError("ID Tanaman " + id + " sudah digunakan");
            return;
        }

        String nama = view.inputTeks("Nama Tanaman: ");
        String grade = view.inputTeks("Grade Tanaman: ");
        String sistem = view.inputTeks("Sistem Irigasi: ");
        int kategori = view.inputAngka("Kategori (1. Sayur / 2. Buah): ");

        switch (kategori) {
            case 1 -> {
                String kerenyahan = view.inputTeks("Tingkat Kerenyahan: ");
                int masaSimpan = view.inputAngka("Masa Simpan (hari): ", TanamanSayur.masaSimpanMin);

                tambahTanaman(id, nama, grade, sistem, kerenyahan, masaSimpan);
                view.tampilkanPesan("Tanaman berhasil ditambahkan");
            }
            case 2 -> {
                double brix = view.inputDesimal("Skala Brix (" + TanamanBuah.brixMin + " - " + TanamanBuah.brixMax + "): ",
                        TanamanBuah.brixMin, TanamanBuah.brixMax);
                boolean berbiji = view.inputYesNo("Apakah Tanaman Berbiji? (yes/no): ");

                tambahTanaman(id, nama, grade, sistem, brix, berbiji);
                view.tampilkanPesan("Tanaman berhasil ditambahkan");
            }
            default -> view.tampilkanError("Kategori tidak valid!");
        }
    }

    public void tambahTanaman(Tanaman t) {
        if (t == null) {
            throw new IllegalArgumentException("Data tanaman tidak boleh null");
        }
        if (cariTanaman(t.getIdTanaman()) != null) {
            throw new IllegalArgumentException("ID Tanaman " + t.getIdTanaman() + " sudah digunakan");
        }
        daftarTanaman.add(t);
    }

    public void tambahTanaman(int id, String nama, String grade, String sistem, String kerenyahan, int masaSimpan) {
        tambahTanaman(new TanamanSayur(id, nama, grade, sistem, kerenyahan, masaSimpan));
    }

    public void tambahTanaman(int id, String nama, String grade, String sistem, double brix, boolean berbiji) {
        tambahTanaman(new TanamanBuah(id, nama, grade, sistem, brix, berbiji));
    }

    public void tampilkanTanaman() {
        view.tampilkanDaftar(daftarTanaman);
    }

//UPDATE
    public void updateTanaman() {
        int idTarget = view.inputAngka("Masukkan ID Tanaman yang ingin diupdate: ", 1);
        Tanaman t = cariTanaman(idTarget);

        if (t == null) {
            view.tampilkanError("Data tanaman dengan ID " + idTarget + " tidak ada");
            return;
        }

        String namaBaru = view.inputTeks("Nama Tanaman Baru: ");
        String gradeBaru = view.inputTeks("Grade Tanaman Baru: ");
        String sistemBaru = view.inputTeks("Sistem Irigasi Baru: ");

        switch (t.getKategori()) {
            case Tanaman.kategoriSayur -> {
                TanamanSayur ts = (TanamanSayur) t;
                String kerenyahanBaru = view.inputTeks("Tingkat Kerenyahan Baru: ");
                int masaSimpanBaru = view.inputAngka("Masa Simpan Baru (hari): ", TanamanSayur.masaSimpanMin);

                ts.setNamaTanaman(namaBaru);
                ts.setGradeTanaman(gradeBaru);
                ts.setSistemIrigasi(sistemBaru);
                ts.setTingkatKerenyahan(kerenyahanBaru);
                ts.setMasaSimpan(masaSimpanBaru);
            }
            case Tanaman.kategoriBuah -> {
                TanamanBuah tb = (TanamanBuah) t;
                double brixBaru = view.inputDesimal("Skala Brix Baru (" + TanamanBuah.brixMin + " - " + TanamanBuah.brixMax + "): ",
                        TanamanBuah.brixMin, TanamanBuah.brixMax);
                boolean berbijiBaru = view.inputYesNo("Apakah Tanaman Berbiji? (yes/no): ");

                tb.setNamaTanaman(namaBaru);
                tb.setGradeTanaman(gradeBaru);
                tb.setSistemIrigasi(sistemBaru);
                tb.setSkalaBrix(brixBaru);
                tb.setBerbiji(berbijiBaru);
            }
            default -> {
                view.tampilkanError("Kategori tanaman tidak dikenal");
                return;
            }
        }

        view.tampilkanPesan("Data berhasil diupdate");
    }

//HAPUS
    public void hapusTanaman() {
        int idTarget = view.inputAngka("Masukkan ID Tanaman yang ingin dihapus: ", 1);

        boolean ditemukan = false;
        for (int i = 0; i < daftarTanaman.size(); i++) {
            if (daftarTanaman.get(i).getIdTanaman() == idTarget) {
                daftarTanaman.remove(i);
                view.tampilkanPesan("Tanaman berhasil dihapus");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            view.tampilkanError("Data tanaman dengan ID " + idTarget + " tidak ada");
        }
    }

    public Tanaman cariTanaman(int id) {
        for (int i = 0; i < daftarTanaman.size(); i++) {
            if (daftarTanaman.get(i).getIdTanaman() == id) {
                return daftarTanaman.get(i);
            }
        }
        return null;
    }

    public ArrayList<Tanaman> cariTanaman(String kataKunci) {
        ArrayList<Tanaman> hasil = new ArrayList<>();
        for (int i = 0; i < daftarTanaman.size(); i++) {
            Tanaman t = daftarTanaman.get(i);
            if (t.getNamaTanaman().toLowerCase().contains(kataKunci.toLowerCase())) {
                hasil.add(t);
            }
        }
        return hasil;
    }
}