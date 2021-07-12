package com.oysterkode.laundry.AdaptersStudent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oysterkode.laundry.Models.StudentPRN;
import com.oysterkode.laundry.R;
import com.oysterkode.laundry.StaticActivity;
import com.oysterkode.laundry.databinding.ContainerPage3Binding;

import java.util.ArrayList;

public class StudentPRNAdapter extends RecyclerView.Adapter<StudentPRNAdapter.UsersViewHolder>{

    Context context;
    ArrayList <StudentPRN> Students;

    public StudentPRNAdapter(Context context, ArrayList<StudentPRN> students) {
        this.context = context;
        this.Students = students;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.container_page3,parent,false);
        return new UsersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        StudentPRN student = Students.get(position);


        holder.binding.condate.setText(student.getDate());
        holder.binding.contotal.setText(student.getTotal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, StaticActivity.class);
                i.putExtra("SPrn",student.getStudentPRN());
                i.putExtra("Sdate",student.getDate());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Students.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        ContainerPage3Binding binding;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ContainerPage3Binding.bind(itemView);
        }
    }
}
