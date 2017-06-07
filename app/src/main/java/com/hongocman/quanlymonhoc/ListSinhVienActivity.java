package com.hongocman.quanlymonhoc;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hongocman.quanlymonhoc.adapter.SinhVienAdapter;
import com.hongocman.quanlymonhoc.models.QLSV;
import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.SinhVienTable;

import java.util.ArrayList;
import java.util.List;

public class ListSinhVienActivity extends AppCompatActivity {

    private ListView lvSinhVien;
    private FloatingActionButton btnAdd;
    SinhVienAdapter adapter;
    SinhVienTable svtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        getSupportActionBar().setTitle("Danh sách Sinh Viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        svtb = new SinhVienTable(this);
        lvSinhVien = (ListView)findViewById(R.id.lv_sinh_vien);
        lvSinhVien.setOnItemClickListener(new ItemClickListener());
        lvSinhVien.setOnItemLongClickListener(new ItemLongClickRemove());
        btnAdd = (FloatingActionButton)findViewById(R.id.fab_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSinhVienActivity.this, InsertSinhVienActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        loadSinhVien();

        QLSV qlsv = QLSV.get();
        qlsv.setDsSV(new SinhVienTable(this).getAll());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    // do something with s, the entered string
                    String query = s;
                    Toast.makeText(getApplicationContext(),
                            "Tìm sinh viên: " + s, Toast.LENGTH_SHORT).show();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    if(s.isEmpty()){
                        loadSinhVien();
                    }else{
                        loadSinhVienSearch(s);
                    }
                    return false;
                }
            });
        }
        return true;
    }

    private  void loadSinhVienSearch(String s){
        List<SinhVienModel> lst = new ArrayList<SinhVienModel>();
        lst = svtb.getSinhVienSearch(s);
        adapter = new SinhVienAdapter(this,lst);
        lvSinhVien.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ListSinhVienActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (item.getItemId() == R.id.btn_XoaTatCa) {
            final SinhVienTable svtb = new SinhVienTable(this);
            AlertDialog.Builder builder = null;
            Dialog dialog = null;
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Xóa tất cả?").setCancelable(false).setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //HandleSaveAddress(sHouseTypeSelected);
                            svtb.xoaTatCa();
                        }
                    }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,
                                    int id) {
                    dialog.cancel();
                }
            });
            dialog = builder.create();
            dialog.show();
            loadSinhVien();
        }

        return super.onOptionsItemSelected(item);
    }

    class ItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id)
        {
            // đến màng hình suathongtin
            Intent intent = new Intent(ListSinhVienActivity.this, activity_update_sinhvien.class);
            intent.putExtra(activity_update_sinhvien.EXTRA_POSITION, position);
            startActivityForResult(intent, 0);
        }
    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ListSinhVienActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sinh viên này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa ss đang nhấn giữ
                    SinhVienModel sv = adapter.getItem(position);
                    //xóa sản phẩm đang chọn trong Database
                    svtb.delete(sv);
                    //cập nhật lại listview
                    adapter.notifyDataSetChanged();
                    loadSinhVien();
                    QLSV.get().setDsSV(svtb.getAll());
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }

    private  void loadSinhVien(){
        List<SinhVienModel> lst = new ArrayList<SinhVienModel>();
        SinhVienTable sv = new SinhVienTable(this);
        lst = sv.getAll();
        adapter = new SinhVienAdapter(this,lst);
        lvSinhVien.setAdapter(adapter);
    }
}
