package turniplabs.projectiles.entity

import net.minecraft.src.EntityArrow
import net.minecraft.src.EntityChicken
import net.minecraft.src.EntityLiving
import net.minecraft.src.World

class EntityArrowEgg(world: World?, entityliving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityliving, doesArrowBelongToPlayer, arrowType) {

    override fun entityInit() {
        super.entityInit()
        arrowDamage = 2
    }

    override fun inGroundAction() {
        super.inGroundAction()

        if (!worldObj.isMultiplayerAndNotHost) run {
            val eChicken = EntityChicken(worldObj)

            // Sets the position of the chicken and then spawns it (has to be this order for some reason)
            eChicken.setPosition(this.posX, this.posY, this.posZ)
            worldObj.entityJoinedWorld(eChicken)

            // Death particles, recreated from EntityLiving.class
            val double1: Double = rand.nextGaussian() * 0.02
            val double2: Double = rand.nextGaussian() * 0.02
            val double3: Double = rand.nextGaussian() * 0.02
            worldObj.spawnParticle(
                "explode",
                posX + (rand.nextFloat() * width * 2.0) - width,
                posY + rand.nextFloat() * height,
                posZ + (rand.nextFloat() * width * 2.0) - width,
                double1,
                double2,
                double3
            )
        }
        // Finally kill the arrow, so it can't be picked up
        kill()
    }
}