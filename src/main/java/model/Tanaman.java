package model;

public abstract class Tanaman {
    public static final String kategoriSayur = "Sayur";
    public static final String kategoriBuah = "Buah";

    private final int idTanaman;
    protected String namaTanaman;
    protected String gradeTanaman;
    protected String sistemIrigasi;

    public Tanaman(int idTanaman, String namaTanaman, String gradeTanaman, String sistemIrigasi) {
        this.idTanaman = idTanaman;
        setNamaTanaman(namaTanaman);
        setGradeTanaman(gradeTanaman);
        setSistemIrigasi(sistemIrigasi);
    }

    public abstract String getKategori();

    public abstract int hitungMasaPanen();

    public abstract String tampilkanPerawatanKhusus();

    public int getIdTanaman() {
        return idTanaman;
    }

    public String getNamaTanaman() {
        return namaTanaman;
    }

    public String getGradeTanaman() {
        return gradeTanaman;
    }

    public String getSistemIrigasi() {
        return sistemIrigasi;
    }

    public final void setNamaTanaman(String namaTanaman) {
        if (namaTanaman == null || namaTanaman.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak valid");
        }
        this.namaTanaman = namaTanaman.trim();
    }

    public final void setGradeTanaman(String gradeTanaman) {
        if (gradeTanaman == null || gradeTanaman.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade Tanaman tidak valid");
        }
        this.gradeTanaman = gradeTanaman.trim();
    }

    public final void setSistemIrigasi(String sistemIrigasi) {
        if (sistemIrigasi == null || sistemIrigasi.trim().isEmpty()) {
            throw new IllegalArgumentException("Sistem Irigasi tidak valid");
        }
        this.sistemIrigasi = sistemIrigasi.trim();
    }

    public String tampilkanInfo() {
        return "ID Tanaman     : " + idTanaman + "\n"
             + "Kategori       : " + getKategori() + "\n"
             + "Nama Tanaman   : " + namaTanaman + "\n"
             + "Grade          : " + gradeTanaman + "\n"
             + "Sistem Irigasi : " + sistemIrigasi;
    }

    public final String kataKata() {
        return "Hidroponik Tanaman Masa Depan RAWRR:>";
    }
}