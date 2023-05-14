package turniplabs.projectiles.entity

import net.minecraft.src.*
import net.minecraft.src.helper.DamageType

// Never shot out of a dispenser for obvious reasons, so we don't need the second or third constructor
class EntityArrowTeleport(world: World?, entityLiving: EntityLiving?, doesArrowBelongToPlayer: Boolean, arrowType: Int) :
    EntityArrow(world, entityLiving, doesArrowBelongToPlayer, arrowType) {

        // Sets no-clip to true and damage to 0, so it's more reliable
        // Also so the player doesn't take damage from their own arrow
        init {
            this.noClip = true
            this.arrowDamage = 0
        }

    override fun inGroundAction() {
        super.inGroundAction()
        setEntityDead()
    }

    override fun onUpdate() {

        // Copied code from EntityArrow
        val vec3d = Vec3D.createVector(posX, posY, posZ)
        val vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ)
        var movingObject = worldObj.func_28105_a(vec3d, vec3d1, false, true)

        // Create a list of entities within 1 block of the arrow
        // More copied code
        var entity: Entity? = null
        val list: List<*> = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(2.0, 2.0, 2.0))
        var d = 0.0

        for (l in list.indices) {
            val closestEnt = list[l] as Entity
            if (closestEnt.canBeCollidedWith() && closestEnt !== owner) {
                val f4 = 0.3f
                val aabb = closestEnt.boundingBox.expand(f4.toDouble(), f4.toDouble(), f4.toDouble())
                val movingObject2 = aabb.func_1169_a(vec3d, vec3d1)
                if (movingObject2 != null) {
                    val d1 = vec3d.distanceTo(movingObject2.hitVec)
                    if (d1 < d || d == 0.0) {
                        entity = closestEnt
                        d = d1
                    }
                }
            }
        }

        if (entity != null) {
            movingObject = MovingObjectPosition(entity)
        }

        // NullPointerException handling
        if (movingObject != null)
            if (movingObject.entityHit != null)
                if (movingObject.entityHit.attackEntityFrom(owner, arrowDamage, DamageType.COMBAT)) {
                    val entityHit = movingObject.entityHit

                    // Finally swap-teleport the two entities
                    entityHit.setPosition(owner.posX, owner.posY, owner.posZ)
                    owner.setPosition(entityHit.prevPosX, entityHit.prevPosY + 2, entityHit.prevPosZ)
                }
        super.onUpdate()
    }
}
