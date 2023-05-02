package turniplabs.projectiles

import net.minecraft.src.Block
import net.minecraft.src.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.halplibe.helper.RecipeHelper
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

    // Recipes
    RecipeHelper.Crafting.createRecipe(
        ARROW_EGG, 1, arrayOf<Any>("A", "B", "C", 'A', Item.eggChicken, 'B', Item.stick, 'C', Item.featherChicken)
    )

    RecipeHelper.Crafting.createRecipe(
        ARROW_FIRE, 1, arrayOf<Any>("A", "B", "C", 'A', Item.coal, 'B', Item.stick, 'C', Item.featherChicken)
    )

    RecipeHelper.Crafting.createRecipe(
        ARROW_ICE, 1, arrayOf<Any>("A", "B", "C", 'A', Block.ice, 'B', Item.stick, 'C', Item.featherChicken)
    )

    RecipeHelper.Crafting.createRecipe(
        ARROW_SULPHUR, 1, arrayOf<Any>("A", "B", "C", 'A', Item.sulphur, 'B', Item.stick, 'C', Item.featherChicken)
    )

    RecipeHelper.Crafting.createRecipe(
        ARROW_LIGHTNING, 1, arrayOf<Any>("A", "B", "C", 'A', Item.diamond, 'B', Item.stick, 'C', Item.featherChicken)
    )

    RecipeHelper.Crafting.createRecipe(
        ARROW_TELEPORT, 4, arrayOf<Any>("A", "B", "C", 'A', Block.obsidian, 'B', Item.stick, 'C', Item.featherChicken)
    )
}