package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.SinhVienVangMatActivity;
import com.hongocman.quanlymonhoc.models.DiemDanhTable;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.ReportDiemDanhModel;
import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuDescriptionModel;

import java.util.List;

/**
 * Created by Laptop on 4/28/2016.
 */
public class ReportDiemDanhAdapter extends BaseAdapter {


    private List<ReportDiemDanhModel> mList;
    private Context mContext;

    public ReportDiemDanhAdapter(Context context, List<ReportDiemDanhModel> lst){
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
    public ReportDiemDanhModel getItem(int position) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_generator_parent, null);
        }
        ReportDiemDanhModel item = getItem(position);
        LinearLayout frmData = (LinearLayout)convertView.findViewById(R.id.frm_row);
        TextView row = (TextView)convertView.findViewById(R.id.lbl_row_num);
        row.setText(String.valueOf(position + 1));
        if(frmData.getChildCount()>0){
            frmData.removeAllViews();
        }
        if(item != null){
            frmData.addView(Helper.addRowItem(mContext, "Mã SV:", item.getSinhVienID()));
            frmData.addView(Helper.addRowItem(mContext, "Họ Tên:", item.getSinhVienFullName()));
            frmData.addView(Helper.addRowItem(mContext, "Tổng số:", String.valueOf(item.getTotal())));
        }
        return convertView;
    }




}
