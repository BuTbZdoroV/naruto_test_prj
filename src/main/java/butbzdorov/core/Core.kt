package butbzdorov.core

import butbzdorov.common.CommonProxy
import butbzdorov.server.ServerProxy
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.relauncher.Side
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import ru.justagod.cutter.GradleSide
import ru.justagod.cutter.GradleSideOnly

@Mod(modid = Core.MODID, name = Core.NAME, version = Core.VERSION)
class Core {

    companion object {
        const val MODID: String = "narutomod"
        const val NAME: String = "Naruto"
        const val VERSION: String = "1.0"

        @Mod.Instance(MODID)
        lateinit var getInstance: Core

        @SidedProxy(
            clientSide = "butbzdorov.client.ClientProxy",
            serverSide = "butbzdorov.common.CommonProxy",
        )
        lateinit var proxy: CommonProxy;

        val logger: Logger = LogManager.getLogger("Core")
    }

    @GradleSideOnly(GradleSide.SERVER)
    val serverProxy: ServerProxy = ServerProxy()

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {

        proxy.preInit(event)

        if (FMLCommonHandler.instance().effectiveSide.isServer) {
            serverProxy.preInit(event)
        }

    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {

        proxy.init(event)

        if (FMLCommonHandler.instance().effectiveSide.isServer) {
            serverProxy.init(event)
        }

    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {

        proxy.postInit(event)

        if (FMLCommonHandler.instance().effectiveSide.isServer) {
            serverProxy.postInit(event)
        }

    }
}