package butbzdorov.common.entity

import butbzdorov.core.Core
import cpw.mods.fml.common.registry.EntityRegistry

object EntityHandler {

    init {
        EntityRegistry.registerModEntity(
            EntityAtomicCylinder::class.java,
            "AtomicCylinder",
            1,
            Core.getInstance,
            64,
            3,
            true
        )
        EntityRegistry.registerModEntity(
            EntityAtomicTrap::class.java,
            "AtomicTrap",
            2, // Новый ID
            Core.getInstance,
            64,
            3,
            true
        )
    }

}