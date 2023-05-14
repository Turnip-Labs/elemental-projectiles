package turniplabs.projectiles.entity

import net.minecraft.client.Minecraft
import net.minecraft.src.*

class EntityArrowIce(world: World?, arrowType: Int) :
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

        // For loop so ice spawns in a 3x2x3 area
        for (x in posX.toInt() - 1..posX.toInt() + 1)
            for (y in posY.toInt() - 1..posY.toInt())
                for (z in posZ.toInt() - 1..posZ.toInt() + 1) {

                    // IN WATER - detects if there's water present, and to replace it with ice
                    if (worldObj.getBlockId(x, y, z) == Block.fluidWaterStill.blockID || worldObj.getBlockId(x, y, z) == Block.fluidWaterFlowing.blockID)
                        worldObj.setBlockWithNotify(x, y, z, Block.ice.blockID)

                    // IN LAVA - detects if there's lava present, and replaces it with obsidian
                    if (worldObj.getBlockId(x, y, z) == Block.fluidLavaStill.blockID || worldObj.getBlockId(x, y, z) == Block.fluidLavaFlowing.blockID)
                        worldObj.setBlockWithNotify(x, y, z, Block.obsidian.blockID)

                    // ON LAND - detects if there's air, grass, or ferns and replaces it with air
                    if (worldObj.getBlockId(x, posY.toInt(), z) == 0 || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrass.blockID || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrassFern.blockID)
                        // Also make sure it's not floating
                        if (worldObj.getBlockId(x, posY.toInt() - 1, z) != 0 || worldObj.getBlockId(x, posY.toInt() - 1, z) != Block.tallgrass.blockID || worldObj.getBlockId(x, posY.toInt(), z) == Block.tallgrassFern.blockID)
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