package model;

public class TanamanBuah extends Tanaman implements PerawatanTanaman {
    public static final double brixMin = 1;
    public static final double brixMax = 16;
    private static final int masaPanenBerbiji = 90;
    private static final int masaPanenTanpaBiji = 75;

    private double skalaBrix;
    private boolean berbiji;

    public TanamanBuah(int idTanaman, String namaTanaman, String gradeTanaman, String sistemIrigasi, double skalaBrix, boolean berbiji) {
        super(idTanaman, namaTanaman, gradeTanaman, sistemIrigasi);
        setSkalaBrix(skalaBrix);
        setBerbiji(berbiji);
    }

    public TanamanBuah(int idTanaman, String namaTanaman, String gradeTanaman, String sistemIrigasi) {
        this(idTanaman, namaTanaman, gradeTanaman, sistemIrigasi, 8, false);
    }

    public double getSkalaBrix() {
        return skalaBrix;
    }

    public boolean isBerbiji() {
        return berbiji;
    }

    public final void setSkalaBrix(double skalaBrix) {
        if (skalaBrix >= brixMin && skalaBrix <= brixMax) {
            this.skalaBrix = skalaBrix;
        } else {
            throw new IllegalArgumentException("Skala Brix harus dalam rentang " + brixMin + " - " + brixMax);
        }
    }

    public final void setBerbiji(boolean berbiji) {
        this.berbiji = berbiji;
    }

    @Override
    public String getKategori() {
        return kategoriBuah;
    }

    @Override
    public int hitungMasaPanen() {
        if (berbiji) {
            return masaPanenBerbiji;
        }
        return masaPanenTanpaBiji;
    }

    @Override
    public String tampilkanPerawatanKhusus() {
        return "Pasang penyangga batang dan bantu penyerbukan saat berbunga";
    }

    @Override
    public String tampilkanInfo() {
        return super.tampilkanInfo() + "\n"
             + "Skala Brix     : " + skalaBrix + " Brix\n"
             + "Berbiji        : " + (berbiji ? "Yes" : "No");
    }

    @Override
    public String cekNutrisi() {
        return "EC 2.0 - 3.5 mS/cm, pH 5.8 - 6.3, cek setiap 2 hari";
    }

    @Override
    public String jadwalPenyiraman() {
        return "Sistem " + sistemIrigasi + ", siram nutrisi 3x sehari (pagi, siang, sore)";
    }
}