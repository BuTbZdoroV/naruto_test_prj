package butbzdorov.client.render

import butbzdorov.client.render.items.RenderAtomicTrap
import butbzdorov.client.renderer.RenderAtomicCylinder
import butbzdorov.common.entity.EntityAtomicCylinder
import butbzdorov.common.entity.EntityAtomicTrap
import cpw.mods.fml.client.registry.RenderingRegistry

object RenderHandler {

    init {
        RenderingRegistry.registerEntityRenderingHandler(
            EntityAtomicCylinder::class.java,
            RenderAtomicCylinder()
        )
        RenderingRegistry.registerEntityRenderingHandler(
            EntityAtomicTrap::class.java,
            RenderAtomicTrap()
        )
    }

}