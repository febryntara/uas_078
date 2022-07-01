package com.example.uas_078;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uas_078.ui.gallery.Matakuliah;

import java.util.List;

public class GridAdapter extends ArrayAdapter<Matakuliah> {
      public GridAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= layoutInflater.inflate(R.layout.item_matkul,parent,false);

        Matakuliah matkul = getItem(position);
        TextView mk_nama = v.findViewById(R.id.mk_nama);
        TextView mk_kode = v.findViewById(R.id.mk_kode);
        TextView mk_dosen = v.findViewById(R.id.mk_dosen);
        TextView mk_sks = v.findViewById(R.id.mk_sks);

        mk_nama.setText(matkul.getMatkul());
        mk_kode.setText(matkul.getKode());
        mk_dosen.setText(matkul.getDosen());
        mk_sks.setText(matkul.getSks());

        return v;
    }
}
