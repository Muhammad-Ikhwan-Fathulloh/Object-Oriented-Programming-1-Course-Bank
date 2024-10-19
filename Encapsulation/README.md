## **Sesi 3: Enkapsulasi**

### **Pengertian Enkapsulasi**
Enkapsulasi adalah konsep menyembunyikan data dan hanya memperbolehkan akses melalui metode tertentu.

### **Hak Akses (Public, Private, Protected)**
- **public**: dapat diakses dari mana saja.
- **private**: hanya dapat diakses dalam kelas itu sendiri.
- **protected**: dapat diakses dalam kelas itu sendiri dan kelas turunan.

### **Metode Get dan Set**
```java
class Mobil {
    private String merek;

    // Metode get untuk mengakses nilai merek
    public String getMerek() {
        return merek;
    }

    // Metode set untuk mengubah nilai merek
    public void setMerek(String merek) {
        this.merek = merek;
    }
}
```