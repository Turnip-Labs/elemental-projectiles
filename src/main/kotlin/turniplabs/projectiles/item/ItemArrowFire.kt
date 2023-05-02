package turniplabs.projectiles.item

import net.minecraft.client.Minecraft
import turniplabs.projectiles.entity.EntityArrowFire

open class ItemArrowFire(i: Int) : ItemElementalArrow(i) {

    override fun shootArrow() {
        val world = Minecraft.getMinecraft().theWorld
        val player = Minecraft.getMinecraft().thePlayer
        val arrowFire = EntityArrowFire(world, player, true, 0)

        if (!world.isMultiplayerAndNotHost) {
            arrowFire.fire = 300
            world.entityJoinedWorld(arrowFire)
        }
    }
}
