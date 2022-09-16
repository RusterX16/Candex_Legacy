package dev.ruster.candex.io

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import dev.ruster.candex.entities.Brand
import dev.ruster.candex.entities.Can
import dev.ruster.candex.entities.CanSize

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "candex", factory, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS can(" +
                    "id INTEGER," +
                    "name TEXT NOT NULL," +
                    "brand TEXT CHECK (brand IN ('COCA_COLA')) NOT NULL UNIQUE," +
                    "size TEXT CHECK (size IN ('MEDIUM_THIN', 'MEDIUM_WIDE')) NOT NULL," +
                    "sparkling INTEGER NOT NULL," +
                    "energizer INTEGER NOT NULL," +
                    "sugarLess INTEGER NOT NULL," +
                    "CONSTRAINT pk_can PRIMARY KEY (id));"
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

    fun insert(table: String, params: Map<String, Any>) {
        val values = ContentValues()

        params.forEach { (k, v) ->
            when (v) {
                is String -> {
                    val s: String = v
                    values.put(k, s)
                }
                is Int -> {
                    val i: Int = v
                    values.put(k, i)
                }
                is Float -> {
                    val f: Float = v
                    values.put(k, f)
                }
                is Long -> {
                    val l: Long = v
                    values.put(k, l)
                }
                is Double -> {
                    val d: Double = v
                    values.put(k, d)
                }
                is Short -> {
                    val s: Short = v
                    values.put(k, s)
                }
                is Boolean -> {
                    val b: Boolean = v
                    values.put(k, b)
                }
                is Byte -> {
                    val b: Byte = v
                    values.put(k, b)
                }
                is ByteArray -> {
                    val ba: ByteArray = v
                    values.put(k, ba)
                }
            }
        }

        if (writableDatabase.insert(table, null, values) == -1L) {
            Log.w("db", "Something went wrong during insertion of param $params")
        } else {
            Can(
                values.getAsInteger("id"),
                values.getAsString("name"),
                Brand.valueOf(values.getAsString("brand")),
                CanSize.valueOf(values.getAsString("size")),
                values.getAsBoolean("sparkling"),
                values.getAsBoolean("energizer"),
                values.getAsBoolean("sugarLess")
            )
        }
        writableDatabase.close()
    }
}
