package turniplabs.projectiles

import io.github.prismwork.prismconfig.api.PrismConfig
import io.github.prismwork.prismconfig.api.config.DefaultSerializers
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.src.Block
import net.minecraft.src.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.halplibe.helper.RecipeHelper
import turniplabs.projectiles.item.*
import java.io.File

const val MOD_ID: String = "projectiles"
val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
var CONFIG: ProjectilesConfig.Config = ProjectilesConfig.Config()

val CONFIG_FILE: File = FabricLoader.getInstance().configDir.resolve("ElementalProjectiles.json5").toFile()

private val PROJECTILES_CONFIG: ProjectilesConfig.Config = PrismConfig.getInstance().serialize(
    ProjectilesConfig.Config::class.java,
    CONFIG_FILE,
    DefaultSerializers.getInstance().json5(ProjectilesConfig.Config::class.java)
)

val ARROW_EGG: Item = ItemHelper.createItem(MOD_ID, ItemArrowEgg(PROJECTILES_CONFIG.eggArrow), "ammo.arrow.egg", "arrow_egg.png")
val ARROW_FIRE: Item = ItemHelper.createItem(MOD_ID, ItemArrowFire(PROJECTILES_CONFIG.fireArrow), "ammo.arrow.fire", "arrow_coal.png")
val ARROW_ICE: Item = ItemHelper.createItem(MOD_ID, ItemArrowIce(PROJECTILES_CONFIG.iceArrow), "ammo.arrow.ice", "arrow_ice.png")
val ARROW_SULPHUR: Item = ItemHelper.createItem(MOD_ID, ItemArrowExplosive(PROJECTILES_CONFIG.explosiveArrow), "ammo.arrow.sulphur", "arrow_sulphur.png")
val ARROW_LIGHTNING: Item = ItemHelper.createItem(MOD_ID, ItemArrowLightning(PROJECTILES_CONFIG.lightningArrow), "ammo.arrow.diamond", "arrow_diamond.png")
val ARROW_TELEPORT: Item = ItemHelper.createItem(MOD_ID, ItemArrowTeleport(PROJECTILES_CONFIG.teleportArrow), "ammo.arrow.teleport", "arrow_teleport.png")

@Suppress("unused")
fun init() {
    LOGGER.info("Elemental Projectiles has been initialized. Have fun!")

    CONFIG = ProjectilesConfig.load(CONFIG_FILE)

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