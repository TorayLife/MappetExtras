package toraylife.mappetextras.events;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandProvider;
import toraylife.mappetextras.capabilities.minecraftHUD.IMinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUDProvider;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandProvider;
import toraylife.mappetextras.modules.client.network.PacketGuiOpenEvent;
import toraylife.mappetextras.modules.client.network.PacketArmRenderCapability;
import toraylife.mappetextras.modules.client.network.PacketMinecraftHUDCapability;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.modules.main.VersionChecker;
import toraylife.mappetextras.modules.utils.render.NpcPathRenderer;
import toraylife.mappetextras.network.Dispatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static net.minecraft.util.EnumHand.MAIN_HAND;
import static net.minecraft.util.EnumHand.OFF_HAND;

public class EventHandler {
    private static final NpcPathRenderer npcPathRenderer = new NpcPathRenderer();
    public static final ResourceLocation MAINHAND = new ResourceLocation(MappetExtras.MOD_ID, "mainHand");
    public static final ResourceLocation OFFHAND = new ResourceLocation(MappetExtras.MOD_ID, "offHand");
    public static final ResourceLocation MINECRAFTHUD = new ResourceLocation(MappetExtras.MOD_ID, "minecraftHUD");

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

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer){
            event.addCapability(MAINHAND, new MainHandProvider());
            event.addCapability(OFFHAND, new OffHandProvider());
            event.addCapability(MINECRAFTHUD, new MinecraftHUDProvider());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderHandEvent(RenderSpecificHandEvent event) {
        MainHand mainHand = new MainHand().get(Minecraft.getMinecraft().player);
        OffHand offHand = new OffHand().get(Minecraft.getMinecraft().player);

        if (event.getHand() == MAIN_HAND) {
            handleRotation(mainHand);
            event.setCanceled(!mainHand.isRender());
        }

        if (event.getHand() == OFF_HAND) {
            handleRotation(offHand);
            event.setCanceled(!offHand.isRender());
        }
    }

    private void handleRotation(OffHand offHand) {
        ScriptVectorAngle vectorAngle = offHand.getRotate();
        ScriptVector pos = offHand.getPosition();

        double angle = vectorAngle.angle;
        double x = vectorAngle.x;
        double y = vectorAngle.y;
        double z = vectorAngle.z;

        double posX = pos.x;
        double posY = pos.y;
        double posZ = pos.z;

        GL11.glRotated(angle, x, y, z);
        GL11.glTranslated(posX, posY, posZ);
    }

    private void handleRotation(MainHand mainHand) {
        ScriptVectorAngle vectorAngle = mainHand.getRotate();
        ScriptVector pos = mainHand.getPosition();

        double angle = vectorAngle.angle;
        double x = vectorAngle.x;
        double y = vectorAngle.y;
        double z = vectorAngle.z;

        double posX = pos.x;
        double posY = pos.y;
        double posZ = pos.z;

        GL11.glRotated(angle, x, y, z);
        GL11.glTranslated(posX, posY, posZ);
    }

    @SubscribeEvent
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;

        final IMainHand mainHand = new MainHand().get(player);
        final IOffHand offHand = new OffHand().get(player);
        final IMinecraftHUD minecraftHUD = MinecraftHUD.get(player);

        Dispatcher.sendTo(new PacketArmRenderCapability(mainHand.serializeNBT()), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketArmRenderCapability(offHand.serializeNBT()), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketMinecraftHUDCapability(minecraftHUD.serializeNBT()), (EntityPlayerMP) player);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGuiPre(RenderGameOverlayEvent.Pre event) throws NoSuchFieldException, IllegalAccessException {
        MinecraftHUD minecraftHUD = MinecraftHUD.get(Minecraft.getMinecraft().player);
        String[] HUDs = new String[]{
            "ALL", "HELMET", "PORTAL", "CROSSHAIRS", "BOSSHEALTH", "BOSSINFO", "ARMOR", "HEALTH", "FOOD", "AIR", "HOTBAR", "EXPERIENCE", "TEXT", "HEALTHMOUNT", "JUMPBAR", "CHAT", "PLAYER_LIST", "DEBUG", "POTION_ICONS", "SUBTITLES", "FPS_GRAPH", "VIGNETTE"
        };

        for(String hud : HUDs){
            if(event.getType() == RenderGameOverlayEvent.ElementType.valueOf(hud))
            {
                minecraftHUD.setName(hud);

                boolean render = minecraftHUD.isRender();
                ScriptVector scale = minecraftHUD.getScale();
                ScriptVector pos = minecraftHUD.getPosition();
                ScriptVectorAngle rotate = minecraftHUD.getRotate();

                event.setCanceled(!render);
                GL11.glPushMatrix();
                GL11.glRotated(rotate.angle, rotate.x, rotate.y, rotate.z);
                GL11.glScaled(scale.x, scale.y, scale.z);
                GL11.glTranslated(pos.x, pos.y, pos.z);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGuiPost(RenderGameOverlayEvent.Post event){
        GL11.glPopMatrix();
    }
}
