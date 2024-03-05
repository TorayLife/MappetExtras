package toraylife.mappetextras.events;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.capabilities.CapabilitiesType;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandProvider;
import toraylife.mappetextras.capabilities.minecraftHUD.IMinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUDProvider;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandProvider;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.network.PacketCapability;
import toraylife.mappetextras.modules.client.network.PacketEvent;
import toraylife.mappetextras.modules.scripting.utils.ScriptVectorAngle;
import toraylife.mappetextras.modules.main.VersionChecker;
import toraylife.mappetextras.modules.utils.render.NpcPathRenderer;
import toraylife.mappetextras.network.Dispatcher;

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

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(MAINHAND, new MainHandProvider());
            event.addCapability(OFFHAND, new OffHandProvider());
            event.addCapability(MINECRAFTHUD, new MinecraftHUDProvider());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderHandEvent(RenderSpecificHandEvent event) {
        MainHand mainHand = MainHand.get(Minecraft.getMinecraft().player);
        OffHand offHand = OffHand.get(Minecraft.getMinecraft().player);
        ScriptVector mPos = mainHand.getPosition();
        ScriptVectorAngle mRotate = mainHand.getRotate();
        ScriptVector oPos = offHand.getPosition();
        ScriptVectorAngle oRotate = offHand.getRotate();

        if (event.getHand() == MAIN_HAND) {
            if(!mainHand.isRender()) {
                event.setCanceled(true);
                return;
            }

            if(mPos.x == 0 && mPos.y == 0 && mPos.z == 0 && mRotate.angle == 0 && mRotate.x == 0 && mRotate.y == 0 && mRotate.z == 0){
                return;
            }

            handleRotation(mainHand);
        }

        if (event.getHand() == OFF_HAND) {
            if(!offHand.isRender()) {
                event.setCanceled(true);
                return;
            }

            if(oPos.x == 0 && oPos.y == 0 && oPos.z == 0 && oRotate.angle == 0 && oRotate.x == 0 && oRotate.y == 0 && oRotate.z == 0){
                return;
            }

            handleRotation(offHand);
        }
    }

    private void handleRotation(OffHand offHand) {
        ScriptVectorAngle rotate = offHand.getRotate();
        ScriptVector pos = offHand.getPosition();

        GL11.glRotated(rotate.angle, rotate.x, rotate.y, rotate.z);
        GL11.glTranslated(pos.x, pos.y, pos.z);
    }

    private void handleRotation(MainHand mainHand) {
        ScriptVectorAngle rotate = mainHand.getRotate();
        ScriptVector pos = mainHand.getPosition();

        GL11.glRotated(rotate.angle, rotate.x, rotate.y, rotate.z);
        GL11.glTranslated(pos.x, pos.y, pos.z);
    }

    @SubscribeEvent
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        final IMainHand mainHand = MainHand.get(player);
        final IOffHand offHand = OffHand.get(player);
        final IMinecraftHUD minecraftHUD = MinecraftHUD.get(player);

        Dispatcher.sendTo(new PacketCapability(mainHand.serializeNBT(), CapabilitiesType.ARM_RENDER), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(offHand.serializeNBT(), CapabilitiesType.ARM_RENDER), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(minecraftHUD.serializeNBT(), CapabilitiesType.MINECRAFT_HUD), (EntityPlayerMP) player);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGuiPre(RenderGameOverlayEvent.Pre event) {
        MinecraftHUD minecraftHUD = MinecraftHUD.get(Minecraft.getMinecraft().player);

        minecraftHUD.setName(event.getType().name());
        boolean render = minecraftHUD.isRender();
        ScriptVector scale = minecraftHUD.getScale();
        ScriptVector pos = minecraftHUD.getPosition();
        ScriptVectorAngle rotate = minecraftHUD.getRotate();

        event.setCanceled(!render);

        if (render && (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR && event.getType() != RenderGameOverlayEvent.ElementType.ALL)) {
            GL11.glPushMatrix();
            GL11.glRotated(rotate.angle, rotate.x, rotate.y, rotate.z);
            GL11.glScaled(scale.x, scale.y, scale.z);
            GL11.glTranslated(pos.x, pos.y, pos.z);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGuiPost(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR && event.getType() != RenderGameOverlayEvent.ElementType.ALL)
            GL11.glPopMatrix();
    }
}