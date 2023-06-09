package turniplabs.projectiles.item

import net.minecraft.client.Minecraft
import turniplabs.projectiles.entity.EntityArrowExplosive

class ItemArrowExplosive(i: Int): ItemElementalArrow(i) {

    override fun shootArrow() {
        val world = Minecraft.getMinecraft().theWorld
        val player = Minecraft.getMinecraft().thePlayer

        if (!world.isMultiplayerAndNotHost) {
            world.playSoundAtEntity(player, "random.fuse", 1.0f, 1.0f)
            world.entityJoinedWorld(EntityArrowExplosive(world, player, true, 0))
        }
    }
}
