package com.hongocman.quanlymonhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.hongocman.quanlymonhoc.adapter.MenuGridViewAdapter;
import com.hongocman.quanlymonhoc.adapter.MonHocAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private GridView gvRow1, gvRow2, gvRow3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        gvRow1 = (GridView)findViewById(R.id.gv_menu_1);
        gvRow2 = (GridView)findViewById(R.id.gv_menu_2);
        gvRow3 = (GridView)findViewById(R.id.gv_menu_3);
        gvRow1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int StrID = (int)parent.getItemAtPosition(position);
                startActivityByMenu(StrID, gvRow1);
            }
        });
        gvRow2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int StrID = (int)parent.getItemAtPosition(position);
                startActivityByMenu(StrID, gvRow2);
            }
        });
        gvRow3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int StrID = (int)parent.getItemAtPosition(position);
                startActivityByMenu(StrID, gvRow3);
            }
        });
        initGridView();
    }
    private  void startActivityByMenu(int strID, GridView gv ){
        if(strID == R.string.menu_sinh_vien){
            Intent intent = new Intent(this, ListSinhVienActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);
        }else if(strID == R.string.menu_import_excel){
            Intent intent = new Intent(this, ImportExcelActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);
        }else if(strID == R.string.menu_diem_danh){
           /* Intent intent = new Intent(mContext, ReportDiemDanhActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            mContext.startActivity(intent);*/
            PopupMenu popup = new PopupMenu(this, gv);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.menu_popup_diem_danh, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId() == R.id.menu_diem_danh){
                        Intent intent = new Intent(MainActivity.this, DiemDanhActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        MainActivity.this.startActivity(intent);
                    }else if(item.getItemId() == R.id.menu_vang_mat){
                        Intent intent = new Intent(MainActivity.this, ReportDiemDanhActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        MainActivity.this.startActivity(intent);
                    }
                    Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            popup.show();
        }else if(strID == R.string.menu_mon_hoc){
            Intent intent = new Intent(MainActivity.this, ListMonHocActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            MainActivity.this.startActivity(intent);
        }else if(strID == R.string.menu_thoi_khoa_bieu){
            Intent intent = new Intent(MainActivity.this, ListThoiKhoaBieuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            MainActivity.this.startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_XoaTatCa) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_sinh_vien){
            Intent intent = new Intent(this, ListSinhVienActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else if(id == R.id.nav_import_excel){
            Intent intent = new Intent(this, ImportExcelActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else if(id == R.id.nav_diem_danh){
            Intent intent = new Intent(this, ReportDiemDanhActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else if(id == R.id.nav_mon_hoc){
            Intent intent = new Intent(this, ListMonHocActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else if(id == R.id.nav_thoi_khoa_bieu){
            Intent intent = new Intent(this, ListThoiKhoaBieuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private  void initGridView(){

        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();
        List<Integer> lst3 = new ArrayList<>();
        lst1.add(R.string.menu_sinh_vien);
        lst1.add(R.string.menu_mon_hoc);
        //lst2.add(R.string.menu_dang_ky_mon_hoc);
        lst2.add(R.string.menu_thoi_khoa_bieu);
        lst3.add(R.string.menu_diem_danh);
        lst2.add(R.string.menu_import_excel);
        MenuGridViewAdapter adapter1 = new MenuGridViewAdapter(this,lst1);
        MenuGridViewAdapter adapter2 = new MenuGridViewAdapter(this,lst2);
        MenuGridViewAdapter adapter3 = new MenuGridViewAdapter(this,lst3);

        if(gvRow1 != null)
            gvRow1.setAdapter(adapter1);
        if(gvRow2 != null)
            gvRow2.setAdapter(adapter2);
        if(gvRow3 != null)
            gvRow3.setAdapter(adapter3);


    }
}
