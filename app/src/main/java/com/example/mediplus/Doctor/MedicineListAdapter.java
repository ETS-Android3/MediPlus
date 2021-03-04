package com.example.mediplus.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mediplus.R;

import java.util.ArrayList;


public class MedicineListAdapter extends RecyclerView.Adapter<com.example.mediplus.Doctor.MedicineListAdapter.MyViewHolder>  {

    ArrayList<MyObject> list;
    Context context;

    public MedicineListAdapter(){}

    public MedicineListAdapter(Context context, ArrayList<MyObject> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_medicine_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).objectName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.medicine_name);
        }
    }
}
