package dev.ruster.candex.entities

import android.content.ContentValues
import dev.ruster.candex.io.Database

data class Can(
    private val id: Int,
    private val name: String,
    private val brand: Brand,
    private val size: CanSize,
    private val sparkling: Boolean,
    private val energizer: Boolean,
    private val sugarLess: Boolean
) {
    constructor(values: ContentValues) : this(
        values.getAsInteger("id"),
        values.getAsString("name"),
        Brand.valueOf(values.getAsString("brand")),
        CanSize.valueOf(values.getAsString("size")),
        values.getAsBoolean("sparkling"),
        values.getAsBoolean("energizer"),
        values.getAsBoolean("sugarLess")
    )

    companion object {
        val CANS: MutableList<Can> = ArrayList()
        private const val TABLE_NAME: String = "can"

        fun defaultInsert(db: Database) {
            db.insert(
                TABLE_NAME,
                mapOf(
                    "id" to 1,
                    "name" to "Coca Cola",
                    "brand" to "COCA_COLA",
                    "size" to "MEDIUM_THIN",
                    "sparkling" to 1,
                    "energizer" to 0,
                    "sugarLess" to 0
                )
            )
        }
    }

    init {
        CANS.add(this)
    }

    override fun toString(): String {
        return "Can{\n" +
                "  id=$id,\n" +
                "  name=$name,\n" +
                "  brand=$brand,\n" +
                "  size=$size,\n" +
                "  sparkling=$sparkling,\n" +
                "  energizer=$energizer,\n" +
                "  sugarLess=$sugarLess\n" +
                "}"
    }
}