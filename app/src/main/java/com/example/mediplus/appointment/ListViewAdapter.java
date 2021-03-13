package com.example.mediplus.appointment;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.mediplus.Database.DoctorHelperClass;
import com.example.mediplus.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    int i=0;
    Context mContext;
    LayoutInflater inflater;
    List<DoctorHelperClass> doctorList;
    ArrayList<DoctorHelperClass> arrayList;

    public ListViewAdapter(Context context, List<DoctorHelperClass> doctorList) {
        mContext = context;
        this.doctorList = doctorList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<DoctorHelperClass>();
        this.arrayList.addAll(doctorList);
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.row,null);

        TextView circleText = convertView.findViewById(R.id.circle_text);
        TextView fullName = convertView.findViewById(R.id.doctorFullName);

        DoctorHelperClass doctor = doctorList.get(position);

        fullName.setText(doctor.getFullName().trim());
        circleText.setText((doctor.getFullName().charAt(0)+"").toUpperCase());
        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        doctorList.clear();
        if (charText.length()==0){
            doctorList.addAll(arrayList);
        }
        else {
            for (DoctorHelperClass d : arrayList){
                if (d.getFullName().toLowerCase(Locale.getDefault()).contains(charText.trim()) ){
                    doctorList.add(d);
                }
            }
        }
        notifyDataSetChanged();
    }

}
