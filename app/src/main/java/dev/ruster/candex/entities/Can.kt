package dev.ruster.candex.entities

class Can {

    companion object {
        val CANS: MutableList<Can> = ArrayList()
    }

    val id: Int
    val name: String
    val brand: Brand
    val size: CanSize
    val sparkling: Boolean
    val energizer: Boolean
    val sugarLess: Boolean

    constructor(
        id: Int,
        name: String,
        brand: Brand,
        size: CanSize,
        sparkling: Boolean,
        energizer: Boolean,
        sugarLess: Boolean
    ) {
        this.id = id
        this.name = name
        this.brand = brand
        this.size = size
        this.sparkling = sparkling
        this.energizer = energizer
        this.sugarLess = sugarLess
        CANS.add(this)
    }

    constructor(
        id: Int,
        name: String,
        brand: Brand,
        size: CanSize,
        sparkling: Boolean,
        energizer: Boolean,
        sugarLess: Boolean,
        register: Boolean
    ) {
        this.id = id
        this.name = name
        this.brand = brand
        this.size = size
        this.sparkling = sparkling
        this.energizer = energizer
        this.sugarLess = sugarLess
    }

    override fun toString(): String {
        return "Can{" +
                "id=$id," +
                "name=$name," +
                "brand=$brand," +
                "size=$size," +
                "sparkling=$sparkling," +
                "energizer=$energizer," +
                "sugarLess=$sugarLess," +
                "}"
    }
}