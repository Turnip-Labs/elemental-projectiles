package turniplabs.projectiles.entity

import net.minecraft.src.*

class EntityArrowTeleport(world: World?, entityLiving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityLiving, doesArrowBelongToPlayer, arrowType) {

    override fun inGroundAction() {
        super.inGroundAction()
        kill()

        // Teleports the player
        // TODO - swap the player and hit entity's positions
        owner.setPosition(posX, posY + 1, posZ)
    }
}
