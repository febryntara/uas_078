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
import com.example.uas_078.databinding.FragmentHomeBinding;

public class FormMahasiswa extends Fragment {

    private FragmentFormMahasiswaBinding binding;
    private EditText mhs_nama, mhs_prodi, mhs_nim, mhs_jurusan;
    private Button simpan;
    private DBHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormMahasiswaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mhs_nama = root.findViewById(R.id.input_mhs_nama);
        mhs_prodi = root.findViewById(R.id.input_mhs_prodi);
        mhs_nim = root.findViewById(R.id.input_mhs_nim);
        mhs_jurusan = root.findViewById(R.id.input_mhs_jurusan);
        simpan = root.findViewById(R.id.input_mhs_tambah);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Yakin simpan data " + mhs_nama.getText().toString().trim() + "?");
                alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new DBHelper(root.getContext());
                        long result = db.mhs_tambah(mhs_nama.getText().toString().trim(),mhs_prodi.getText().toString().trim(),mhs_nim.getText().toString().trim(),mhs_jurusan.getText().toString().trim());
                        if(result != -1){
//                            Navigation.findNavController(view).navigate(R.id.action_formMahasiswa_to_nav_home);
                            mhs_nama.setText("");
                            mhs_prodi.setText("");
                            mhs_nim.setText("");
                            mhs_jurusan.setText("");
                        }
                    }
                })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Anda batal menyimpan data", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();

            }
        });



        return root;
    }
}