## **Sesi 4: Pewarisan (Inheritance)**

---

### **Pengertian Pewarisan**
Pewarisan adalah mekanisme di mana satu kelas dapat mewarisi atribut dan metode dari kelas lain.

---

### **Kelas Turunan (Subclass) dan Kelas Induk (Superclass)**
```java
class Kendaraan {
    void berjalan() {
        System.out.println("Kendaraan berjalan");
    }
}

class Mobil extends Kendaraan {
    void klakson() {
        System.out.println("Mobil membunyikan klakson");
    }
}
```

---

### **Overriding dan Overloading**

- **Overriding**: Mengubah implementasi metode yang diwarisi dari superclass.
- **Overloading**: Membuat beberapa metode dengan nama yang sama tetapi dengan parameter yang berbeda.
