package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.hongocman.quanlymonhoc.adapter.ThoiKhoaBieuDescriptionAdapter;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuDescriptionModel;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuTable;

import java.util.ArrayList;
import java.util.List;

public class ListThoiKhoaBieuActivity extends AppCompatActivity {

    private ListView lvMonHoc;
    private FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        getSupportActionBar().setTitle("Thời khóa biểu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvMonHoc = (ListView)findViewById(R.id.lv_sinh_vien);
        btnAdd = (FloatingActionButton)findViewById(R.id.fab_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListThoiKhoaBieuActivity.this, InsertSinhVienActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        loadThoiKhoaBieu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ListThoiKhoaBieuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private  void loadThoiKhoaBieu(){
        List<ThoiKhoaBieuDescriptionModel> lst = new ArrayList<>();
        ThoiKhoaBieuTable table = new ThoiKhoaBieuTable(this);
        lst = table.getAllOrderByThuBuoi();
        ThoiKhoaBieuDescriptionAdapter adapter = new ThoiKhoaBieuDescriptionAdapter(this, lst);
        lvMonHoc.setAdapter(adapter);
    }
}
