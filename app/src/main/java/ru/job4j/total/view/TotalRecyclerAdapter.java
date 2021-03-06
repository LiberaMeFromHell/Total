package ru.job4j.total.view;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import ru.job4j.total.R;
import ru.job4j.total.model.FileModel;
import ru.job4j.total.model.FileTree;
import ru.job4j.total.model.ITree;

public class TotalRecyclerAdapter extends RecyclerView.Adapter<TotalRecyclerAdapter.TotalViewHolder> {

    private List<FileModel> fileModels;
    private String parentAbsolutePath;
    private AppCompatActivity activity;

    public TotalRecyclerAdapter(List<FileModel> fileModels, AppCompatActivity activity) {
        this.fileModels = fileModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler, parent, false);
        return new TotalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalViewHolder holder, final int position) {

        final FileModel currentFile = fileModels.get(position);

        TextView textView = holder.itemView.findViewById(R.id.textView);
        textView.setText("" + currentFile.getName());

        ImageView imageView = holder.itemView.findViewById(R.id.imageView);
        if (currentFile.getDirectory()) {
            Glide.with(imageView).load(R.drawable.ic_folder_black_24dp).into(imageView);
        } else {
            Glide.with(imageView).load(R.drawable.ic_insert_drive_file_black_24dp).into(imageView);

        }

        View itemLayout = holder.itemView.findViewById(R.id.itemLayout);
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFile.getName().contains(".")) {
                    if ((currentFile.getName().substring(currentFile.getName().lastIndexOf("."))).equals("mp3")) {
                        Log.d("audio", "onClick: " + currentFile.getAbsolutePath());
                        Intent intent = new Intent("audio/mp3", (Uri.parse(currentFile.getAbsolutePath())));
                        activity.startActivity(intent);
                    }
                }

                ITree fileTree = new FileTree();
                parentAbsolutePath = fileModels.get(position).getAbsolutePath();
                List<FileModel> list;
                if (((list) = fileTree.getChildrenByParent(fileModels.get(position).getAbsolutePath())) != null) {
                    fileModels = list;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileModels.size();
    }

    public void onBackPressed() {
        ITree fileTree = new FileTree();
        FileModel parent;
        if ((parent = fileTree.getParentByChild(parentAbsolutePath)) != null) {
            parentAbsolutePath = parent.getAbsolutePath();
            fileModels = fileTree.getChildrenByParent(parent.getAbsolutePath());
            notifyDataSetChanged();
        }
    }

    public class TotalViewHolder extends RecyclerView.ViewHolder {

        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public String getParentAbsolutePath() {
        return parentAbsolutePath;
    }

    public void setParentAbsolutePath(String parentAbsolutePath) {
        this.parentAbsolutePath = parentAbsolutePath;
    }
}
