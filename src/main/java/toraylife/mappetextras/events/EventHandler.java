package toraylife.mappetextras.events;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import toraylife.mappetextras.capabilities.camera.Camera;
import toraylife.mappetextras.capabilities.camera.CameraProvider;
import toraylife.mappetextras.capabilities.camera.ICamera;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandProvider;
import toraylife.mappetextras.capabilities.minecraftHUD.IMinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUDProvider;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandProvider;
import toraylife.mappetextras.capabilities.shake.IShake;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.capabilities.shake.ShakeProvider;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.network.*;
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
    public static final ResourceLocation SHAKE = new ResourceLocation(MappetExtras.MOD_ID, "shake");
    public static final ResourceLocation CAMERA = new ResourceLocation(MappetExtras.MOD_ID, "camera");

    public static boolean isReversed = false;
    public static float animationProgress = 0.0F;

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
            String gui = event.getGui().toString();

            Dispatcher.sendToServer(new PacketGuiOpenEvent(this.processText(gui)));
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
            event.addCapability(SHAKE, new ShakeProvider());
            event.addCapability(CAMERA, new CameraProvider());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderHandEvent(RenderSpecificHandEvent event) {
        MainHand mainHand = MainHand.get(Minecraft.getMinecraft().player);
        OffHand offHand = OffHand.get(Minecraft.getMinecraft().player);

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
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        final IMainHand mainHand = MainHand.get(player);
        final IOffHand offHand = OffHand.get(player);
        final IMinecraftHUD minecraftHUD = MinecraftHUD.get(player);
        final IShake shake = Shake.get(player);
        final ICamera camera = Camera.get(player);

        Dispatcher.sendTo(new PacketCapability(mainHand.serializeNBT(), AccessType.ARM_RENDER), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(offHand.serializeNBT(), AccessType.ARM_RENDER), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(minecraftHUD.serializeNBT(), AccessType.MINECRAFT_HUD), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(shake.serializeNBT(), AccessType.SHAKE), (EntityPlayerMP) player);
        Dispatcher.sendTo(new PacketCapability(camera.serializeNBT(), AccessType.CAMERA), (EntityPlayerMP) player);
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

        if(!render){
            event.setCanceled(!render);
            return;
        }

        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR && event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            GL11.glPushMatrix();
            GL11.glDepthMask(true);
            GL11.glRotated(rotate.angle, rotate.x, rotate.y, rotate.z);
            GL11.glScaled(scale.x, scale.y, scale.z);
            GL11.glTranslated(pos.x, pos.y, pos.z);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGuiPost(RenderGameOverlayEvent.Post event) {
        MinecraftHUD minecraftHUD = MinecraftHUD.get(Minecraft.getMinecraft().player);
        boolean render = minecraftHUD.isRender();

        if(!render){
            event.setCanceled(!render);
            return;
        }

        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR && event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onCameraRender(EntityViewRenderEvent.CameraSetup event) {
        Shake shake = Shake.get(Minecraft.getMinecraft().player);

        if (shake.isActive()) {
            this.tickRender(shake);
            float ticks = animationProgress;
            float s = 1.0F - (float) shake.getZoom() * ticks / 10.0F;
            GlStateManager.rotate((ticks - (float) shake.getRotate().angle) * (float) shake.getRotation(), (float) shake.getRotate().x, (float) shake.getRotate().y, (float) shake.getRotate().z);
            GlStateManager.scale(1.0F, 1.0F, s);
        }
    }

    public void tickRender(Shake shake) {
        if (isReversed) {
            animationProgress = Math.max(animationProgress - (float) shake.getSpeed().x, 0.0F);
            if (animationProgress == 0.0F)
                isReversed = false;
        } else {
            animationProgress = Math.min(animationProgress + (float) shake.getSpeed().y, 1.0F);
            if (animationProgress == 1.0F)
                isReversed = true;
        }
    }

    @SubscribeEvent
    public void onPreRenderGame(EntityViewRenderEvent.CameraSetup event) {
        Camera camera = Camera.get(Minecraft.getMinecraft().player);

        if(camera.isEnabled()) {
            event.setPitch(camera.getPitch());
            event.setYaw(camera.getYaw());
            event.setRoll(camera.getRoll());
        }
    }
}