package com.example.projet_major_simon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.collection.ArraySet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet_major_simon.Tache;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.MyViewHolder> {
    public List<Tache> list;
    Context context;
    String username;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;
        public TextView tvPourcentage;
        public TextView tvTempsEcouler;
        public TextView tvDate;
        public LinearLayout linearLayout;

        public MyViewHolder(LinearLayout v) {
            super(v);
            tvName = v.findViewById(R.id.tvName);
            tvPourcentage = v.findViewById(R.id.tvPourcentage);
            tvTempsEcouler = v.findViewById(R.id.tvTempsEcouler);
            tvDate = v.findViewById(R.id.tvDate);
            linearLayout = v.findViewById(R.id.linear_Layout);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TacheAdapter() {
        list = new ArrayList<>();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public TacheAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Tache TacheCourante = list.get(position);
        holder.tvName.setText(TacheCourante.name);
        holder.tvPourcentage.setText(TacheCourante.percentageDone+"%");
        holder.tvTempsEcouler.setText(TacheCourante.percentageTimeSpent+" / 7");
        holder.tvDate.setText(format.format(Date.parse(TacheCourante.deadline.toString())));// TODO setText sur un integer crash


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ConsultationActivity.class);
                i.putExtra("name",TacheCourante.name);
                i.putExtra("Pourcentage",TacheCourante.percentageDone+"%");
                i.putExtra("TempsE",TacheCourante.percentageTimeSpent+" / 7");
                i.putExtra("Date",TacheCourante.deadline.toString());
                i.putExtra("username", username);
                context.startActivity(i);
            }
        });

    }

    // renvoie la taille de la liste
    @Override
    public int getItemCount() {
        return list.size();
    }

}

