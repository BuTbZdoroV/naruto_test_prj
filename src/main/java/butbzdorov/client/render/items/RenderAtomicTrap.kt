package butbzdorov.client.render.items

import butbzdorov.common.entity.EntityAtomicTrap
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.Render
import net.minecraft.entity.Entity
import net.minecraft.util.MathHelper
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

@SideOnly(Side.CLIENT)
class RenderAtomicTrap : Render() {

    
    override fun doRender(entity: Entity, x: Double, y: Double, z: Double, yaw: Float, partialTicks: Float) {
        val trap = entity as? EntityAtomicTrap ?: return
        
        GL11.glPushMatrix()
        GL11.glTranslated(x, y, z)
        
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glDisable(GL11.GL_CULL_FACE)
        GL11.glDisable(GL11.GL_LIGHTING)
        
        val tessellator = Tessellator.instance
        
        val radius = trap.getTrapRadius()
        val ticksCount = trap.getTicksCount()
        val duration = trap.getTrapDuration()
        
        val progress = ticksCount.toFloat() / duration
        val red = progress
        val blue = 1.0f - progress
        val alpha = 0.6f + progress * 0.4f
        
        val pulse = (MathHelper.sin(ticksCount * 0.5f) * 0.2f + 0.8f)
        
        GL11.glColor4f(red, 0.2f, blue, alpha * pulse)
        
        renderTrapArea(tessellator, radius)
        
        GL11.glEnable(GL11.GL_LIGHTING)
        GL11.glEnable(GL11.GL_CULL_FACE)
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        
        GL11.glPopMatrix()
    }
    
    private fun renderTrapArea(tessellator: Tessellator, radius: Float) {
        val height = radius
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.draw()
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(-radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), 0.0, radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), 0.0, radius.toDouble())
        tessellator.draw()
        
        GL11.glColor4f(0.8f, 0.8f, 0.8f, 0.3f)
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(-radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.draw()
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(-radius.toDouble(), 0.0, radius.toDouble())
        tessellator.addVertex(radius.toDouble(), 0.0, radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.draw()
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(-radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), 0.0, radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.addVertex(-radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.draw()
        
        tessellator.startDrawing(GL11.GL_QUADS)
        tessellator.addVertex(radius.toDouble(), 0.0, -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), -radius.toDouble())
        tessellator.addVertex(radius.toDouble(), height.toDouble(), radius.toDouble())
        tessellator.addVertex(radius.toDouble(), 0.0, radius.toDouble())
        tessellator.draw()
    }

    override fun getEntityTexture(entity: Entity): ResourceLocation? {
        return null
    }
}