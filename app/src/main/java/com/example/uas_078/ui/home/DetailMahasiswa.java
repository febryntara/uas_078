package com.example.uas_078.ui.home;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas_078.DBHelper;
import com.example.uas_078.R;
import com.example.uas_078.databinding.FragmentDetailMahasiswaBinding;
import com.example.uas_078.databinding.FragmentFormMahasiswaBinding;

public class DetailMahasiswa extends Fragment {
    private FragmentDetailMahasiswaBinding binding;
    private EditText mhs_nama, mhs_prodi, mhs_nim, mhs_jurusan;
    private Button ubah, hapus;
    private DBHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailMahasiswaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mhs_nama = root.findViewById(R.id.detail_mhs_nama);
        mhs_prodi = root.findViewById(R.id.detail_mhs_prodi);
        mhs_nim = root.findViewById(R.id.detail_mhs_nim);
        mhs_jurusan = root.findViewById(R.id.detail_mhs_jurusan);
        ubah = root.findViewById(R.id.detail_mhs_ubah);
        hapus = root.findViewById(R.id.detail_mhs_hapus);

        db = new DBHelper(root.getContext());
        String mhs_id = this.getArguments().getString("id");
        Cursor cursor = db.mhs_ambilSatu(mhs_id);
        while (cursor.moveToNext()) {
            mhs_nama.setText(cursor.getString(1));
            mhs_prodi.setText(cursor.getString(2));
            mhs_nim.setText(cursor.getString(3));
            mhs_jurusan.setText(cursor.getString(4));
        }

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin ubah data " + mhs_nama.getText().toString().trim() + "?");
//                alert.setIcon(R.drawable.logo);
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long result = db.mhs_ubah(mhs_id, mhs_nama.getText().toString().trim(),mhs_prodi.getText().toString().trim(),mhs_nim.getText().toString().trim(),mhs_jurusan.getText().toString().trim());
                        if(result != -1){
                            Navigation.findNavController(view).navigate(R.id.action_detailMahasiswa_to_nav_home);
                        }
                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Anda batal mengubah data", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin hapus data " + mhs_nama.getText().toString().trim() + "?");
//                alert.setIcon(R.drawable.logo);
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long result = db.mhs_hapus(mhs_id);
                        if(result != -1){
                            Navigation.findNavController(view).navigate(R.id.action_detailMahasiswa_to_nav_home);
                        }
                    }
                })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Anda batal menghapus data", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });



        return root;
    }
}