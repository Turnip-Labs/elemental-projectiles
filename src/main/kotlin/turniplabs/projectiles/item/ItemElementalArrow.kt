package turniplabs.projectiles.item

import net.minecraft.client.Minecraft
import net.minecraft.src.Entity
import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityPlayer
import net.minecraft.src.Item
import net.minecraft.src.World

open class ItemElementalArrow(i: Int) : Item(i) {

    open fun shootArrow() {
        val world = Minecraft.getMinecraft().theWorld
        val player = Minecraft.getMinecraft().thePlayer

        if (!world.isMultiplayerAndNotHost)
            world.entityJoinedWorld(EntityArrow(world, player, true, 0))
    }
}
