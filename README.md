# Sistem Informasi Pengelolaan Kebun Hidroponik

## Deskripsi Singkat Program

Program ini adalah aplikasi berbasis konsol (CLI) yang dibangun menggunakan bahasa pemrograman Java dengan pola arsitektur **MVC (Model - View - Controller)**. Aplikasi ini berfungsi untuk mengelola data tanaman pada kebun hidroponik secara digital. Data disimpan di dalam memori menggunakan struktur data `ArrayList`, sehingga data akan kembali ke data awal (dummy data) setiap program dijalankan ulang.

Program ini menerapkan konsep Object-Oriented Programming (OOP) berupa **encapsulation, inheritance, polymorphism, abstraction**, serta **interface** sebagai nilai tambah.

Entitas utama pada program ini adalah **Tanaman**, yaitu sebuah *abstract class* yang diturunkan menjadi dua jenis tanaman:

* **Tanaman Sayur** (`TanamanSayur`): memiliki atribut khusus berupa tingkat kerenyahan dan masa simpan.
* **Tanaman Buah** (`TanamanBuah`): memiliki atribut khusus berupa skala Brix dan status berbiji.

Melalui menu utama, pengguna dapat melakukan operasi CRUD (Create, Read, Update, Delete) pada data tanaman. Program juga dilengkapi dengan validasi input pada setter (enkapsulasi), validasi input pada `View` (perulangan sampai input benar), serta blok `try-catch` pada `main` sehingga aplikasi tidak akan crash apabila pengguna memasukkan data yang tidak valid.

## Penjelasan Struktur Package

<img width="359" height="300" alt="image" src="https://github.com/user-attachments/assets/1534fb94-89c7-4774-bcfb-16823da9aa43" />

| Package | File | Penjelasan |
|---|---|---|
| `main` | `Minpro3.java` | Class yang berisi method `main`. Menampilkan menu utama dalam perulangan, membaca pilihan pengguna, memanggil method pada controller, dan menangani exception (`NoSuchElementException`, `IllegalArgumentException`, `Exception`). |
| `controller` | `TanamanController.java` | Menyimpan `ArrayList<Tanaman>` dan berisi logika CRUD: tambah, tampil, update, hapus, serta pencarian berdasarkan ID atau nama. Controller menghubungkan `Model` dengan `View`. |
| `model` | `Tanaman.java` | *Abstract class* (superclass) yang memuat atribut dan method umum semua tanaman. |
| `model` | `TanamanSayur.java` | Subclass dari `Tanaman` untuk jenis sayur, sekaligus mengimplementasikan interface `PerawatanTanaman`. |
| `model` | `TanamanBuah.java` | Subclass dari `Tanaman` untuk jenis buah, sekaligus mengimplementasikan interface `PerawatanTanaman`. |
| `model` | `PerawatanTanaman.java` | *Interface* yang mendefinisikan kontrak perawatan tanaman (`cekNutrisi()` dan `jadwalPenyiraman()`). |
| `view` | `TanamanView.java` | Menangani seluruh input dan output ke konsol (menu, daftar tanaman, pesan, error) beserta validasi input (teks tidak kosong, angka, desimal, yes/no). |

## Penjelasan Alur Program

1. **Program Dijalankan**

   `Minpro3` membuat objek `TanamanView` dan `TanamanController`. Saat controller dibuat, dua data awal (*dummy data*) otomatis ditambahkan, yaitu Selada Hijau (sayur) dan Tomat Cherry (buah).

2. **Menu Utama**

<img width="537" height="172" alt="image" src="https://github.com/user-attachments/assets/594f424f-1807-43be-8d01-f277eca79e71" />

   Menu ditampilkan berulang sampai pengguna memilih menu 5 (Keluar). Jika pilihan di luar 1-5, program menampilkan pesan error dan kembali ke menu.

3. **Menu 1 - Tambah Tanaman**

<img width="599" height="341" alt="image" src="https://github.com/user-attachments/assets/89a1c251-46c2-4e05-ba30-1d9c5394bcdf" />

   Pengguna memasukkan ID (angka, minimal 1, tidak boleh sama dengan ID yang sudah ada), nama, grade, dan sistem irigasi. Selanjutnya pengguna memilih kategori:
   * **1 (Sayur)**: input tingkat kerenyahan dan masa simpan (hari, minimal 1).
   * **2 (Buah)**: input skala Brix (rentang 1 - 16) dan status berbiji (`yes`/`no`).
   * Kategori selain 1 dan 2 akan ditolak dengan pesan error.

