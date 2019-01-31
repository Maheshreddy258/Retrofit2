package com.example.mahesh.retrofit2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<ListUser.Datum> android;
    private static ClickListener clickListener;

    public RecycleAdapter( List<ListUser.Datum>
                                   android) {
        this.android = android;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder viewHolder, int i) {



        viewHolder.tv_typeofcall.setText(android.get(i).firstName);
        viewHolder.tv_name.setText(android.get(i).lastName);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView tv_typeofcall,tv_name;
        public ViewHolder(View view) {
            super(view);

            tv_typeofcall = (TextView)view.findViewById(R.id.tv_fname);
            tv_name = (TextView)view.findViewById(R.id.tv_lname);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        RecycleAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}
