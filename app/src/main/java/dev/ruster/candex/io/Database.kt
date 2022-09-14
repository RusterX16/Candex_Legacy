package dev.ruster.candex.io

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.ruster.candex.entities.Brand
import dev.ruster.candex.entities.Can
import dev.ruster.candex.entities.CanSize

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "candex", factory, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE can(" +
                    "id INTEGER," +
                    "CONSTRAINT pk_can PRIMARY KEY (id))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS can")
        onCreate(db)
    }

    fun showTables(db: SQLiteDatabase): Cursor {
        return db.rawQuery(
            "SELECT name FROM sqlite_schema " +
                    "WHERE type='table' " +
                    "ORDER BY name;",
            null
        )
    }

    fun query(query: String): Cursor {
        return readableDatabase.rawQuery(query, null)
    }

    fun insert(table: String) {
        val values = ContentValues()

        values.put("id", 1)
        values.put("name", "Coca Cola")
        values.put("brand", Brand.COCA_COLA.toString())
        values.put("size", CanSize.MEDIUM_THIN.toString())
        values.put("sparkling", true)
        values.put("energizer", false)
        values.put("sugarLess", false)

        writableDatabase.insert(table, null, values)
    }
}
