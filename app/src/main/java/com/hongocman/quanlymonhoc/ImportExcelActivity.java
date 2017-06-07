package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hongocman.quanlymonhoc.adapter.ImportExcelAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ImportExcelActivity extends AppCompatActivity {

    private EditText txtPath;
    private Button btnBrowse;
    private ListView lvSheet;
    int PICK_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_excel);
        getSupportActionBar().setTitle("Import dữ liệu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtPath = (EditText)findViewById(R.id.txt_path);
        lvSheet =(ListView)findViewById(R.id.lv_sheet_object);
        btnBrowse = (Button)findViewById(R.id.btn_browse);
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, PICK_REQUEST_CODE);
            }
        });
        initListView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ImportExcelActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initListView(){
        String path = Environment.getExternalStorageDirectory()+"/QuanLyMonHoc.xls";
        txtPath.setText(path);
        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setEncoding( "Cp1252" );
        try {
            Workbook wrk1 = Workbook.getWorkbook(new File(path), workbookSettings);
            List<Sheet> lst = new ArrayList<>();
            for(int i=0;i< wrk1.getSheets().length ; i++){
                lst.add(wrk1.getSheet(i));
            }
            ImportExcelAdapter adapter = new ImportExcelAdapter(this, lst);
            lvSheet.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == PICK_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {

                Uri uri = intent.getData();
                String FilePath = intent.getData().getPath();
                /*if (uri.getScheme().toString().compareTo("content")==0)
                {
                    Cursor cursor =getContentResolver().query(uri, null, null, null, null);
                    if (cursor.moveToFirst())
                    {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);//Instead of "MediaStore.Images.Media.DATA" can be used "_data"
                        Uri filePathUri = Uri.parse(cursor.getString(column_index));
                        String file_name = filePathUri.getLastPathSegment().toString();
                        String file_path=filePathUri.getPath();
                        txtPath.setText(file_path);
                        Toast.makeText(this, "File Name & PATH are:" + file_name + "\n" + file_path, Toast.LENGTH_LONG).show();
                    }
                }*/
                txtPath.setText(FilePath);
            }
        }
    }
}
