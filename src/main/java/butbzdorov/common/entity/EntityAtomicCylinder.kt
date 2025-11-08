package butbzdorov.common.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.DamageSource
import net.minecraft.util.MathHelper
import net.minecraft.world.World

class EntityAtomicCylinder : Entity {

    private var owner: EntityPlayer? = null
    private var ticksCount = 0
    private var currentSize = 0.5f
    private val maxLifetime = 100

    private var motionX: Double = 0.0
    private var motionY: Double = 0.0
    private var motionZ: Double = 0.0

    constructor(world: World) : super(world) {
        setSize(0.5f, 0.5f)
        noClip = true
        ignoreFrustumCheck = true
    }

    constructor(world: World, owner: EntityPlayer) : this(world) {
        this.owner = owner

        val lookVec = owner.lookVec
        if (lookVec != null) {
            setPosition(
                owner.posX + lookVec.xCoord * 2,
                owner.posY + owner.eyeHeight + lookVec.yCoord * 2,
                owner.posZ + lookVec.zCoord * 2
            )

            this.motionX = lookVec.xCoord * 0.3
            this.motionY = lookVec.yCoord * 0.3
            this.motionZ = lookVec.zCoord * 0.3
        } else {
            setPosition(owner.posX, owner.posY + owner.eyeHeight, owner.posZ)
            this.motionX = 0.0
            this.motionY = 0.0
            this.motionZ = 0.3
        }
    }

    override fun onUpdate() {
        super.onUpdate()
        ticksCount++

        if (ticksCount <= 50) {
            currentSize = 0.5f + (ticksCount * 0.02f)
            setSize(currentSize, currentSize)
        }

        this.posX += motionX
        this.posY += motionY
        this.posZ += motionZ

        checkCollision()

        if (ticksCount >= maxLifetime) {
            setDead()
        }

        setPosition(posX, posY, posZ)
    }

    private fun checkCollision() {
        val entities = worldObj.getEntitiesWithinAABB(
            EntityLivingBase::class.java,
            AxisAlignedBB.getBoundingBox(
                posX - currentSize.toDouble(),
                posY - currentSize.toDouble(),
                posZ - currentSize.toDouble(),
                posX + currentSize.toDouble(),
                posY + currentSize.toDouble(),
                posZ + currentSize.toDouble()
            )
        )

        for (entity in entities) {
            if (entity is EntityLivingBase && entity != owner && !entity.isDead) {
                val trap = EntityAtomicTrap(worldObj, posX, posY, posZ, owner)
                worldObj.spawnEntityInWorld(trap)

                setDead()
                break
            }
        }
    }

    override fun getDistanceToEntity(entity: Entity): Float {
        val dx = posX - entity.posX
        val dy = posY - entity.posY
        val dz = posZ - entity.posZ
        return MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz).toFloat()
    }

    fun getCurrentSize(): Float = currentSize

    fun getTicksCount(): Int = ticksCount

    override fun entityInit() {

    }

    override fun readEntityFromNBT(tag: NBTTagCompound?) {
        ticksCount = tag?.getInteger("ticksCount") ?: 0
        currentSize = tag?.getFloat("currentSize") ?: 0.5f
        motionX = tag?.getDouble("motionX") ?: 0.0
        motionY = tag?.getDouble("motionY") ?: 0.0
        motionZ = tag?.getDouble("motionZ") ?: 0.0
    }

    override fun writeEntityToNBT(tag: NBTTagCompound?) {
        tag?.setInteger("ticksCount", ticksCount)
        tag?.setFloat("currentSize", currentSize)
        tag?.setDouble("motionX", motionX)
        tag?.setDouble("motionY", motionY)
        tag?.setDouble("motionZ", motionZ)
    }
}