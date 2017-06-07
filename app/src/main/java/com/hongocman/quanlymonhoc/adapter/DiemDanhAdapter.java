package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.models.DangKyMonHocModel;
import com.hongocman.quanlymonhoc.models.DiemDanhModel;
import com.hongocman.quanlymonhoc.models.DiemDanhTable;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.SinhVienModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Laptop on 4/28/2016.
 */
public class DiemDanhAdapter extends BaseAdapter {

    private List<SinhVienModel> mList;
    private Context mContext;
    private String monHocID, buoiHocID, thuID;

    public DiemDanhAdapter(Context context, List<SinhVienModel> lst, String thu , String buoiHoc, String monHoc ){
        this.mContext = context;
        this.mList = lst;
        this.monHocID = monHoc;
        this.buoiHocID = buoiHoc;
        this.thuID = thu;
    }
    @Override
    public int getCount() {
        if(mList != null)
            return  mList.size();
        return 0;
    }

    @Override
    public SinhVienModel getItem(int position) {
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
        SinhVienModel item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_diem_danh, null);
            viewHolder = new ViewHolder();
            viewHolder.chbStatus = (CheckBox)convertView.findViewById(R.id.chk_status);
            viewHolder.lblFullName = (TextView)convertView.findViewById(R.id.lbl_full_name);
            viewHolder.lblID = (TextView)convertView.findViewById(R.id.lbl_id);
            viewHolder.lblRowNum = (TextView)convertView.findViewById(R.id.lbl_row_num);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.item = item;
        viewHolder.initCheckBox();
        viewHolder.lblID.setText(viewHolder.item.getID());
        viewHolder.lblFullName.setText(viewHolder.item.getFullName());
        viewHolder.lblRowNum.setText(String.valueOf(position + 1));
        if(viewHolder.item.getDiemDanhStatus())
            viewHolder.chbStatus.setChecked(true);
        else
            viewHolder.chbStatus.setChecked(false);
        return convertView;
    }



    private class ViewHolder {
        private TextView lblRowNum, lblID, lblFullName;
        private CheckBox chbStatus;
        private SinhVienModel item;

        private void initCheckBox(){
            if(chbStatus != null)
                chbStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean checked = chbStatus.isChecked();
                        updateDiemDanh(checked);
                    }
                });

        }

        private void updateDiemDanh(boolean checked){
            if(item != null){
                DiemDanhTable table = new DiemDanhTable(mContext);
                table.diemDanh(thuID, buoiHocID, monHocID, item.getID(), checked);
            }
        }
    }
}
