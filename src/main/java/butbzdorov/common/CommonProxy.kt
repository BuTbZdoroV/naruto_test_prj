package butbzdorov.common

import butbzdorov.common.entity.EntityAtomicCylinder
import butbzdorov.common.entity.EntityAtomicTrap
import butbzdorov.common.entity.EntityHandler
import butbzdorov.common.items.NarutoItemsRegistry
import butbzdorov.core.Core
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.EntityRegistry

open class CommonProxy {

    open fun preInit(event: FMLPreInitializationEvent) {
        Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)
    }

    open fun init(event: FMLInitializationEvent) {
        Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)

        EntityHandler
        NarutoItemsRegistry
    }

    open fun postInit(event: FMLPostInitializationEvent) {
        Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)
    }


}