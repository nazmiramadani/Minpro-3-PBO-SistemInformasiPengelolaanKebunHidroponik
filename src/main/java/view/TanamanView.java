package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import model.PerawatanTanaman;
import model.Tanaman;

public class TanamanView {
    private Scanner scanner;

    public TanamanView() {
        this.scanner = new Scanner(System.in);
        this.scanner.useLocale(Locale.US);
    }

//OUTPUT
    public void tampilkanMenu() {
        System.out.println("\n=== Sistem Informasi Pengelolaan Kebun Hidroponik ===");
        System.out.println("1. Tambah Tanaman");
        System.out.println("2. Tampilkan Tanaman");
        System.out.println("3. Update Tanaman");
        System.out.println("4. Hapus Tanaman");
        System.out.println("5. Keluar");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilkanError(String pesan) {
        System.out.println("Error: " + pesan);
    }

    public void tampilkanDaftar(ArrayList<Tanaman> daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Data tanaman kosong.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            tampilkanDetail(daftar.get(i));
        }
    }

    public void tampilkanDetail(Tanaman t) {
        System.out.println(t.tampilkanInfo());
        System.out.println("Estimasi Panen : " + t.hitungMasaPanen() + " Hari");
        System.out.println("Perawatan      : " + t.tampilkanPerawatanKhusus());

        if (t instanceof PerawatanTanaman) {
            PerawatanTanaman p = (PerawatanTanaman) t;
            System.out.println("Nutrisi        : " + p.cekNutrisi());
            System.out.println("Penyiraman     : " + p.jadwalPenyiraman());
        }

        System.out.println(t.kataKata());
        System.out.println("-------------------------");
    }

//INPUT
    public String inputTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = scanner.nextLine().trim();

            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong atau hanya spasi");
            } else {
                return teks;
            }
        }
    }

    public int inputAngka(String pesan) {
        return inputAngka(pesan, Integer.MIN_VALUE);
    }

    public int inputAngka(String pesan, int minimum) {
        while (true) {
            try {
                System.out.print(pesan);
                int angka = scanner.nextInt();
                scanner.nextLine();

                if (angka < minimum) {
                    System.out.println("Angka minimal " + minimum);
                } else {
                    return angka;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka");
                scanner.nextLine();
            }
        }
    }

    public double inputDesimal(String pesan, double min, double max) {
        while (true) {
            try {
                System.out.print(pesan);
                double angka = scanner.nextDouble();
                scanner.nextLine();

                if (angka >= min && angka <= max) {
                    return angka;
                }
                System.out.println("Angka harus dalam rentang " + min + " - " + max);
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka, contoh 7.5");
                scanner.nextLine();
            }
        }
    }

    public boolean inputYesNo(String pesan) {
        while (true) {
            System.out.print(pesan);
            String jawaban = scanner.nextLine().trim();

            if (jawaban.equalsIgnoreCase("yes")) {
                return true;
            } else if (jawaban.equalsIgnoreCase("no")) {
                return false;
            }
            System.out.println("Jawaban harus 'yes' atau 'no'");
        }
    }

    public void tutup() {
        scanner.close();
    }
}