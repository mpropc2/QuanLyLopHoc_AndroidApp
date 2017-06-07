package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.hongocman.quanlymonhoc.adapter.KeyValuePairAdapter;
import com.hongocman.quanlymonhoc.adapter.ReportDiemDanhAdapter;
import com.hongocman.quanlymonhoc.models.BuoiHocModel;
import com.hongocman.quanlymonhoc.models.BuoiHocTable;
import com.hongocman.quanlymonhoc.models.DiemDanhModel;
import com.hongocman.quanlymonhoc.models.DiemDanhTable;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.KeyValuePairModel;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.MonHocTable;
import com.hongocman.quanlymonhoc.models.ReportDiemDanhModel;
import com.hongocman.quanlymonhoc.models.SinhVienModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportDiemDanhActivity extends AppCompatActivity {

    private Spinner spMonHoc;
    private ListView lvSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_diem_danh);
        getSupportActionBar().setTitle("Sinh viên vắng theo môn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvSinhVien = (ListView)findViewById(R.id.lv_sinh_vien);
        spMonHoc = (Spinner)findViewById(R.id.sp_mon_hoc);
        spMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                KeyValuePairModel item = (KeyValuePairModel)parent.getItemAtPosition(position);
                loadDSSVVangMat(item.getsID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReportDiemDanhModel item = (ReportDiemDanhModel)parent.getItemAtPosition(position);
                goToDSSVVangMat(item.getMonHocID(), item.getSinhVienID());
            }
        });
        initSpinnerMonHoc();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ReportDiemDanhActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSpinnerMonHoc(){
        ArrayList<KeyValuePairModel> lstKey = new ArrayList<>();
        MonHocTable bm = new MonHocTable(this);
        List<MonHocModel> lstBH = bm.getAll();
        KeyValuePairAdapter adapter = new KeyValuePairAdapter(this, R.layout.my_spinner_style,
                lstKey);
        if(lstBH != null && lstBH.size()>0){
            for(int i =0;i<lstBH.size();i++)
                lstKey.add(new KeyValuePairModel(lstBH.get(i).getID(), lstBH.get(i).getDescription()));
        }
        spMonHoc.setAdapter(adapter);
    }

    private void loadDSSVVangMat(String monHocID){
        List<ReportDiemDanhModel> lst = new ArrayList<>();
        DiemDanhTable ddTable = new DiemDanhTable(this);
        List<DiemDanhModel> lstDD = ddTable.getDiemDanhByMonHoc(monHocID, false);
        if(lstDD != null && lstDD.size()>0){
            ReportDiemDanhModel item = new ReportDiemDanhModel();
            item.setMonHocID(lstDD.get(0).getMonHocID());
            item.setMonHocName("");
            item.setSinhVienFullName("");
            item.setSinhVienID(lstDD.get(0).getSinhVienID());
            item.setTotal(1);
            for(int i=1;i<lstDD.size();i++){
                //Cung 1 SV

                if(lstDD.get(i).getSinhVienID().toLowerCase().equals(item.getSinhVienID().toLowerCase())){
                    item.setTotal(item.getTotal()+1);
                }else{
                    int s = 0;
                    if(item.getTotal()>1){
                        s = item.getTotal();
                    }

                    lst.add(item);
                    item = new ReportDiemDanhModel();
                    item.setMonHocID(lstDD.get(i).getMonHocID());
                    item.setMonHocName("");
                    item.setSinhVienFullName("");
                    item.setSinhVienID(lstDD.get(i).getSinhVienID());
                    item.setTotal(1);
                }
            }
            lst.add(item);
        }
        ReportDiemDanhAdapter adapter = new ReportDiemDanhAdapter(this, lst);
        lvSinhVien.setAdapter(adapter);
    }

    private void goToDSSVVangMat(String monHocID, String sinhVienID){
        Intent intent = new Intent(this, SinhVienVangMatActivity.class);
        intent.putExtra(SinhVienVangMatActivity.TAG_MONHOC_ID, monHocID);
        intent.putExtra(SinhVienVangMatActivity.TAG_SINHVIEN_ID, sinhVienID);
        startActivity(intent);
    }


}
