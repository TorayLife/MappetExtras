package toraylife.mappetextras.events;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.client.network.PacketGuiOpenEvent;
import org.lwjgl.opengl.GL11;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandProvider;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandProvider;
import toraylife.mappetextras.modules.client.network.PacketProfileCapability;
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

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer){
            event.addCapability(MAINHAND, new MainHandProvider());
            event.addCapability(OFFHAND, new OffHandProvider());
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
        NBTTagCompound NBTangle = offHand.getRotate();

        double angle = NBTangle.getDouble("angle");
        double x = NBTangle.getDouble("x");
        double y = NBTangle.getDouble("y");
        double z = NBTangle.getDouble("z");

        double posX = offHand.getPosition().x;
        double posY = offHand.getPosition().y;
        double posZ = offHand.getPosition().z;

        GL11.glRotated(angle, x, y, z);
        GL11.glTranslated(posX, posY, posZ);
    }

    private void handleRotation(MainHand mainHand) {
        NBTTagCompound NBTangle = mainHand.getRotate();

        double angle = NBTangle.getDouble("angle");
        double x = NBTangle.getDouble("x");
        double y = NBTangle.getDouble("y");
        double z = NBTangle.getDouble("z");

        double posX = mainHand.getPosition().x;
        double posY = mainHand.getPosition().y;
        double posZ = mainHand.getPosition().z;

        GL11.glRotated(angle, x, y, z);
        GL11.glTranslated(posX, posY, posZ);
    }

    @SubscribeEvent
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;

        final IMainHand mainHand = new MainHand().get(player);
        final IOffHand offHand = new OffHand().get(player);

        Dispatcher.sendTo(new PacketProfileCapability(mainHand.serializeNBT()), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketProfileCapability(offHand.serializeNBT()), (EntityPlayerMP) player);
    }
}
