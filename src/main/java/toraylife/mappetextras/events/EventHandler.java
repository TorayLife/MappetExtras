package toraylife.mappetextras.events;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.network.PacketEvent;
import toraylife.mappetextras.modules.main.VersionChecker;
import toraylife.mappetextras.modules.utils.render.NpcPathRenderer;
import toraylife.mappetextras.network.Dispatcher;

public class EventHandler {
    private static final NpcPathRenderer npcPathRenderer = new NpcPathRenderer();

    @SubscribeEvent
    public void onPlayerJoinEvent(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        VersionChecker versionChecker = new VersionChecker();
        versionChecker.start();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (!npcPathRenderer.shouldRenderDebugPath()) {
            return;
        }

        npcPathRenderer.renderNpcPaths(event);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerOpenGuiEvent(GuiOpenEvent event) {
        if (event.getGui() != null) {
            NBTTagCompound data = new NBTTagCompound();
            data.setString("gui", this.processText( event.getGui().toString() ));

            Dispatcher.sendToServer(new PacketEvent(AccessType.OPEN, data));
        }
    }

    @SideOnly(Side.CLIENT)
    public void onPlayerCloseGuiEvent(GuiScreen guiScreen){
        if (guiScreen != null) {
            NBTTagCompound data = new NBTTagCompound();
            data.setString("gui", this.processText( guiScreen.toString() ));

            Dispatcher.sendToServer(new PacketEvent(AccessType.CLOSE, data));
        }
    }

    public String processText(String text){
        int lastDot = text.lastIndexOf('.');
        text = text.substring(lastDot + 1);

        int indexOfAt = text.indexOf('@');
        if(indexOfAt != -1) {
            text = text.substring(0, indexOfAt);
        }

        text = text.trim();
        if(text.toLowerCase().startsWith("gui")) {
            text = text.substring(3);
        }

        if(!text.isEmpty()) {
            text = Character.toLowerCase(text.charAt(0)) + text.substring(1);
        }

        return text;
    }
}