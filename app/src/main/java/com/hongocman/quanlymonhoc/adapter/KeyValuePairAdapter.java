package com.hongocman.quanlymonhoc.adapter;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hongocman.quanlymonhoc.R;
import com.hongocman.quanlymonhoc.models.KeyValuePairModel;

import java.util.ArrayList;

/**
 * ADAPTER:			KeyValuePairSpinnerAdapter  
 * @description: 	customize adapter for DDL KeyValuePaisModel
 * @author: 		vandn
 * @create on: 		16/08/2013
 */
public class KeyValuePairAdapter extends ArrayAdapter<KeyValuePairModel> {
	private ArrayList<KeyValuePairModel> lstObj;
	private Context mContext;
	private boolean hasInitText = false;
	private int iColor = 2;

	public KeyValuePairAdapter(Context context,	int textViewResourceId, ArrayList<KeyValuePairModel> lstObj) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.lstObj = lstObj;
	}
	
	public KeyValuePairAdapter(Context context,	int textViewResourceId, ArrayList<KeyValuePairModel> lstObj, int color) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.lstObj = lstObj;
		this.iColor = color;
	}
	
	public KeyValuePairAdapter(Context context,	int textViewResourceId, ArrayList<KeyValuePairModel> lstObj, boolean hasInitText) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.lstObj = lstObj;
		this.hasInitText = true;
	}
	
	
	public ArrayList<KeyValuePairModel> getList(){
		return lstObj;
	}
	public int getCount(){
		if(lstObj != null)
	       return lstObj.size();
		return 0;
	}
		
	public KeyValuePairModel getItem(int position){
		if(lstObj != null)
			return lstObj.get(position);
		return null;
    }
	
	public long getItemId(int position){
       return position;
    }	
	
    @Override
    public View getDropDownView(int position, View convertView,
            ViewGroup parent) {
        TextView label = new TextView(mContext);
        if(hasInitText && position == 0){
        	label.setVisibility(View.GONE);  
        }
        else{
            label.setText(lstObj.get(position).getDescription());
	        int padding = (int) mContext.getResources().getDimension(R.dimen.padding_medium);
	        label.setPadding(padding, padding, padding, padding);
        }
        return label;    	
    }	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(mContext);
        //label.setGravity(Gravity.CENTER);
        label.setText(lstObj.get(position).getDescription()); 
        if(this.iColor != 2)
        	label.setTextColor(ColorStateList.valueOf(this.iColor));
        return label;
    }

}
