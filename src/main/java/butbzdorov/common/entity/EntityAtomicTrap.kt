package butbzdorov.common.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.DamageSource
import net.minecraft.world.World

class EntityAtomicTrap : Entity {
    
    private var owner: EntityPlayer? = null
    private var ticksCount = 0
    private val trapDuration = 40
    private val trapRadius = 3.0f
    private val trappedEntities = mutableSetOf<EntityLivingBase>()

    constructor(world: World) : super(world) {
        setSize(trapRadius * 2, 2.0f)
        noClip = true
        ignoreFrustumCheck = true
    }

    constructor(world: World, x: Double, y: Double, z: Double, owner: EntityPlayer?) : this(world) {
        this.owner = owner
        setPosition(x, y, z)
    }

    override fun onUpdate() {
        super.onUpdate()
        ticksCount++

        when {
            ticksCount < trapDuration -> {
                trapEntities()
            }
            ticksCount == trapDuration -> {
                explode()
                setDead()
            }
            else -> {
                setDead()
            }
        }

        setPosition(posX, posY, posZ)
    }

    private fun trapEntities() {
        val entities = worldObj.getEntitiesWithinAABB(
            EntityLivingBase::class.java,
            AxisAlignedBB.getBoundingBox(
                posX - trapRadius, posY - 1.0, posZ - trapRadius,
                posX + trapRadius, posY + 1.0, posZ + trapRadius
            )
        )

        for (entity in entities) {
            if (entity is EntityLivingBase && entity != owner && !entity.isDead) {
                trappedEntities.add(entity)
                
                entity.motionX = 0.0
                entity.motionY = 0.0
                entity.motionZ = 0.0

                entity.addPotionEffect(PotionEffect(Potion.moveSlowdown.id, 10, 100))

                entity.addPotionEffect(PotionEffect(Potion.resistance.id, 10, 100))
            }
        }
        
        trappedEntities.removeAll { it.isDead || it.getDistanceToEntity(this) > trapRadius }
    }

    private fun explode() {
        for (entity in trappedEntities) {
            if (!entity.isDead) {
                entity.attackEntityFrom(DamageSource.outOfWorld, 50.0f)
            }
        }
        trappedEntities.clear()
    }

    fun getTrapRadius(): Float = trapRadius
    
    fun getTicksCount(): Int = ticksCount
    
    fun getTrapDuration(): Int = trapDuration
    
    fun getTrappedCount(): Int = trappedEntities.size

    override fun entityInit() {
    }

    override fun readEntityFromNBT(tag: NBTTagCompound?) {
        ticksCount = tag?.getInteger("ticksCount") ?: 0
    }

    override fun writeEntityToNBT(tag: NBTTagCompound?) {
        tag?.setInteger("ticksCount", ticksCount)
    }
}