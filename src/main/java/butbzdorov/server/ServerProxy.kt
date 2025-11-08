package butbzdorov.server

import butbzdorov.common.CommonProxy
import butbzdorov.core.Core
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent

class ServerProxy {

   fun preInit(event: FMLPreInitializationEvent) {
       Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)   }

   fun init(event: FMLInitializationEvent) {
       Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)   }

   fun postInit(event: FMLPostInitializationEvent) {
       Core.logger.info("Hello from ${this.javaClass.name} {} : {}", event.eventType, event.side.name)   }



}