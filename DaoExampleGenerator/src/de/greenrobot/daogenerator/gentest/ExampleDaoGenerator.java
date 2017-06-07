/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * 
 * Run it as a Java application (not Android).
 * 
 * @author Markus
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.hongocman.quanlymonhoc.models");

        addSinhVien(schema);
        addGiangVien(schema);
        addMonHoc(schema);
        addDangKyMonHoc(schema);
        addBuoiHoc(schema);
        addThu(schema);
        addThoiKhoaBieu(schema);
        addDiemDanh(schema);
        new DaoGenerator().generateAll(schema, "E:/LAP TRINH/CODE/Mobility/QuanLySinhVien/QuanLyMonHoc_Android/QuanLyMonHoc/app/src/main/java");
    }
    //Sinh vien
    private static void addSinhVien(Schema schema) {
        Entity note = schema.addEntity("SinhVienModel");
        note.addStringProperty("ID").primaryKey().notNull();
        note.addStringProperty("FullName");
        note.addBooleanProperty("Sex");
        note.addStringProperty("Password");
        note.addStringProperty("Passport");
        note.addStringProperty("PhoneNumber");
        note.addDateProperty("BirthDay");
    }
    //Giang Vien
    private static void addGiangVien(Schema schema) {
        Entity note = schema.addEntity("GiangVienModel");
        note.addStringProperty("ID").primaryKey().notNull();
        note.addStringProperty("FullName");
        note.addBooleanProperty("Sex");
        note.addStringProperty("Password");
        note.addStringProperty("Passport");
        note.addStringProperty("PhoneNumber");
        note.addStringProperty("Email");
        note.addDateProperty("BirthDay");
    }

    //Dang ky Mon Hoc
    private static void addMonHoc(Schema schema) {
        Entity note = schema.addEntity("MonHocModel");
        note.addStringProperty("ID").primaryKey();
        note.addStringProperty("Description");
        note.addDateProperty("FromDate");
        note.addDateProperty("ToDate");
    }

    //Dang ky Mon Hoc
    private static void addDangKyMonHoc(Schema schema) {
        Entity note = schema.addEntity("DangKyMonHocModel");
        note.addStringProperty("MonHocID");
        note.addStringProperty("SinhVienID");
        note.addDateProperty("Date");
    }

    //Buoi Hoc
    private static void addBuoiHoc(Schema schema) {
        Entity note = schema.addEntity("BuoiHocModel");
        note.addStringProperty("ID").primaryKey().notNull();
        note.addStringProperty("Description");
        note.addIntProperty("StartTime");
        note.addIntProperty("EndTime");
    }

    //Thu trong Tuan
    private static void addThu(Schema schema) {
        Entity note = schema.addEntity("ThuModel");
        note.addStringProperty("ID").primaryKey().notNull();
        note.addStringProperty("Description");
    }

    //Thoi Khoa Bieu
    private static void addThoiKhoaBieu(Schema schema) {
        Entity note = schema.addEntity("ThoiKhoaBieuModel");
        //note.addLongProperty("ID").primaryKey().notNull();
        note.addStringProperty("BuoiID").notNull();
        note.addStringProperty("MonHocID").notNull();
        note.addStringProperty("ThuID").notNull();
    }

    //Diem Danh
    private static void addDiemDanh(Schema schema) {
        Entity note = schema.addEntity("DiemDanhModel");
        note.addLongProperty("ID").primaryKey();
        note.addStringProperty("MonHocID");
        note.addStringProperty("SinhVienID");
        note.addStringProperty("BuoiID");
        note.addStringProperty("ThuID");
        note.addBooleanProperty("Status");
        note.addDateProperty("Date");
    }

    /********************************************************************************/
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

}
