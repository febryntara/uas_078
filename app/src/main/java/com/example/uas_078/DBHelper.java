package com.example.uas_078;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAMA="dbkampus.db";
    private static final int VERSI=1;

//    properti mahasiswa
    private static final String TB_MHS="tb_mahasiswa";
    private static final String MHS_ID="id";
    private static final String MHS_NAMA="nama";
    private static final String MHS_PRODI="prodi";
    private static final String MHS_NIM="nim";
    private static final String MHS_JURUSAN="jurusan";

//    properti matakuliah
    private static final String TB_MK="tb_matakuliah";
    private static final String MK_ID="id";
    private static final String MK_NAMA="nama";
    private static final String MK_KODE="kode";
    private static final String MK_DSN="dosen";
    private static final String MK_SKS="jumlah_sks";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAMA, null, VERSI);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//      buat tabel mahasiswa
        String query_mhs ="CREATE TABLE " + TB_MHS + "(" +
                MHS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MHS_NAMA + " TEXT," +
                MHS_PRODI + " TEXT," +
                MHS_NIM+ " TEXT," +
                MHS_JURUSAN + " TEXT );";
        sqLiteDatabase.execSQL(query_mhs);

//      buat tabel matakuliah
        String query_mk ="CREATE TABLE " + TB_MK + "(" +
                MK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MK_NAMA + " TEXT," +
                MK_KODE + " TEXT," +
                MK_DSN+ " TEXT," +
                MK_SKS + " TEXT );";
        sqLiteDatabase.execSQL(query_mk);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_MHS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_MK);
    }

//    crud mahasiswa
    public long mhs_tambah(String nama, String prodi, String nim, String jurusan){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MHS_NAMA,nama);
        cv.put(MHS_PRODI,prodi);
        cv.put(MHS_NIM,nim);
        cv.put(MHS_JURUSAN,jurusan);

        long result= db.insert(TB_MHS,null, cv);

        if (result == -1)
        {
            Toast.makeText(context, "Penyimpanan Data Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Penyimpanan Data Sukses", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public Cursor mhs_ambil() {
        String query = "SELECT * FROM " + TB_MHS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor mhs_ambilSatu(String id) {
        String query = "SELECT * FROM " + TB_MHS + " WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public long mhs_ubah(String row_id, String nama, String prodi, String nim, String jurusan)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MHS_NAMA,nama);
        cv.put(MHS_PRODI,prodi);
        cv.put(MHS_NIM,nim);
        cv.put(MHS_JURUSAN,jurusan);

        long result= db.update(TB_MHS,cv,"id=?", new String[]{row_id});

        if (result == -1)
        {
            Toast.makeText(context, "Proses Update Gagal", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Proses Update Sukses", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public long mhs_hapus(String row_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete(TB_MHS,"id=?", new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Proses Hapus Gagal", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Proses Hapus Sukses", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

//    crud matakuliah
    public long mk_tambah(String matkul, String kode, String dosen, String sks){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MK_NAMA,matkul);
        cv.put(MK_KODE,kode);
        cv.put(MK_DSN,dosen);
        cv.put(MK_SKS,sks);

        long result= db.insert(TB_MK,null, cv);

        if (result == -1)
        {
            Toast.makeText(context, "Penyimpanan Data Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Penyimpanan Data Sukses", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public Cursor mk_ambil() {
        String query = "SELECT * FROM " + TB_MK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor mk_ambilSatu(String id) {
        String query = "SELECT * FROM " + TB_MK + " WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public long mk_ubah(String row_id, String matkul, String kode, String dosen, String sks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MK_NAMA,matkul);
        cv.put(MK_KODE,kode);
        cv.put(MK_DSN,dosen);
        cv.put(MK_SKS,sks);

        long result= db.update(TB_MK,cv,"id=?", new String[]{row_id});

        if (result == -1)
        {
            Toast.makeText(context, "Proses Update Gagal", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Proses Update Sukses", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public long mk_hapus(String row_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result= db.delete(TB_MK,"id=?", new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Proses Hapus Gagal", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Proses Hapus Sukses", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
}
