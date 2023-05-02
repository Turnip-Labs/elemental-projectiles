package turniplabs.projectiles.entity

import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityLightningBolt
import net.minecraft.src.EntityLiving
import net.minecraft.src.World

class EntityArrowLightning(world: World?, entityliving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityliving, doesArrowBelongToPlayer, arrowType) {

    override fun inGroundAction() {
        super.inGroundAction()
        this.kill()

        if (!worldObj.isMultiplayerAndNotHost)
            worldObj.entityJoinedWorld(EntityLightningBolt(worldObj, posX, posY, posZ))
    }
}
