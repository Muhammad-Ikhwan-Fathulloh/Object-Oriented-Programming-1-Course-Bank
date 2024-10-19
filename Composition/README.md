# Sesi 7: Komposisi

## Pengertian Komposisi

Komposisi adalah hubungan antara objek di mana satu objek memiliki atau terdiri dari objek lain.

## Contoh Komposisi

```java
class Universitas {
    private Fakultas[] fakultas;

    public Universitas(Fakultas[] fakultas) {
        this.fakultas = fakultas;
    }
}

class Fakultas {
    private String nama;

    public Fakultas(String nama) {
        this.nama = nama;
    }
}
```