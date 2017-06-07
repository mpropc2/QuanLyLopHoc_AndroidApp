package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.models.DiemDanhModel;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.ReportDiemDanhModel;

import java.util.List;

/**
 * Created by Laptop on 4/28/2016.
 */
public class ListSinhVienVangMatDiemDanhAdapter extends BaseAdapter {

    private List<DiemDanhModel> mList;
    private Context mContext;

    public ListSinhVienVangMatDiemDanhAdapter(Context context, List<DiemDanhModel> lst){
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
    public DiemDanhModel getItem(int position) {
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
        DiemDanhModel item = getItem(position);
        LinearLayout frmData = (LinearLayout)convertView.findViewById(R.id.frm_row);
        TextView row = (TextView)convertView.findViewById(R.id.lbl_row_num);
        row.setText(String.valueOf(position + 1));
        if(frmData.getChildCount()>0){
            frmData.removeAllViews();
        }
        if(item != null){
            frmData.addView(Helper.addRowItem(mContext, "Mã SV:", item.getSinhVienID()));
            frmData.addView(Helper.addRowItem(mContext, "Mã Môn học:", item.getMonHocID()));
            frmData.addView(Helper.addRowItem(mContext, "Ngày vắng:", Helper.convertDateToString(item.getDate(), Helper.TAG_FORMATE_DATE_VN)));
        }
        return convertView;
    }




}
