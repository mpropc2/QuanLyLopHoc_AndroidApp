package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.hongocman.quanlymonhoc.adapter.MonHocAdapter;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.MonHocTable;

import java.util.ArrayList;
import java.util.List;

public class ListMonHocActivity extends AppCompatActivity {

    private ListView lvMonHoc;
    private FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        getSupportActionBar().setTitle("Danh sách môn học");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvMonHoc = (ListView)findViewById(R.id.lv_sinh_vien);
        btnAdd = (FloatingActionButton)findViewById(R.id.fab_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonHocActivity.this, InsertSinhVienActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        loadSinhVien();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ListMonHocActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private  void loadSinhVien(){
        List<MonHocModel> lst = new ArrayList<>();
        MonHocTable table = new MonHocTable(this);
        lst = table.getAll();
        MonHocAdapter adapter = new MonHocAdapter(this, lst);
        lvMonHoc.setAdapter(adapter);
    }
}
