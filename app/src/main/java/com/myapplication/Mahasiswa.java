package com.myapplication;

public class Mahasiswa {

    private long id;
    private String nama_mahasiswa;
    private String alamat_mahasiswa;
    private String nim_mahasiswa;

    public Mahasiswa() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }


    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getAlamat_mahasiswa() {
        return alamat_mahasiswa;
    }

    public void setAlamat_mahasiswa(String alamat_mahasiswa) {
        this.alamat_mahasiswa = alamat_mahasiswa;
    }

    public String getNIM_mahasiswa() {
        return nim_mahasiswa;
    }


    public void setNIM_mahasiswa(String nim_mahasiswa) {
        this.nim_mahasiswa = nim_mahasiswa;
    }

    @Override
    public String toString()
    {
        return "Nama : "+ nama_mahasiswa;
    }
}