package toraylife.mappetextras.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.main.MainModule;
import toraylife.mappetextras.modules.main.VersionChecker;

public class EventHandler {

    private static boolean updateMessageSended = false;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerJoinEvent(EntityJoinWorldEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer) || event.getEntity() == null) {
            return;
        }

        if (!EventHandler.updateMessageSended) {
            sendUpdateMessage(false);
            EventHandler.updateMessageSended = true;
        }
    }

    public void sendUpdateMessage(boolean ignoreSettings) {
        if (!ignoreSettings && !MainModule.getInstance().showVersionUpdateMessage.get()) {
            return;
        }

        VersionChecker checker = new VersionChecker();
        ForgeVersion.CheckResult versionCheckResult = checker.getVersionCheckResult();

        if (!ignoreSettings && MainModule.getInstance().showUpdateOnlyIfOutdated.get() && versionCheckResult.status != ForgeVersion.Status.OUTDATED) {
            return;
        }

        ITextComponent message = checker.getUpdateMessage();
        Minecraft.getMinecraft().player.sendMessage(message);
    }
}
