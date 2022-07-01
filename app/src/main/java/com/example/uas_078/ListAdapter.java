package com.example.uas_078;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uas_078.ui.home.Mahasiswa;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Mahasiswa> {
    public ListAdapter(@NonNull Context context, int resource, @NonNull List objects){
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int posititon, @Nullable View convertView, @NonNull ViewGroup parent ){
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.item_mhs, parent, false);

        Mahasiswa mhs = getItem(posititon);

        TextView textNama=(TextView) v.findViewById(R.id.mhs_nama);
        TextView textProdi=(TextView) v.findViewById(R.id.mhs_prodi);
        TextView textNim=(TextView) v.findViewById(R.id.mhs_nim);
        TextView textJurusan=(TextView) v.findViewById(R.id.mhs_jurusan);

        textNama.setText(mhs.getNama());
        textProdi.setText(mhs.getProdi());
        textNim.setText(mhs.getNim());
        textJurusan.setText(mhs.getJurusan());
        return v;
    }
}
