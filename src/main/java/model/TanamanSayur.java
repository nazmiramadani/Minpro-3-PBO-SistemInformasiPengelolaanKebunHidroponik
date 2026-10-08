package model;

public class TanamanSayur extends Tanaman implements PerawatanTanaman {
    public static final int masaSimpanMin = 1;
    private static final int masaPanen = 35;

    private String tingkatKerenyahan;
    private int masaSimpan;

    public TanamanSayur(int idTanaman, String namaTanaman, String gradeTanaman, String sistemIrigasi, String tingkatKerenyahan, int masaSimpan) {
        super(idTanaman, namaTanaman, gradeTanaman, sistemIrigasi);
        setTingkatKerenyahan(tingkatKerenyahan);
        setMasaSimpan(masaSimpan);
    }

    public TanamanSayur(int idTanaman, String namaTanaman, String gradeTanaman, String sistemIrigasi) {
        this(idTanaman, namaTanaman, gradeTanaman, sistemIrigasi, "Standar", 7);
    }

    public String getTingkatKerenyahan() {
        return tingkatKerenyahan;
    }

    public int getMasaSimpan() {
        return masaSimpan;
    }

    public final void setTingkatKerenyahan(String tingkatKerenyahan) {
        if (tingkatKerenyahan == null || tingkatKerenyahan.trim().isEmpty()) {
            throw new IllegalArgumentException("Tingkat kerenyahan tidak valid");
        }
        this.tingkatKerenyahan = tingkatKerenyahan.trim();
    }

    public final void setMasaSimpan(int masaSimpan) {
        if (masaSimpan >= masaSimpanMin) {
            this.masaSimpan = masaSimpan;
        } else {
            throw new IllegalArgumentException("Masa simpan harus minimal " + masaSimpanMin + " hari");
        }
    }

    @Override
    public String getKategori() {
        return kategoriSayur;
    }

    @Override
    public int hitungMasaPanen() {
        return masaPanen;
    }

    @Override
    public String tampilkanPerawatanKhusus() {
        return "Jaga suhu larutan nutrisi di bawah 25 derajat C agar daun tetap renyah";
    }

    @Override
    public String tampilkanInfo() {
        return super.tampilkanInfo() + "\n"
             + "Kerenyahan     : " + tingkatKerenyahan + "\n"
             + "Masa Simpan    : " + masaSimpan + " Hari";
    }

    @Override
    public String cekNutrisi() {
        return "EC 1.2 - 1.8 mS/cm, pH 5.5 - 6.5, cek setiap 3 hari";
    }

    @Override
    public String jadwalPenyiraman() {
        return "Sistem " + sistemIrigasi + ", alirkan nutrisi 15 menit setiap 1 jam";
    }
}