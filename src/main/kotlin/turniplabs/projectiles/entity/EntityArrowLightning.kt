package turniplabs.projectiles.entity

import net.minecraft.src.*

class EntityArrowLightning(world: World?, arrowType: Int) :
    EntityArrow(world, arrowType) {

    constructor(world: World?, x: Double, y: Double, z: Double, arrowType: Int): this(world, arrowType) {
        super.worldObj
        doesArrowBelongToPlayer = false
        this.arrowType = arrowType
        arrowShake = 0
        ticksInAir = 0
        setSize(0.5f, 0.5f)
        setPosition(x, y, z)
        yOffset = 0.0f
    }

    constructor(world: World?, entityLiving: EntityLiving, playerArrow: Boolean, arrowType: Int): this(world, arrowType) {
        super.worldObj
        this.doesArrowBelongToPlayer = playerArrow
        this.arrowType = arrowType
        arrowShake = 0
        ticksInAir = 0
        owner = entityLiving
        setSize(0.5f, 0.5f)
        setLocationAndAngles(
            entityLiving.posX,
            entityLiving.posY + entityLiving.eyeHeight.toDouble(),
            entityLiving.posZ,
            entityLiving.rotationYaw,
            entityLiving.rotationPitch
        )
        posX -= (MathHelper.cos(rotationYaw / 180.0f * 3.141593f) * 0.16f).toDouble()
        posY -= 0.1
        posZ -= (MathHelper.sin(rotationYaw / 180.0f * 3.141593f) * 0.16f).toDouble()
        setPosition(posX, posY, posZ)
        yOffset = 0.0f
        motionX =
            (-MathHelper.sin(rotationYaw / 180.0f * 3.141593f) * MathHelper.cos(rotationPitch / 180.0f * 3.141593f)).toDouble()
        motionZ =
            (MathHelper.cos(rotationYaw / 180.0f * 3.141593f) * MathHelper.cos(rotationPitch / 180.0f * 3.141593f)).toDouble()
        motionY = (-MathHelper.sin(rotationPitch / 180.0f * 3.141593f)).toDouble()
        setArrowHeading(motionX, motionY, motionZ, 1.5f, 1.0f)
    }

    override fun inGroundAction() {
        super.inGroundAction()
        setEntityDead()

        if (!worldObj.isMultiplayerAndNotHost)
            worldObj.entityJoinedWorld(EntityLightningBolt(worldObj, posX, posY, posZ))
    }
}
