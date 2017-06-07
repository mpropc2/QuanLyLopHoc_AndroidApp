package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.models.BuoiHocModel;
import com.hongocman.quanlymonhoc.models.BuoiHocTable;
import com.hongocman.quanlymonhoc.models.DangKyMonHocModel;
import com.hongocman.quanlymonhoc.models.DangKyMonHocTable;
import com.hongocman.quanlymonhoc.models.GiangVienModel;
import com.hongocman.quanlymonhoc.models.GiangVienTable;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.KeyValuePairModel;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.MonHocTable;
import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.SinhVienTable;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuModel;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuTable;
import com.hongocman.quanlymonhoc.models.ThuModel;
import com.hongocman.quanlymonhoc.models.ThuTable;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;

/**
 * Created by Laptop on 4/28/2016.
 */
public class ImportExcelAdapter extends BaseAdapter {

    private List<Sheet> mList;
    private Context mContext;

    public ImportExcelAdapter(Context context, List<Sheet> lst){
        this.mContext = context;
        this.mList = lst;
    }
    @Override
    public int getCount() {
        if(mList != null)
            return  mList.size();
        return 0;
    }

    @Override
    public Sheet getItem(int position) {
        if(mList != null)
            return  mList.get(position);
        return  null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_import_sheet, null);
            holder = new ViewHolder();
            holder.spObject = (Spinner)convertView.findViewById(R.id.sp_object);
            holder.spSheet = (Spinner)convertView.findViewById(R.id.sp_sheet);
            holder.btnImport = (Button)convertView.findViewById(R.id.btn_import);
            holder.initOnClick();
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.initObjectSpinner(position);
        holder.initSheetSpinner(position);
        return convertView;
    }



    private class ViewHolder{

        private final  int POSITION_SINH_VIEN =0;
        private final  int POSITION_MON_HOC =1;
        private final  int POSITION_GIANG_VIEN =2;
        private final  int POSITION_BUOI_HOC =3;
        private final  int POSITION_THU =4;
        private final  int POSITION_THOI_KHOA_BIEU =5;
        private final  int POSITION_DIEM_DANH =6;
        private final  int POSITION_DANG_KY_MON_HOC =7;

        private Spinner spSheet, spObject;
        private Button btnImport;


        private void initSheetSpinner(int position){
            if(mList != null && mList.size()>0){
                if(spSheet!=null){
                    if(spSheet.getAdapter() == null) {
                        ArrayList<KeyValuePairModel> lstHouseTypes = new ArrayList<>();
                        for (int i = 0; i < mList.size(); i++) {
                            lstHouseTypes.add(new KeyValuePairModel(i, "Sheet " + String.valueOf(i + 1)));
                        }
                        KeyValuePairAdapter adapter = new KeyValuePairAdapter(mContext, R.layout.my_spinner_style,
                                lstHouseTypes);
                        spSheet.setAdapter(adapter);
                    }
                    try{
                        spSheet.setSelection(position);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        private void initObjectSpinner(int position) {
            if (spObject != null ) {
                if(spObject.getAdapter() == null) {
                    ArrayList<KeyValuePairModel> lstHouseTypes = new ArrayList<>();
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_SINH_VIEN, "Sinh viên "));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_MON_HOC, "Môn học"));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_GIANG_VIEN, "Giảng Viên"));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_BUOI_HOC, "Buổi học"));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_THU, "Thứ"));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_THOI_KHOA_BIEU, "Thời khóa biểu"));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_DANG_KY_MON_HOC, "Đăng ký môn học "));
                    lstHouseTypes.add(new KeyValuePairModel(POSITION_DIEM_DANH, "Điểm Danh "));
                    KeyValuePairAdapter adapter = new KeyValuePairAdapter(mContext, R.layout.my_spinner_style,
                            lstHouseTypes);
                    spObject.setAdapter(adapter);
                }
                try{
                    spObject.setSelection(position);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }


        private void initOnClick(){
            if(btnImport != null){
                btnImport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        importData();
                    }
                });
            }
        }

        private void importData(){
            KeyValuePairModel sheetItem = null, objectItem = null;
            if(spSheet!= null && spSheet.getAdapter()!= null){
                sheetItem = (KeyValuePairModel)spSheet.getSelectedItem();
            }
            if(spObject!= null && spObject.getAdapter()!= null){
                objectItem = (KeyValuePairModel)spObject.getSelectedItem();
            }
            if(sheetItem != null && objectItem != null){
                switch (objectItem.getID()){
                    case POSITION_SINH_VIEN:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addSinhVien(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_MON_HOC:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addMonHoc(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_GIANG_VIEN:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addGiangVien(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_BUOI_HOC:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addBuoiHoc(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_THU:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addThu(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_THOI_KHOA_BIEU:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addThoiKhoaBieu(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                    case POSITION_DANG_KY_MON_HOC:
                        Helper.alertDialogResult(mContext,
                                "Import thành công " + addDangKyMonHoc(mList.get(sheetItem.getID())) + " dòng.");
                        break;
                }
            }
        }

    }

    private int addSinhVien(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                SinhVienModel sv = new SinhVienModel();
                try{
                    for(int j = 0; j < sheet.getColumns(); j++){
                        Cell cell = sheet.getCell(j, i);
                        switch (j){
                            case 0: sv.setID(cell.getContents());
                                break;
                            case 1: sv.setFullName(cell.getContents());
                                break;
                            case 2: sv.setPassport(cell.getContents());
                                break;
                            case 3:
                                if(Integer.valueOf(cell.getContents()) > 0)
                                    sv.setSex(true);
                                else
                                    sv.setSex(false);
                                break;
                            case 4: sv.setBirthDay(Helper.convertStringToDate(cell.getContents(), "dd/MM/yyyy"));
                                break;
                            case 5: sv.setPhoneNumber(cell.getContents());
                                break;
                            case 6: sv.setPassword(cell.getContents());
                                break;
                        }
                    }
                    SinhVienTable table = new SinhVienTable(mContext);
                    table.insert(sv);
                    total++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return  total;
    }

    private int addMonHoc(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                MonHocModel item = new MonHocModel();
                try {
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        switch (j) {
                            case 0:
                                item.setID(cell.getContents());
                                break;
                            case 1:
                                item.setDescription(cell.getContents());
                                break;
                            case 2:
                                item.setFromDate(Helper.convertStringToDate(cell.getContents(), "dd/MM/yyyy"));
                                break;
                            case 3:
                                item.setToDate(Helper.convertStringToDate(cell.getContents(), "dd/MM/yyyy"));
                                break;
                        }
                    }
                    MonHocTable table = new MonHocTable(mContext);
                    table.insert(item);
                    total++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

    private int addGiangVien(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                try {
                    GiangVienModel item = new GiangVienModel();
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        switch (j) {
                            case 0:
                                item.setID(cell.getContents());
                                break;
                            case 1:
                                item.setFullName(cell.getContents());
                                break;
                            case 2:
                                if (Integer.valueOf(cell.getContents()) > 0)
                                    item.setSex(true);
                                else
                                    item.setSex(false);
                                break;
                            case 3:
                                item.setPassword(cell.getContents());
                                break;
                            case 4:
                                item.setPassport(cell.getContents());
                                break;
                            case 5:
                                item.setPhoneNumber(cell.getContents());
                                break;
                            case 6:
                                item.setEmail(cell.getContents());
                                break;
                            case 8:
                                item.setBirthDay(Helper.convertStringToDate(cell.getContents(), "dd/MM/yyyy"));
                                break;
                        }
                    }
                    GiangVienTable table = new GiangVienTable(mContext);
                    table.insert(item);
                    total++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return  total;
    }

    private int addBuoiHoc(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                try {
                    BuoiHocModel item = new BuoiHocModel();
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        switch (j) {
                            case 0:
                                item.setID(cell.getContents());
                                break;
                            case 1:
                                item.setDescription(cell.getContents());
                                break;
                            case 2:
                                item.setStartTime(Integer.valueOf(cell.getContents()));
                                break;
                            case 3:
                                item.setEndTime(Integer.valueOf(cell.getContents()));
                                break;
                        }
                    }
                    BuoiHocTable table = new BuoiHocTable(mContext);
                    table.insert(item);
                    total++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

    private int addDangKyMonHoc(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                try{
                    DangKyMonHocModel item = new DangKyMonHocModel();
                    for(int j = 0; j < sheet.getColumns(); j++){
                        Cell cell = sheet.getCell(j, i);
                        switch (j){
                            case 0: item.setMonHocID(cell.getContents());
                                break;
                            case 1: item.setSinhVienID(cell.getContents());
                                break;
                            case 2:
                                item.setDate(Helper.convertStringToDate(cell.getContents(), "dd/MM/yyyy"));
                                break;
                        }
                    }
                    DangKyMonHocTable table = new DangKyMonHocTable(mContext);
                    table.insert(item);
                    total++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return  total;
    }

    private int addThu(Sheet sheet){
        int total =0;
        if(sheet != null){
            try {
                for (int i = 1; i < sheet.getRows(); i++) {
                    ThuModel item = new ThuModel();
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        switch (j) {
                            case 0:
                                item.setID(cell.getContents());
                                break;
                            case 1:
                                item.setDescription(cell.getContents());
                                break;
                        }
                    }
                    ThuTable table = new ThuTable(mContext);
                    table.insert(item);
                    total++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return total;
    }

    private int addThoiKhoaBieu(Sheet sheet){
        int total = 0;
        if(sheet != null){
            for(int i = 1; i < sheet.getRows(); i++){
                try {
                    ThoiKhoaBieuModel item = new ThoiKhoaBieuModel();
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        switch (j) {
                            case 0:
                                item.setThuID(cell.getContents());
                                break;
                            case 1:
                                item.setBuoiID(cell.getContents());
                                break;
                            case 2:
                                item.setMonHocID(cell.getContents());
                                break;
                        }
                    }
                    ThoiKhoaBieuTable table = new ThoiKhoaBieuTable(mContext);
                    total += table.insert(item);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

}
