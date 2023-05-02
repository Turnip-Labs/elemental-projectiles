package turniplabs.projectiles.entity

import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityLiving
import net.minecraft.src.World

class EntityArrowExplosive(world: World?, entityliving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityliving, doesArrowBelongToPlayer, arrowType) {

    override fun inGroundAction() {
        super.inGroundAction()
        this.kill()

        if (!worldObj.isMultiplayerAndNotHost)
            worldObj.createExplosion(this, posX, posY, posZ, 1.0f)
    }
}