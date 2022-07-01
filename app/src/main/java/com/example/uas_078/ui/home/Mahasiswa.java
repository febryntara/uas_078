package com.example.uas_078.ui.home;

public class Mahasiswa {
    private String id;
    private String nama;
    private String prodi;
    private String nim;
    private String jurusan;

    public Mahasiswa (String id, String nama, String prodi, String nim, String jurusan){
        this.id = id;
        this.nama = nama;
        this.prodi = prodi;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
