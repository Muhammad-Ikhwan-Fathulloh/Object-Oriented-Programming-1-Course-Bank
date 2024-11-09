class Library {
    String libraryName;
    String libraryAddress;
    String libraryGuard;
    int libraryNumber;
    String[] libraryDay = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
    String libraryStartTime;
    String libraryFinishTime;

    public Library(
        String libraryName, 
        String libraryAddress, 
        String libraryGuard, 
        int libraryNumber, 
        String[] libraryDay, 
        String libraryStartTime, 
        String libraryFinishTime
    ) 
    {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.libraryGuard = libraryGuard;
        this.libraryNumber = libraryNumber;
        this.libraryDay = libraryDay;
        this.libraryStartTime = libraryStartTime;
        this.libraryFinishTime = libraryFinishTime;
    }
}