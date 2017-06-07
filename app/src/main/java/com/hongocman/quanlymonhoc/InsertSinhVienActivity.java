package com.hongocman.quanlymonhoc;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hongocman.quanlymonhoc.models.DateDialog;
import com.hongocman.quanlymonhoc.models.Helper;
import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.SinhVienTable;

import java.text.SimpleDateFormat;

import de.greenrobot.dao.DaoException;

public class InsertSinhVienActivity extends AppCompatActivity {

    private EditText txtID, txtFullName, txtPhoneNumber, txtEmail, txtBirthDay, txtPassport;
    private RadioButton radMale, radFemale;
    private Button btnUpdate;
    private SinhVienTable svtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sinh_vien);
        getSupportActionBar().setTitle("Thêm sinh viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtID = (EditText)findViewById(R.id.txt_id);
        txtFullName = (EditText)findViewById(R.id.txt_full_name);
        txtPhoneNumber = (EditText)findViewById(R.id.txt_phone_number);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtBirthDay = (EditText)findViewById(R.id.txt_birth_day);
        txtPassport = (EditText)findViewById(R.id.txt_passport);

        radMale = (RadioButton)findViewById(R.id.rad_male);
        radFemale = (RadioButton)findViewById(R.id.rad_female);
        btnUpdate = (Button)findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        //set value
        svtb = new SinhVienTable(this);
        txtID.setText(svtb.getNextMaSo());
        radMale.setChecked(true);
        txtBirthDay.setHint("Chạm để chọn ngày sinh");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(InsertSinhVienActivity.this, ListSinhVienActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkForUpdate(){
        if(txtID.getText().toString().trim().equals("")){
            txtID.setError("Chưa nhập mã SV");
            return  false;
        }
        if(txtFullName.getText().toString().trim().equals("")){
            txtFullName.setError("Chưa nhập Họ tên");
            return  false;
        }
        if(txtBirthDay.getText().toString().equals("")){
            txtBirthDay.setError("Chưa chọn ngày sinh");
            return false;
        }
        return true;
    }

    private void alertDialog(){
        if(checkForUpdate()){
            AlertDialog.Builder builder = null;
            Dialog dialog = null;
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có muốn thêm?").setCancelable(false).setPositiveButton("Có",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,	int id) {
                            //HandleSaveAddress(sHouseTypeSelected);
                            update();
                        }
                    }).setNegativeButton("Không",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,
                                    int id) {
                    dialog.cancel();
                }
            });
            dialog = builder.create();
            dialog.show();
        }
    }

    private void update(){
        try{
            SinhVienModel sv = new SinhVienModel();
            sv.setID(svtb.getNextMaSo());
            sv.setBirthDay(Helper.convertStringToDate(txtBirthDay.getText().toString(), "dd/MM/yyyy"));
            sv.setFullName(txtFullName.getText().toString());
            sv.setPhoneNumber(txtPhoneNumber.getText().toString());
            sv.setPassport(txtPassport.getText().toString());
            if(radFemale.isChecked())
                sv.setSex(false);
            else
                sv.setSex(true);
            SinhVienTable table = new SinhVienTable(this);
            table.insert(sv);
            Helper.alertDialogResult(this, "Thêm thành công");
            Intent intent = new Intent(InsertSinhVienActivity.this, ListSinhVienActivity.class);
            startActivity(intent);
            finish();
        }catch (DaoException ex){
            Helper.alertDialogResult(this, ex.getMessage());
        }
    }

    public void onStart(){
        super.onStart();
//        EditText txtNgaySinh = (EditText)findViewById(R.id.txt_birth_day);
        txtBirthDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }
}
