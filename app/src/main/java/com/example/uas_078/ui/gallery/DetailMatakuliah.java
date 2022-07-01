package com.example.uas_078.ui.gallery;

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
import com.example.uas_078.databinding.FragmentDetailMatakuliahBinding;
import com.example.uas_078.databinding.FragmentFormMatakuliahBinding;

public class DetailMatakuliah extends Fragment {

    private FragmentDetailMatakuliahBinding binding;
    private EditText mk_nama, mk_kode, mk_dosen, mk_sks;
    private Button ubah, hapus;
    private DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailMatakuliahBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mk_nama = root.findViewById(R.id.detail_mk_nama);
        mk_kode = root.findViewById(R.id.detail_mk_kode);
        mk_dosen = root.findViewById(R.id.detail_mk_dosen);
        mk_sks = root.findViewById(R.id.detail_mk_sks);
        ubah = root.findViewById(R.id.detail_mk_ubah);
        hapus = root.findViewById(R.id.detail_mk_hapus);

        db = new DBHelper(root.getContext());
        String mk_id = this.getArguments().getString("id");
        Cursor cursor = db.mk_ambilSatu(mk_id);
        while (cursor.moveToNext()) {
            mk_nama.setText(cursor.getString(1));
            mk_kode.setText(cursor.getString(2));
            mk_dosen.setText(cursor.getString(3));
            mk_sks.setText(cursor.getString(4));
        }

        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin ubah matakuliah " + mk_nama.getText().toString().trim() + "?");
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long result = db.mk_ubah(mk_id, mk_nama.getText().toString().trim(),mk_kode.getText().toString().trim(),mk_dosen.getText().toString().trim(),mk_sks.getText().toString().trim());
                        if(result != -1){
                            Navigation.findNavController(view).navigate(R.id.action_detailMatakuliah_to_nav_gallery);
                        }
                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Anda batal mengubah matakuliah", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin hapus data " + mk_nama.getText().toString().trim() + "?");
//                alert.setIcon(R.drawable.logo);
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long result = db.mk_hapus(mk_id);
                        if(result != -1){
                            Navigation.findNavController(view).navigate(R.id.action_detailMatakuliah_to_nav_gallery);
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