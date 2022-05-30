package com.adi.passwordmanager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordViewHolder> {
    private ArrayList<PasswordListItem> pPasswordList;
    private OnItemClickListener pListener;

    public interface OnItemClickListener {
        void onItemClick(int pos);
        void onDeleteClick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        pListener = listener;
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder {
        public TextView pTextView1;
        public TextView pTextView2;
        public ImageView pDeleteImage;

        public PasswordViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            pTextView1 = itemView.findViewById(R.id.webName);
            pTextView2 = itemView.findViewById(R.id.userName);
            pDeleteImage = itemView.findViewById(R.id.delBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });

            pDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(pos);
                        }
                    }
                }
            });
        }
    }

    public PasswordListAdapter(ArrayList<PasswordListItem> passwordList){
        pPasswordList = passwordList;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.passwords, parent, false);
        PasswordViewHolder pvh = new PasswordViewHolder(v, pListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        PasswordListItem currentItem = pPasswordList.get(position);
        holder.pTextView1.setText(currentItem.getText1());
        holder.pTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return pPasswordList.size();
    }
}
