package toraylife.mappetextras.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import toraylife.mappetextras.modules.main.MainModule;
import toraylife.mappetextras.modules.main.VersionChecker;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        sendUpdateMessage(event.player, false);
    }

    public void sendUpdateMessage(EntityPlayer player, boolean ignoreSettings) {
        if (!ignoreSettings && !MainModule.getInstance().showVersionUpdateMessage.get()) {
            return;
        }

        VersionChecker checker = new VersionChecker();
        ForgeVersion.CheckResult versionCheckResult = checker.getVersionCheckResult();

        if (!ignoreSettings && MainModule.getInstance().showUpdateOnlyIfOutdated.get() && versionCheckResult.status != ForgeVersion.Status.OUTDATED) {
            return;
        }

        ITextComponent message = checker.getUpdateMessage();
        player.sendMessage(message);
    }
}
