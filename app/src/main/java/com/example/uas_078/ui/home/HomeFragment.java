package com.example.uas_078.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.uas_078.DBHelper;
import com.example.uas_078.ListAdapter;
import com.example.uas_078.R;
import com.example.uas_078.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView listView;
    private ListAdapter listAdapter;
    private List<Mahasiswa> mhs_array;
    private DBHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = (ListView) root.findViewById(R.id.listView);
        getMahasiswa(root);
        listAdapter = new ListAdapter(root.getContext(), R.layout.item_mhs, mhs_array);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(root.getContext(), mhs_array.get(i).getNama(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("id", mhs_array.get(i).getId());
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_detailMahasiswa, bundle);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Mahasiswa> getMahasiswa(View view){
        mhs_array = new ArrayList<>();
        db = new DBHelper(view.getContext());
        Cursor cursor = db.mhs_ambil();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "Data mahasiswa kosong!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getContext(), cursor.getCount() + " mahasiswa ditemukan", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String nama = cursor.getString(1);
                String prodi = cursor.getString(2);
                String nim = cursor.getString(3);
                String jurusan = cursor.getString(4);
//                ar = new Artikel(id, nama, prodi, nim, jurusan);
                mhs_array.add(new Mahasiswa(id, nama, prodi, nim, jurusan));
            }
        }

        return mhs_array;

    }
}