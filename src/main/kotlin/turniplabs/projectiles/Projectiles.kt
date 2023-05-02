package turniplabs.projectiles

import net.minecraft.src.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.projectiles.item.*

const val MOD_ID: String = "projectiles"
val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

var ItemID = 1100

fun nextID(): Int {
    return ItemID++
}

val ARROW_EGG: Item = ItemHelper.createItem(MOD_ID, ItemArrowEgg(nextID()), "ammo.arrow.egg", "arrow_egg.png");
val ARROW_FIRE: Item = ItemHelper.createItem(MOD_ID, ItemArrowFire(nextID()), "ammo.arrow.fire", "arrow_coal.png")
val ARROW_ICE: Item = ItemHelper.createItem(MOD_ID, ItemArrowIce(nextID()), "ammo.arrow.ice", "arrow_ice.png")
val ARROW_SULPHUR: Item = ItemHelper.createItem(MOD_ID, ItemArrowExplosive(nextID()), "ammo.arrow.sulphur", "arrow_sulphur.png")
val ARROW_LIGHTNING: Item = ItemHelper.createItem(MOD_ID, ItemArrowLightning(nextID()), "ammo.arrow.diamond", "arrow_diamond.png")
val ARROW_TELEPORT: Item = ItemHelper.createItem(MOD_ID, ItemArrowTeleport(nextID()), "ammo.arrow.teleport", "arrow_teleport.png")

@Suppress("unused")
fun init() {
    LOGGER.info("Elemental Projectiles initialized. Have fun!")
}