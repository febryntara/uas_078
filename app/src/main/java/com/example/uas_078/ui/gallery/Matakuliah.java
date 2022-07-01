package com.example.uas_078.ui.gallery;

public class Matakuliah {
    private String id;
    private String matkul;
    private String kode;
    private String dosen;
    private String sks;

    public Matakuliah(String id, String matkul, String kode, String dosen, String sks) {
        this.id = id;
        this.matkul = matkul;
        this.kode = kode;
        this.dosen = dosen;
        this.sks = sks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }
}
