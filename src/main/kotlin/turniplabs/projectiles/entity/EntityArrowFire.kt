package turniplabs.projectiles.entity

import net.minecraft.src.*

class EntityArrowFire(world: World?, arrowType: Int) : EntityArrow(world, arrowType) {

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
        worldObj.playSoundAtEntity(this, "fire.ignite", 1.0f, 1.0f)
        setEntityDead()

        // A for-loop so fire spawns in a 3x1x3 area
        // We don't need the floating check, since fire just doesn't spawn in the air
        for (x in posX.toInt() - 1..posX.toInt() + 1)
            for (z in posZ.toInt() - 1..posZ.toInt() + 1) {

                // Detects if there's air, grass, or ferns present, and to replace it with fire
                if (worldObj.getBlockId(x, posY.toInt(), z) == 0 || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrass.blockID || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrassFern.blockID)
                    worldObj.setBlockWithNotify(x, posY.toInt(), z, Block.fire.blockID)
            }
    }

    // Spawns fire particles as it travels
    override fun onUpdate() {
        super.onUpdate()
        worldObj.spawnParticle("flame", posX, posY, posZ, 0.0, 0.0, 0.0)
    }
}
