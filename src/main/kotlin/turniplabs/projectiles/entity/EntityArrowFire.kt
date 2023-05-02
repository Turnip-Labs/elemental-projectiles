package turniplabs.projectiles.entity

import net.minecraft.src.Block
import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityLiving
import net.minecraft.src.World

class EntityArrowFire(world: World?, entityliving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityliving, doesArrowBelongToPlayer, arrowType) {

    override fun inGroundAction() {
        super.inGroundAction()
        worldObj.playSoundAtEntity(this, "fire.ignite", 1.0f, 1.0f)
        this.kill()

        // A for-loop so fire spawns in a 3x1x3 area
        for (x in posX.toInt() - 1..posX.toInt() + 1)
            for (z in posZ.toInt() - 1..posZ.toInt() + 1) {

                // Detects if there's air or grass present, and to replace it with fire
                if (worldObj.getBlockId(x, posY.toInt(), z) == 0 || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrass.blockID)
                    worldObj.setBlockWithNotify(x, posY.toInt(), z, Block.fire.blockID)
            }
    }

    // Sets the arrow on fire and spawns flame particles as it travels
    override fun onUpdate() {
        super.onUpdate()
        worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0, 0.0, 0.0)
    }
}