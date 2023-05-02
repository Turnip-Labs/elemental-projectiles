package turniplabs.projectiles.item

import net.minecraft.client.Minecraft
import turniplabs.projectiles.entity.EntityArrowTeleport

open class ItemArrowTeleport(i: Int) : ItemElementalArrow(i) {

    override fun shootArrow() {
        val world = Minecraft.getMinecraft().theWorld
        val player = Minecraft.getMinecraft().thePlayer

        if (!world.isMultiplayerAndNotHost)
            world.entityJoinedWorld(EntityArrowTeleport(world, player, true, 0))
    }
}
