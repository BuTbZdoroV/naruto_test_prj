package butbzdorov.client

import butbzdorov.client.render.RenderHandler
import butbzdorov.client.render.items.RenderAtomicTrap
import butbzdorov.client.renderer.RenderAtomicCylinder
import butbzdorov.common.CommonProxy
import butbzdorov.common.entity.EntityAtomicCylinder
import butbzdorov.common.entity.EntityAtomicTrap
import butbzdorov.core.Core
import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.EntityRegistry

class ClientProxy : CommonProxy() {

    override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)

    }

    override fun init(event: FMLInitializationEvent) {
        super.init(event)
        RenderHandler
    }

    override fun postInit(event: FMLPostInitializationEvent) {
        super.postInit(event)
    }

}