package turniplabs.projectiles.entity

import net.minecraft.client.Minecraft
import net.minecraft.src.Block
import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityDiggingFX
import net.minecraft.src.EntityLiving
import net.minecraft.src.World

class EntityArrowIce(world: World?, entityliving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityliving, doesArrowBelongToPlayer, arrowType) {

        // TODO - fix the checkerboard pattern and floating snow
    override fun inGroundAction() {
        super.inGroundAction()
        this.kill()

        // For loop so ice spawns in a 3x3x3 area
        for (x in posX.toInt() - 1..posX.toInt() + 1)
            for (y in posY.toInt() - 1..posY.toInt() + 1)
                for (z in posZ.toInt() - 1..posZ.toInt() + 1) {

                    // IN WATER - detects if there's water present, and to replace it with ice
                    if (worldObj.getBlockId(x, y, z) == Block.fluidWaterStill.blockID)
                        worldObj.setBlockWithNotify(x, y, z, Block.ice.blockID)

                    // IN LAVA - detects if there's lava present, and replaces it with obsidian
                    if (worldObj.getBlockId(x, y, z) == Block.fluidLavaStill.blockID)
                        worldObj.setBlockWithNotify(x, y, z, Block.obsidian.blockID)

                    // ON LAND - detects if there's air or grass, and replaces it with snow layers
                    if (worldObj.getBlockId(x, posY.toInt(), z) == 0 || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrass.blockID)
                        worldObj.setBlockWithNotify(x, posY.toInt(), z, Block.layerSnow.blockID)

                    // WITH FIRE - detects if there's fire, and replaces it with air
                    if (worldObj.getBlockId(x, posY.toInt(), z) == Block.fire.blockID)
                        worldObj.setBlockWithNotify(x, posY.toInt(), z, 0)
                }
    }

    // Snow and Ice digging effect as it launches
    override fun onUpdate() {
        super.onUpdate()

        val mc = Minecraft.getMinecraft()
        for (i in 0..5) {
            mc.effectRenderer.addEffect(EntityDiggingFX(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, Block.ice, 0, 0))
            mc.effectRenderer.addEffect(EntityDiggingFX(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, Block.blockSnow, 0, 0))
        }
    }
}