package com.hongocman.quanlymonhoc.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocMan on 19/03/2016.
 */
public class QLSV {
    private List<SinhVienModel> dsSV;

    public QLSV(List<SinhVienModel> dsSV) {
        this.dsSV = dsSV;
    }

    public QLSV(){
        dsSV = new ArrayList<>();
    }

    public void setDsSV(List<SinhVienModel> dsSV) {
        this.dsSV = dsSV;
    }

    public List<SinhVienModel> getDsSV() {
        return dsSV;
    }

    private static QLSV qlsv;

    public static QLSV get() {
        if (qlsv == null)
            qlsv = new QLSV();
        return qlsv;
    }
}
