package toraylife.mappetextras.events;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.client.network.PacketGuiOpenEvent;
import toraylife.mappetextras.modules.main.VersionChecker;
import toraylife.mappetextras.modules.utils.render.NpcPathRenderer;
import toraylife.mappetextras.network.Dispatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public void onPlayerOpenGuiEvent(GuiOpenEvent event) {
        if(event.getGui() != null) {
            String stringGui = event.getGui().toString();

            Map<String, String> guis = new HashMap<>();
            guis.put("GuiInventory", "inventory");
            guis.put("GuiContainerCreative", "creativeInventory");
            guis.put("GuiChat", "chat");
            guis.put("GuiIngameMenu", "ingameMenu");
            guis.put("GuiOptions", "options");
            guis.put("GuiVideoSettings", "videoSettings");
            guis.put("GuiEditSign", "editSign");
            guis.put("GuiMappetDashboard", "mappetDashboard");
            guis.put("GuiTriggerBlockScreen", "triggerBlock");
            guis.put("GuiEmitterBlockScreen", "emitterBlock");
            guis.put("GuiScriptedItemScreen", "scriptedItem");
            guis.put("GuiDashboard", "dashboard");
            guis.put("GuiCreativeScreen", "morphSelection");

            Set<String> keys = guis.keySet();
            String gui = "null";

            for (String key : keys) {
                if (stringGui.contains(key)) {
                    gui = guis.get(key);
                }
            }

            Dispatcher.sendToServer(new PacketGuiOpenEvent(gui));
        }
    }
}
