package ru.job4j.total.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.job4j.total.R;
import ru.job4j.total.model.FileModel;
import ru.job4j.total.model.FileTree;
import ru.job4j.total.model.ITree;

public class MainActivity extends AppCompatActivity {

    private TotalRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ITree fileTree = new FileTree();
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("parentAbsolutePath") != null) {
                initRecyclerView(fileTree.getChildrenByParent(savedInstanceState.getString("parentAbsolutePath")));
            }
        } else {
            FileModel rootDir = fileTree.getRootDir();
            initRecyclerView(fileTree.getChildrenByParent(rootDir.getAbsolutePath()));
        }


        Toolbar myChildToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView(List<FileModel> dirs) {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        adapter = new TotalRecyclerAdapter(dirs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        adapter.onBackPressed();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (adapter != null) {
            outState.putString("parentAbsolutePath", adapter.getParentAbsolutePath());
        }
    }
}
