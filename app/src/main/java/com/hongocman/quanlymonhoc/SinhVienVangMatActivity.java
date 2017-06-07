package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.hongocman.quanlymonhoc.adapter.KeyValuePairAdapter;
import com.hongocman.quanlymonhoc.adapter.ListSinhVienVangMatDiemDanhAdapter;
import com.hongocman.quanlymonhoc.adapter.ReportDiemDanhAdapter;
import com.hongocman.quanlymonhoc.models.DiemDanhModel;
import com.hongocman.quanlymonhoc.models.DiemDanhTable;
import com.hongocman.quanlymonhoc.models.KeyValuePairModel;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.MonHocTable;
import com.hongocman.quanlymonhoc.models.ReportDiemDanhModel;

import java.util.ArrayList;
import java.util.List;

public class SinhVienVangMatActivity extends AppCompatActivity {
    public  static  final String TAG_MONHOC_ID ="TAG_MONHOC_ID";
    public  static  final String TAG_SINHVIEN_ID ="TAG_SINHVIEN_ID";
    private ListView lvSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_diem_danh);
        lvSinhVien = (ListView)findViewById(R.id.lv_sinh_vien);

        getDataFromIntent();
    }

    private void getDataFromIntent(){
        String monHocID= "", sinhVienID ="";
        Intent intent = getIntent();
        if(intent !=null) {
            if (intent.hasExtra(TAG_MONHOC_ID)){
                monHocID = intent.getStringExtra(TAG_MONHOC_ID);
            }
            if (intent.hasExtra(TAG_SINHVIEN_ID)){
                sinhVienID = intent.getStringExtra(TAG_SINHVIEN_ID);
            }
            loadDSSVVangMat(monHocID, sinhVienID);
        }
    }


    private void loadDSSVVangMat(String monHocID, String sinhVienID){

        List<DiemDanhModel> lst = new ArrayList<>();
        DiemDanhTable table = new DiemDanhTable(this);
        lst = table.getDiemDanhByMonHoc(monHocID, sinhVienID);
        ListSinhVienVangMatDiemDanhAdapter adapter = new ListSinhVienVangMatDiemDanhAdapter(this, lst);
        lvSinhVien.setAdapter(adapter);
    }



}
