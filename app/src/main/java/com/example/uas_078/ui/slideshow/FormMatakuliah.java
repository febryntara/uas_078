package com.example.uas_078.ui.slideshow;

import android.content.DialogInterface;
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
import com.example.uas_078.databinding.FragmentFormMahasiswaBinding;
import com.example.uas_078.databinding.FragmentFormMatakuliahBinding;

public class FormMatakuliah extends Fragment {
    private FragmentFormMatakuliahBinding binding;
    private EditText mk_nama, mk_kode, mk_dosen, mk_sks;
    private Button simpan;
    private DBHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormMatakuliahBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mk_nama = root.findViewById(R.id.input_mk_nama);
        mk_kode = root.findViewById(R.id.input_mk_kode);
        mk_dosen = root.findViewById(R.id.input_mk_dosen);
        mk_sks = root.findViewById(R.id.input_mk_sks);
        simpan = root.findViewById(R.id.input_mk_tambah);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin simpan matakuliah " + mk_nama.getText().toString().trim() + "?");
//                alert.setIcon(R.drawable.logo);
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new DBHelper(root.getContext());
                        long result = db.mk_tambah(mk_nama.getText().toString().trim(),mk_kode.getText().toString().trim(),mk_dosen.getText().toString().trim(),mk_sks.getText().toString().trim());
                        if(result != -1){
                            Navigation.findNavController(view).navigate(R.id.action_formMatakuliah_to_nav_gallery);
                        }
                    }
                })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Anda batal menyimpan matakuliah", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();

            }
        });

        return root;
    }
}