package com.example.internship_task_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private List<photo> photoList;
    String text="Not Available!";
    Integer num;
    public MyAdapter(Context context,List<photo> images) {
        mContext=context;
        photoList = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        photo photolist=photoList.get(position);
        TextView textView = holder.title;
        if (photolist.getTitle()!=null) {
            textView.setText(photolist.getTitle());
        }else {
            textView.setText(text.toString());
        }
        ImageView imageView=holder.imageView;
        Glide.with(mContext).load(photolist.getUrl_s()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textViewName);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }
}