4. **Menu 2 - Tampilkan Tanaman**

<img width="855" height="708" alt="image" src="https://github.com/user-attachments/assets/1e50762e-84e0-4243-8ef2-734797871d5e" />

   Menampilkan seluruh tanaman beserta informasi umum, informasi khusus sesuai jenisnya, estimasi masa panen, perawatan khusus, nutrisi, jadwal penyiraman, dan kata-kata. Jika data kosong, ditampilkan pesan "Data tanaman kosong."

5. **Menu 3 - Update Tanaman**

<img width="599" height="312" alt="image" src="https://github.com/user-attachments/assets/9d5e79a1-f0eb-4338-9e90-bcd2cdd2b500" />

   Pengguna memasukkan ID tanaman yang ingin diubah. Jika ID tidak ditemukan, muncul pesan error. Jika ditemukan, pengguna mengisi data baru (nama, grade, sistem irigasi), kemudian data khusus sesuai kategori tanaman (sayur atau buah). ID tanaman tidak dapat diubah.

6. **Menu 4 - Hapus Tanaman**

<img width="562" height="218" alt="image" src="https://github.com/user-attachments/assets/05f259ee-7da3-471d-83c0-fb708a60da57" />

   Pengguna memasukkan ID tanaman yang ingin dihapus. Jika ditemukan, data dihapus dari `ArrayList`; jika tidak, muncul pesan error.

7. **Menu 5 - Keluar**

<img width="695" height="310" alt="image" src="https://github.com/user-attachments/assets/6e067406-6354-4c25-b4b5-293a5f25badb" />

   Program menampilkan pesan perpisahan, perulangan berhenti, dan `Scanner` ditutup melalui `view.tutup()`.

## Penjelasan Penerapan Encapsulation dan Inheritance

### Encapsulation

Seluruh atribut pada class model dideklarasikan dengan access modifier **`private`** (atau **`protected`** pada atribut yang diwariskan di `Tanaman`), sehingga tidak dapat diakses langsung dari luar class. Akses dilakukan melalui **getter** dan **setter** publik yang melakukan validasi:

* `Tanaman`: `setNamaTanaman()`, `setGradeTanaman()`, `setSistemIrigasi()` menolak `null` atau string kosong/spasi saja, dan menghapus spasi di awal/akhir dengan `trim()`.
* `TanamanSayur`:
  * `setTingkatKerenyahan()` menolak string kosong.
  * `setMasaSimpan()` memvalidasi masa simpan minimal `masaSimpanMin` (1 hari).
* `TanamanBuah`:
  * `setSkalaBrix()` memvalidasi nilai Brix berada pada rentang `brixMin` - `brixMax` (1 - 16).
* Validasi gagal akan melempar `IllegalArgumentException` yang ditangkap di `Minpro3` dan ditampilkan sebagai pesan error.

Penerapan lain:
* Atribut `idTanaman` dideklarasikan **`private final`**: nilainya hanya dapat diisi sekali melalui constructor dan tidak disediakan setter, sehingga identitas unik tanaman terlindungi.
* Konstanta seperti `brixMin`, `brixMax`, `masaSimpanMin`, `masaPanen`, `masaPanenBerbiji`, dan `masaPanenTanpaBiji` dideklarasikan `static final` (sebagian `private`) agar aturan bisnis tidak dapat diubah dari luar.
* Setter dideklarasikan **`final`** dan dipanggil di dalam constructor, sehingga validasi selalu berlaku sejak objek dibuat dan tidak dapat di-override oleh subclass.

### Inheritance

Program menerapkan relasi pewarisan sebagai berikut:

* **Superclass**: `Tanaman` memuat atribut umum (`idTanaman`, `namaTanaman`, `gradeTanaman`, `sistemIrigasi`), getter/setter, method `tampilkanInfo()`, dan method `kataKata()` yang dideklarasikan `final` sehingga tidak dapat di-override oleh subclass.
* **Subclass 1**: `TanamanSayur extends Tanaman` menambahkan atribut khusus sayur (`tingkatKerenyahan`, `masaSimpan`).
* **Subclass 2**: `TanamanBuah extends Tanaman` menambahkan atribut khusus buah (`skalaBrix`, `berbiji`).

