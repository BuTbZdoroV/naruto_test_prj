package butbzdorov.common.items

import butbzdorov.common.entity.EntityAtomicCylinder
import butbzdorov.core.Core
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class AtomicItem(
    name: String,
    textureName: String,
    private val lore: String,
) : Item() {

    private val name: String
    private val localizationName: String

    init {
        this.name = name
        this.localizationName = textureName
        this.unlocalizedName = name
        this.maxStackSize = 1
        this.creativeTab = CreativeTabs.tabCombat
        GameRegistry.registerItem(this, this.unlocalizedName)
        Core.logger.info("add Item $name for ${this.javaClass.name}")
    }

    override fun onItemRightClick(
        stack: ItemStack,
        world: World,
        player: EntityPlayer
    ): ItemStack {
        if (!world.isRemote) {
            val cylinder = EntityAtomicCylinder(world, player)
            world.spawnEntityInWorld(cylinder)
        }
        return stack
    }


}