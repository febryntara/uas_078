package com.example.uas_078.ui.gallery;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.uas_078.DBHelper;
import com.example.uas_078.GridAdapter;
import com.example.uas_078.ListAdapter;
import com.example.uas_078.R;
import com.example.uas_078.databinding.FragmentGalleryBinding;
import com.example.uas_078.databinding.FragmentHomeBinding;
import com.example.uas_078.ui.home.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private List<Matakuliah> mk_array;
    private DBHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        gridView = (GridView) root.findViewById(R.id.gridView);
        getMatakuliah(root);
        gridAdapter = new GridAdapter(root.getContext(), R.layout.item_matkul, mk_array);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(root.getContext(), mk_array.get(i).getNama(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("id", mk_array.get(i).getId());
                Navigation.findNavController(root).navigate(R.id.action_nav_gallery_to_detailMatakuliah, bundle);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Matakuliah> getMatakuliah(View view){
        mk_array = new ArrayList<>();
        db = new DBHelper(view.getContext());
        Cursor cursor = db.mk_ambil();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "Data matakuliah kosong!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getContext(), cursor.getCount() + " matakuliah ditemukan", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String matkul = cursor.getString(1);
                String kode = cursor.getString(2);
                String dosen = cursor.getString(3);
                String sks = cursor.getString(4);
                mk_array.add(new Matakuliah(id, matkul, kode, dosen, sks));
            }
        }

        return mk_array;

    }
}