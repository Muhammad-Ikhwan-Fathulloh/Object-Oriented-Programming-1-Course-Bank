# Sesi 6: Abstraksi

## Pengertian Abstraksi

Abstraksi adalah proses menyembunyikan detail implementasi dan hanya menampilkan fitur penting dari objek.

## Kelas Abstrak dan Antarmuka (Interface)

### Kelas Abstrak
Kelas yang tidak dapat diinstansiasi dan dapat memiliki metode abstrak dan metode konkret.

```java
abstract class Hewan {
    abstract void suara();
}

class Kucing extends Hewan {
    void suara() {
        System.out.println("Meong");
    }
}
```

### Antarmuka (Interface)
Antarmuka adalah kelas abstrak yang hanya dapat memiliki metode abstrak.

```java
interface Hewan {
    void suara();
}

class Kucing implements Hewan {
    public void suara() {
        System.out.println("Meong");
    }
}
```