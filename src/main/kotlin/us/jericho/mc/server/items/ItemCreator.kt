package us.jericho.mc.server.items

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import us.jericho.mc.server.CustomServerPlugin
import java.util.*

open class ItemCreator {
    var material: Material? = null
    var amount = 1

    constructor()

    constructor(mat: String?, amount: Int?) {
        if(mat != null)
            this.setMaterial(mat)
        if(amount != null)
            this.setAmount(amount)
    }

    constructor(mat: String?, min: Int, max: Int) {
        if(mat != null)
            this.setMaterial(mat)
        this.randomAmount(min, max)
    }

    fun setMaterial(mat: String): ItemCreator {
        this.material = Material.getMaterial(mat.toUpperCase())
        return this
    }

    fun randomAmount(min: Int, max: Int): ItemCreator {
        val rand = Random()
        this.amount = rand.nextInt(max - min + 1) + min
        return this
    }

    fun setAmount(num: Int): ItemCreator {
        this.amount = num
        return this
    }

    fun make(): ItemStack {
        return ItemStack(this.material!!, this.amount)
    }
}