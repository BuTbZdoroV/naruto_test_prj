package butbzdorov.client.renderer

import butbzdorov.common.entity.EntityAtomicCylinder
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.Render
import net.minecraft.entity.Entity
import net.minecraft.util.MathHelper
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

@SideOnly(Side.CLIENT)
class RenderAtomicCylinder : Render() {


    override fun doRender(entity: Entity, x: Double, y: Double, z: Double, yaw: Float, partialTicks: Float) {
        val cylinder = entity as? EntityAtomicCylinder ?: return

        GL11.glPushMatrix()
        GL11.glTranslated(x, y, z)

        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glDisable(GL11.GL_CULL_FACE)
        GL11.glDisable(GL11.GL_LIGHTING)

        val tessellator = Tessellator.instance

        val currentSize = cylinder.getCurrentSize()
        val ticksCount = cylinder.getTicksCount()

        val pulse = (MathHelper.sin(ticksCount * 0.3f) * 0.2f + 0.8f)
        GL11.glColor4f(0.0f, 1.0f, 0.0f, 0.6f * pulse)

        renderCylinder(tessellator, currentSize)

        GL11.glEnable(GL11.GL_LIGHTING)
        GL11.glEnable(GL11.GL_CULL_FACE)
        GL11.glDisable(GL11.GL_BLEND)
        GL11.glEnable(GL11.GL_TEXTURE_2D)

        GL11.glPopMatrix()
    }

    private fun renderCylinder(tessellator: Tessellator, radius: Float) {
        val height = radius * 2.0f
        val segments = 16

        tessellator.startDrawing(GL11.GL_QUAD_STRIP)
        for (i in 0..segments) {
            val angle = (i * 2 * Math.PI / segments).toFloat()
            val x = MathHelper.cos(angle) * radius
            val z = MathHelper.sin(angle) * radius

            tessellator.addVertex(x.toDouble(), (-height / 2).toDouble(), z.toDouble())
            tessellator.addVertex(x.toDouble(), (height / 2).toDouble(), z.toDouble())
        }
        tessellator.draw()

        tessellator.startDrawing(GL11.GL_TRIANGLE_FAN)
        tessellator.addVertex(0.0, (height / 2).toDouble(), 0.0)
        for (i in 0..segments) {
            val angle = (i * 2 * Math.PI / segments).toFloat()
            val x = MathHelper.cos(angle) * radius
            val z = MathHelper.sin(angle) * radius
            tessellator.addVertex(x.toDouble(), (height / 2).toDouble(), z.toDouble())
        }
        tessellator.draw()

        tessellator.startDrawing(GL11.GL_TRIANGLE_FAN)
        tessellator.addVertex(0.0, (-height / 2).toDouble(), 0.0)
        for (i in 0..segments) {
            val angle = (i * 2 * Math.PI / segments).toFloat()
            val x = MathHelper.cos(angle) * radius
            val z = MathHelper.sin(angle) * radius
            tessellator.addVertex(x.toDouble(), (-height / 2).toDouble(), z.toDouble())
        }
        tessellator.draw()
    }

    override fun getEntityTexture(entity: Entity): ResourceLocation? {
        return null
    }
}