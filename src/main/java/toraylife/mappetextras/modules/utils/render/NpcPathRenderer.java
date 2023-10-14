package toraylife.mappetextras.modules.utils.render;

import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.entities.EntityNpc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * Renders debug info for NPC pathfinding.
 */
public class NpcPathRenderer {

    private static final int PATH_COLOR = 0xFFFFFF;
    private static final float PATH_ALPHA = 0.5F;
    private static final float PATH_WIDTH = 2.0F;

    public boolean shouldRenderDebugPath() {
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        return settings.showDebugInfo && !settings.hideGUI;
    }

    public void renderNpcPaths(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        float partialTicks = event.getPartialTicks();

        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityNpc) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                GlStateManager.disableDepth();

                renderPathForNpc((EntityNpc) entity, player, partialTicks);

                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }

    private void renderPathForNpc(EntityNpc npc, EntityPlayerSP player, float partialTicks) {
        NpcState state = npc.getState();
        if (state == null) {
            return;
        }

        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();

        if (entity == null) {
            return;
        }

        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;

        drawPathLines(x, y, z, state.patrol);
        drawPatrolCirculateLine(x, y, z, state);
        drawPatrolCubes(x, y, z, state.patrol);
    }

    private void drawPatrolCubes(double x, double y, double z, List<BlockPos> patrol) {
        for (BlockPos pos : patrol) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(pos.getX(), pos.getY(), pos.getZ());
            GlStateManager.translate(-x, -y, -z);
            RenderGlobal.renderFilledBox(0.4F, 0F, 0.4F, 0.6F, 0.2F, 0.6F, 1F, 0F, 0F, 0.5F);
            GlStateManager.popMatrix();
        }
    }

    private void drawPatrolCirculateLine(double x, double y, double z, NpcState state) {
        if (state.patrolCirculate.get() && state.patrol.size() > 2) {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            GL11.glLineWidth(PATH_WIDTH);
            GlStateManager.pushMatrix();
            GlStateManager.translate(-x, -y, -z);
            BlockPos pos1 = state.patrol.get(0);
            BlockPos pos2 = state.patrol.get(state.patrol.size() - 1);
            drawPos(buffer, pos1);
            drawPos(buffer, pos2);
            tessellator.draw();
            GlStateManager.popMatrix();
        }
    }

    private void drawPathLines(double x, double y, double z, List<BlockPos> patrol) {
        BlockPos tempPos = null;
        for (BlockPos pos : patrol) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(-x, -y, -z);
            if (tempPos != null) {
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();
                buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
                GL11.glLineWidth(PATH_WIDTH);
                drawPos(buffer, tempPos);
                drawPos(buffer, pos);
                tessellator.draw();
            }
            tempPos = pos;
            GlStateManager.popMatrix();
        }
    }

    private void drawPos(BufferBuilder buffer, BlockPos pos) {
        buffer.pos(pos.getX() + 0.5F, pos.getY() + 0.1F, pos.getZ() + 0.5F).color(PATH_COLOR, PATH_COLOR, PATH_COLOR, PATH_ALPHA).endVertex();
    }
}