package toraylife.mappetextras.events;

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
import org.lwjgl.opengl.GL11;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.capabilities.mainHand.IMainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.mainHand.MainHandProvider;
import toraylife.mappetextras.capabilities.offHand.IOffHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.offHand.OffHandProvider;
import toraylife.mappetextras.modules.client.ItemRendererAccessor;
import toraylife.mappetextras.modules.client.network.PacketProfileCapability;
import toraylife.mappetextras.modules.main.VersionChecker;
import toraylife.mappetextras.modules.utils.render.NpcPathRenderer;
import toraylife.mappetextras.network.Dispatcher;

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
            ItemRenderer itemRenderer = Minecraft.getMinecraft().getItemRenderer();
            ItemRendererAccessor itemRendererAccessor = ((ItemRendererAccessor) itemRenderer);
            AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;

            float a = abstractclientplayer.getSwingProgress(event.getPartialTicks());
            float b = 1.0F - (itemRendererAccessor.getPrevEquippedProgressMainHand() + (itemRendererAccessor.getEquippedProgressMainHand() - itemRendererAccessor.getPrevEquippedProgressMainHand()) * event.getPartialTicks());

            itemRendererAccessor.invokeRenderArmFirstPerson(a, b, EnumHandSide.LEFT);
            handleRotation(offHand);
            event.setCanceled(true);
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
