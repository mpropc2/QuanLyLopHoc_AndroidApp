package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.renderscript.ScriptIntrinsicHistogram;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.SinhVienModel;

import java.util.List;

/**
 * Created by Laptop on 4/28/2016.
 */
public class SinhVienAdapter extends BaseAdapter {

    private List<SinhVienModel> mList;
    private Context mContext;
    private SparseBooleanArray mSelectedItemsIds;

    public SinhVienAdapter(Context context, List<SinhVienModel> lst){
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
    public SinhVienModel getItem(int position) {
        if (mList != null)
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_sinh_vien, null);
        }
        SinhVienModel item = getItem(position);
        if(item != null){
            TextView rowNum = (TextView)convertView.findViewById(R.id.lbl_row_num);
            rowNum.setText(String.valueOf(position + 1));
            TextView lblID = (TextView)convertView.findViewById(R.id.lbl_id);
            TextView lblFullName = (TextView)convertView.findViewById(R.id.lbl_full_name);
            ImageView imgGioiTinh = (ImageView)convertView.findViewById(R.id.imgGioiTinh);
            if(item.getSex()){
                imgGioiTinh.setImageResource(R.drawable.sinhviennam);
            }else{
                imgGioiTinh.setImageResource(R.drawable.sinhviennu);
            }
            lblID.setText(item.getID());
            lblFullName.setText(item.getFullName());
        }
        return convertView;
    }
}
