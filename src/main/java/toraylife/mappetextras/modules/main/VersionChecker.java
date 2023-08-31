package toraylife.mappetextras.modules.main;

import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.main.client.gui.VersionChangelogScreen;

public class VersionChecker {

    public ForgeVersion.CheckResult getVersionCheckResult() {
        FMLCommonHandler.instance().findContainerFor(MappetExtras.instance);
        ModContainer modContainer = Loader.instance().getIndexedModList().get(MappetExtras.MOD_ID);
        return ForgeVersion.getResult(modContainer);
    }

    public ITextComponent getUpdateMessage() {
        ForgeVersion.CheckResult result = this.getVersionCheckResult();
        ITextComponent message = new TextComponentString("");
        String messageKey = "mappetextras.main_module.update";
        IKey lang;
        switch (result.status) {
            case UP_TO_DATE:
                lang = IKey.format(messageKey + ".up_to_date", MappetExtras.NAME);
                message.appendText(lang.get());
                break;
            case OUTDATED:
                String newVersion = result.target != null ? result.target.toString() : "";
                lang = IKey.format(messageKey + ".outdated", MappetExtras.NAME, newVersion, MappetExtras.VERSION);
                message.appendText(lang.get());
                TextComponentString viewChangelogString = new TextComponentString(IKey.lang(messageKey + ".outdated_view").get());
                // 🩼🩼
                viewChangelogString.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ""){
                    @Override
                    public Action getAction() {
                        Minecraft.getMinecraft().displayGuiScreen(new VersionChangelogScreen(result));
                        return super.getAction();
                    }
                });
                TextComponentString tooltip = new TextComponentString(IKey.lang(messageKey + ".outdated_tooltip").get());
                viewChangelogString.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip));
                message.appendSibling(viewChangelogString);
                break;
            default:
                lang = IKey.format(messageKey + ".failed", MappetExtras.NAME);
                message.appendText(lang.get());
                break;
        }
        return message;
    }
}
