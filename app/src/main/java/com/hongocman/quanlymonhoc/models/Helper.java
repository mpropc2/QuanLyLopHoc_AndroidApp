package com.hongocman.quanlymonhoc.models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Laptop on 4/28/2016.
 */
public class Helper {

    public  static final String TAG_DB_NAME = "QuanLyMonHoc";
    public  static final String TAG_FORMATE_DATE_VN = "dd/MM/yyyy";
    public static Date convertStringToDate(String dateStr, String formatStr){
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(dateStr);
            return  date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
    }

    public static String convertDateToString(Date date, String formatStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        try {
            String datetime = dateFormat.format(date);
            return datetime;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
    }

    public static Calendar convertStringToCalendar(String EndDate, String formatStr){
        Calendar result = null;
        try {
            SimpleDateFormat  format = new SimpleDateFormat(formatStr);
            Date date;
            date = format.parse(EndDate);
            result = Calendar.getInstance();
            result.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertCalendarToString(Calendar cal, String format){
        try {
            if(cal != null){
                SimpleDateFormat format1 = new SimpleDateFormat(format);
                return format1.format(cal.getTime());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    public static void alertDialogResult(Context mContext, String html)
    {
        new AlertDialog.Builder(mContext).setTitle("Thông báo").setMessage(html)
                .setPositiveButton("OK", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //MyApp.currentActivity().finish();
                        dialog.cancel();
                    }
                })
                .setCancelable(true).create().show();
    }

    public static View addRowItem(Context mContext, String label, final String value){
        try {
            View viewChild = LayoutInflater.from(mContext).inflate(R.layout.row_generator_childrent_value, null );
            LinearLayout frmData = (LinearLayout)viewChild.findViewById(R.id.frm_child_row);
            TextView lblTitle = (TextView)viewChild.findViewById(R.id.lbl_title);
            TextView lblTotal = (TextView)viewChild.findViewById(R.id.lbl_value);
            lblTitle.setText(label);
            lblTotal.setText(value);
            return frmData;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

}
