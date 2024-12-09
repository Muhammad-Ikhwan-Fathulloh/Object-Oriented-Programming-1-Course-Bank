## **Sesi 5: Polimorfisme**

---

### **Pengertian Polimorfisme**
Polimorfisme memungkinkan objek untuk mengambil banyak bentuk. Ini memungkinkan metode untuk melakukan hal yang berbeda berdasarkan objek yang memanggilnya.

---

### **Metode Virtual dan Abstrak**
```java
abstract class Kendaraan {
    abstract void berjalan();
}

class Mobil extends Kendaraan {
    void berjalan() {
        System.out.println("Mobil berjalan");
    }
}
```

---

### **Late Binding**
Late binding adalah teknik di mana metode yang akan dipanggil ditentukan pada waktu eksekusi, bukan pada waktu kompilasi. Dalam polimorfisme, late binding memungkinkan metode yang tepat untuk dipanggil berdasarkan objek spesifik yang digunakan pada runtime.