Kedua subclass memanggil constructor superclass melalui `super(...)` untuk mengisi atribut yang diwariskan, kemudian menginisialisasi atribut miliknya sendiri. Pada `TanamanSayur` dan `TanamanBuah` juga tersedia constructor tambahan dengan nilai default (`this(...)`), yaitu kerenyahan "Standar" dengan masa simpan 7 hari untuk sayur, dan Brix 8 tidak berbiji untuk buah.

## Penjelasan Penerapan Polymorphism dan Abstraction

### Abstraction

`Tanaman` dideklarasikan sebagai **abstract class**, sehingga tidak dapat dibuat objeknya secara langsung (hanya bisa lewat `TanamanSayur` atau `TanamanBuah`). Class ini memiliki tiga **abstract method** yang wajib diimplementasikan oleh setiap subclass:

| Abstract method | `TanamanSayur` | `TanamanBuah` |
|---|---|---|
| `getKategori()` | Mengembalikan `"Sayur"` | Mengembalikan `"Buah"` |
| `hitungMasaPanen()` | Selalu 35 hari | 90 hari jika berbiji, 75 hari jika tidak berbiji |
| `tampilkanPerawatanKhusus()` | Menjaga suhu larutan nutrisi di bawah 25 derajat C | Memasang penyangga batang dan membantu penyerbukan |

Dengan abstraction, `Tanaman` hanya mendefinisikan "apa" yang harus dimiliki setiap tanaman, sedangkan "bagaimana" caranya ditentukan oleh masing-masing jenis tanaman.

### Polymorphism

1. **Method overriding (runtime polymorphism)**
   * `tampilkanInfo()` di `Tanaman` di-override oleh `TanamanSayur` dan `TanamanBuah`. Masing-masing memanggil `super.tampilkanInfo()` terlebih dahulu, lalu menambahkan atribut spesifiknya.
   * `getKategori()`, `hitungMasaPanen()`, dan `tampilkanPerawatanKhusus()` diimplementasikan dengan perilaku berbeda di setiap subclass.

2. **Method overloading (compile-time polymorphism)**
   * `TanamanController.tambahTanaman()` memiliki 4 versi: tanpa parameter (input interaktif), dengan objek `Tanaman`, dengan parameter sayur, dan dengan parameter buah.
   * `TanamanController.cariTanaman(int id)` dan `cariTanaman(String kataKunci)`.
   * `TanamanView.inputAngka(String)` dan `inputAngka(String, int)`.
   * Constructor `TanamanSayur` dan `TanamanBuah` masing-masing memiliki 2 versi.

## Penerapan Nilai Tambah

### Interface

Nilai tambah yang diterapkan pada project ini adalah **interface**, yaitu `PerawatanTanaman` pada package `model` (file `PerawatanTanaman.java`):

```java
public interface PerawatanTanaman {
    String cekNutrisi();
    String jadwalPenyiraman();
}
```

Interface ini diimplementasikan oleh `TanamanSayur` dan `TanamanBuah` (`extends Tanaman implements PerawatanTanaman`), sehingga setiap kelas wajib menyediakan isi method tersebut dengan perilaku masing-masing:

| Method | `TanamanSayur` | `TanamanBuah` |
|---|---|---|
| `cekNutrisi()` | EC 1.2 - 1.8 mS/cm, pH 5.5 - 6.5, cek setiap 3 hari | EC 2.0 - 3.5 mS/cm, pH 5.8 - 6.3, cek setiap 2 hari |
| `jadwalPenyiraman()` | Alirkan nutrisi 15 menit setiap 1 jam | Siram nutrisi 3x sehari (pagi, siang, sore) |

**Letak penggunaan:**
* Deklarasi interface: `model/PerawatanTanaman.java`
* Implementasi: `model/TanamanSayur.java` dan `model/TanamanBuah.java`
* Pemanggilan: `view/TanamanView.java` pada method `tampilkanDetail()`, di mana objek dicek dengan `t instanceof PerawatanTanaman`, di-cast ke `PerawatanTanaman`, lalu `cekNutrisi()` dan `jadwalPenyiraman()` ditampilkan ke layar.

Dengan interface, kemampuan perawatan dipisahkan dari hierarki pewarisan `Tanaman`, sehingga jenis tanaman baru di masa depan dapat menambahkan kemampuan yang sama hanya dengan mengimplementasikan `PerawatanTanaman`.
