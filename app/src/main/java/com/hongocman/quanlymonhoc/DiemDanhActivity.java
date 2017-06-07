package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.hongocman.quanlymonhoc.adapter.DiemDanhAdapter;
import com.hongocman.quanlymonhoc.adapter.KeyValuePairAdapter;
import com.hongocman.quanlymonhoc.models.BuoiHocModel;
import com.hongocman.quanlymonhoc.models.BuoiHocTable;
import com.hongocman.quanlymonhoc.models.DangKyMonHocModel;
import com.hongocman.quanlymonhoc.models.DangKyMonHocTable;
import com.hongocman.quanlymonhoc.models.DiemDanhTable;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.KeyValuePairModel;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.MonHocTable;
import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.SinhVienTable;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuModel;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiemDanhActivity extends AppCompatActivity {

    private Spinner spBuoiHoc, spMonHoc;
    private ListView lvSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh);
        getSupportActionBar().setTitle("Điểm danh");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spBuoiHoc = (Spinner)findViewById(R.id.sp_buoi_hoc);
        lvSinhVien = (ListView)findViewById(R.id.lv_sinh_vien);
        spBuoiHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                KeyValuePairModel item = (KeyValuePairModel) parent.getItemAtPosition(position);
                initSpinnerMonHoc(item.getsID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spMonHoc = (Spinner)findViewById(R.id.sp_mon_hoc);
        spMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                KeyValuePairModel item = (KeyValuePairModel)parent.getItemAtPosition(position);
                initListView(item.getsID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initSpinnerBuoiHoc();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(DiemDanhActivity.this, ListSinhVienActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    private void initSpinnerBuoiHoc(){
        Calendar curDate = Calendar.getInstance();
        int curMinute = curDate.get(Calendar.HOUR_OF_DAY) * 60;
        BuoiHocTable table = new BuoiHocTable(this);
        List<BuoiHocModel> lst = table.getBuoiHocHomNay();
        ArrayList<KeyValuePairModel> lstKey = new ArrayList<>();
        if(lst != null && lst.size() >0){
            for(int i=0;i<lst.size();i++){
                if(curMinute >= lst.get(i).getStartTime() && curMinute <= lst.get(i).getEndTime() )
                    lstKey.add(new KeyValuePairModel(lst.get(i).getID(), lst.get(i).getDescription()));
            }
        }
        KeyValuePairAdapter adapter = new KeyValuePairAdapter(this, R.layout.my_spinner_style,
                lstKey);
        spBuoiHoc.setAdapter(adapter);
    }

    private void initSpinnerMonHoc(String buoiHocID){
        ThoiKhoaBieuTable tkbTable = new ThoiKhoaBieuTable(this);
        Calendar currentDate = Calendar.getInstance();
        //Lay ca Mon hoc ngay hom nay va buoi nay
        List<ThoiKhoaBieuModel> lstTKB = tkbTable.getThoiKhoaBieuTheoThuBuoiHoc(buoiHocID, String.valueOf(currentDate.get(Calendar.DAY_OF_WEEK)));
        ArrayList<KeyValuePairModel> lstKey = new ArrayList<>();
        if(lstTKB != null && lstTKB.size()>0){
            MonHocTable monHocTable = new MonHocTable(this);
            for(int i = 0;i<lstTKB.size();i++){
                //Lay Buoi hoc theo ID
                MonHocModel item = monHocTable.get(lstTKB.get(0).getMonHocID());
                if(item != null)
                    lstKey.add(new KeyValuePairModel(item.getID(), item.getDescription()));
            }
        }
        KeyValuePairAdapter adapter = new KeyValuePairAdapter(this, R.layout.my_spinner_style,
                lstKey);
        spMonHoc.setAdapter(adapter);
    }

    private void initListView(String monHocID){
        KeyValuePairModel buoiHoc = (KeyValuePairModel) spBuoiHoc.getSelectedItem();
        Calendar curDate = Calendar.getInstance();
        String thuID = String.valueOf(curDate.get(Calendar.DAY_OF_WEEK));

        DangKyMonHocTable dkmhTable = new DangKyMonHocTable(this);
        List<DangKyMonHocModel> lstDKMH = dkmhTable.getSinhVienBy(monHocID);
        List<SinhVienModel> lstSV = new ArrayList<>();
        if(lstDKMH != null && lstDKMH.size()> 0){
            SinhVienTable svTable = new SinhVienTable(this);
            for(int i= 0;i<lstDKMH.size();i++){
                lstSV.add(svTable.getItem(lstDKMH.get(i).getSinhVienID()));
            }
        }
        insertDiemDanh(lstSV);
        DiemDanhAdapter adapter = new DiemDanhAdapter(this, lstSV, thuID, buoiHoc.getsID(), monHocID);
        lvSinhVien.setAdapter(adapter);
    }

    private void insertDiemDanh(List<SinhVienModel> lstSinhVien){
        KeyValuePairModel buoiHoc = (KeyValuePairModel) spBuoiHoc.getSelectedItem();
        KeyValuePairModel monHoc = (KeyValuePairModel) spMonHoc.getSelectedItem();
        Calendar curDate = Calendar.getInstance();
        String thuID = String.valueOf(curDate.get(Calendar.DAY_OF_WEEK));
        for(SinhVienModel sv:lstSinhVien){
            DiemDanhTable table = new DiemDanhTable(this);
            try{
                table.insertDiemDanh(thuID, buoiHoc.getsID(), monHoc.getsID(), sv.getID(), false, sv);
            }catch (Exception e){
                Helper.alertDialogResult(this, e.getMessage());
            }
        }
    }
}
