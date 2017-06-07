package com.hongocman.quanlymonhoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.hongocman.quanlymonhoc.DiemDanhActivity;
import com.hongocman.quanlymonhoc.ImportExcelActivity;
import com.hongocman.quanlymonhoc.ListMonHocActivity;
import com.hongocman.quanlymonhoc.ListSinhVienActivity;
import com.hongocman.quanlymonhoc.ListThoiKhoaBieuActivity;
import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.ReportDiemDanhActivity;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.SinhVienModel;

import java.util.List;

/**
 * Created by Laptop on 4/28/2016.
 */
public class MenuGridViewAdapter extends BaseAdapter {

    private List<Integer> mList;
    private Context mContext;

    public MenuGridViewAdapter(Context context, List<Integer> lst){
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
    public Integer getItem(int position) {
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
        final int strID = getItem(position);
        final GridView gv=(GridView)parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row_menu_cell, null);
        }
       ImageView img = (ImageView)convertView.findViewById(R.id.img_cell);
         /*img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByMenu(strID, gv);
            }
        });*/
        img.setImageResource(getMipmapID(strID));
        TextView lblTitle = (TextView)convertView.findViewById(R.id.lbl_title);

        lblTitle.setText(mContext.getString(strID));
       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByMenu(strID, gv);
            }
        });*/

        return convertView;
    }

    //Ham thay icon theo Stirng id: e lam tuong tu cho cac id sau nhe
    private int getMipmapID(int strID){
        if(strID == R.string.menu_sinh_vien){
            return R.mipmap.quanlysv;
        }else if(strID == R.string.menu_import_excel){
            return R.mipmap.fileexcel;
        }else if(strID == R.string.menu_diem_danh){
            return R.mipmap.diemdanh;
        }else if(strID == R.string.menu_mon_hoc){
            return R.mipmap.quanlymh;
        }else if(strID == R.string.menu_dang_ky_mon_hoc){
            return R.mipmap.dangkimh;
        }else if(strID == R.string.menu_thoi_khoa_bieu){
            return R.mipmap.quanlytkb;
        }
        return -1;
    }
    private  void startActivityByMenu(int strID, GridView gv ){
        if(strID == R.string.menu_sinh_vien){
            Intent intent = new Intent(mContext, ListSinhVienActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);
        }else if(strID == R.string.menu_import_excel){
            Intent intent = new Intent(mContext, ImportExcelActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);
        }else if(strID == R.string.menu_diem_danh){
           /* Intent intent = new Intent(mContext, ReportDiemDanhActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);*/
            PopupMenu popup = new PopupMenu(mContext, gv);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.menu_popup_diem_danh, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId() == R.id.menu_diem_danh){
                        Intent intent = new Intent(mContext, DiemDanhActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        mContext.startActivity(intent);
                    }else if(item.getItemId() == R.id.menu_vang_mat){
                        Intent intent = new Intent(mContext, ReportDiemDanhActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        mContext.startActivity(intent);
                    }
                    Toast.makeText(mContext,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            popup.show();
        }else if(strID == R.string.menu_mon_hoc){
            Intent intent = new Intent(mContext, ListMonHocActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);
        }else if(strID == R.string.menu_thoi_khoa_bieu){
            Intent intent = new Intent(mContext, ListThoiKhoaBieuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);
        }
    }

}
