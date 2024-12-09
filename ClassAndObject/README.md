## **Sesi 2: Kelas dan Objek**

---

### **Pengertian Kelas dan Objek**  
- **Kelas** adalah cetak biru untuk membuat objek.
- **Objek** adalah instance dari kelas.

---

### **Pembuatan Kelas dan Objek**
```java
class Mobil {
    String merek;
    String model;

    // Konstruktor
    Mobil(String merek, String model) {
        this.merek = merek;
        this.model = model;
    }
}

public class Main {
    public static void main(String[] args) {
        Mobil mobilSaya = new Mobil("Toyota", "Avanza");
    }
}
```

---

### **Variabel dalam Kelas**  
Variabel yang dideklarasikan dalam kelas disebut **atribut** atau **properti**.

---

### **Konstruktor dan Destruktor**  
- **Konstruktor** digunakan untuk menginisialisasi objek.
- **Destruktor** digunakan untuk membersihkan sumber daya sebelum objek dihapus.

```java
class Mobil {
    // Destruktor
    protected void finalize() {
        System.out.println("Objek Mobil dihapus");
    }
}
```