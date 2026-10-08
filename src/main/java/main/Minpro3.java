package main;

import controller.TanamanController;
import java.util.NoSuchElementException;
import view.TanamanView;

public class Minpro3 {
    public static void main(String[] args) {
        TanamanView view = new TanamanView();
        TanamanController controller = new TanamanController(view);

        boolean berjalan = true;

        while (berjalan) {
            view.tampilkanMenu();

            try {
                int pilihan = view.inputAngka("Pilih menu (1-5): ");

                switch (pilihan) {
                    case 1 -> controller.tambahTanaman();
                    case 2 -> controller.tampilkanTanaman();
                    case 3 -> controller.updateTanaman();
                    case 4 -> controller.hapusTanaman();
                    case 5 -> {
                        view.tampilkanPesan("Terima kasih dan bye");
                        berjalan = false;
                    }
                    default -> view.tampilkanError("Pilihan " + pilihan + " tidak valid, pilih 1-5");
                }
            } catch (NoSuchElementException e) {
                view.tampilkanError("Input sudah tidak bisa, program dihentikan");
                berjalan = false;
            } catch (IllegalArgumentException e) {
                view.tampilkanError(e.getMessage());
            } catch (Exception e) {
                view.tampilkanError(e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
        view.tutup();
    }
}