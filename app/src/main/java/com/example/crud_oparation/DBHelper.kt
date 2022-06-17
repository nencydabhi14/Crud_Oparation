package com.example.crud_oparation

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.LocusId
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Crud.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query =
            "CREATE TABLE Nency(id INTEGER PRIMARY KEY AUTOINCREMENT,Amount TEXT,Details TEXT,Date TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun insertData(am: String, detail: String, Dat: String) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("Amount",am)
        cv.put("Details",detail)
        cv.put("Date",Dat)

        var res = db.insert("Nency",null,cv)

        println("$res")
    }

    @SuppressLint("Range")
    fun ReadData(): ArrayList<ModalData> {

        var list = arrayListOf<ModalData>()

        var db = readableDatabase
        var query = "SELECT * FROM Nency"
        var cursor =db.rawQuery(query,null)

        if (cursor.moveToFirst()){

            do {
                var id =cursor.getString(cursor.getColumnIndex("id"))
                var amount =cursor.getString(cursor.getColumnIndex("Amount"))
                var details =cursor.getString(cursor.getColumnIndex("Details"))
                var date =cursor.getString(cursor.getColumnIndex("Date"))

                var m1 = ModalData(id,amount,details,date)
                list.add(m1)

            }while (cursor.moveToNext())
        }

        return list
    }

    fun DelateData(id: String){
        var db = writableDatabase
        db.delete("Nency","id = $id",null)
    }

    fun UpDateData(id: String,amount: String,detailles : String,date :String){

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("Amount",amount)
        cv.put("Details",detailles)
        cv.put("Date",date)

        db.update("Nency",cv,"id = $id",null)
    }
}