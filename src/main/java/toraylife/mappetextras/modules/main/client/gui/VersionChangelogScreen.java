package toraylife.mappetextras.modules.main.client.gui;

import mchorse.mappet.client.gui.utils.GuiScrollLogsElement;
import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiButtonElement;
import mchorse.mclib.client.gui.framework.elements.list.GuiStringListElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.utils.Color;
import mchorse.mclib.utils.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.versioning.ComparableVersion;
import org.apache.logging.log4j.LogManager;
import toraylife.mappetextras.MappetExtras;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionChangelogScreen extends GuiBase {

    GuiButtonElement openLinkUpdate;

    public VersionChangelogScreen(ForgeVersion.CheckResult result) {
        Minecraft mc = Minecraft.getMinecraft();

        List<String> changes = new ArrayList<>();
        result.changes.forEach((key, element) -> changes.add(key.toString() + ": " + element));

        String targetVersion = result.target == null ? (result.status == ForgeVersion.Status.AHEAD ? MappetExtras.VERSION : IKey.lang("mappetextras.error").get()) : result.target.toString();

        VersionChangelogElement versionChangelogElement = new VersionChangelogElement(mc, result.changes, result.url);

        versionChangelogElement.flex().relative(this.viewport).wh(0.5F, 0.5F);

        GuiElement frame = Elements.column(mc, 5,
            Elements.label(IKey.format("mappetextras.main_module.update.screen_title", MappetExtras.VERSION, targetVersion)).background(),
            versionChangelogElement
        );

        frame.flex().relative(this.viewport).xy(0.5F, 0.5F).w(0.5F).anchor(0.5F, 0.5F);

        this.root.add(frame);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }


}

class VersionChangelogElement extends GuiElement {

    public Map<String, String> versions;
    public GuiStringListElement scrollList;
    public GuiText description;
    public GuiButtonElement buttonVisitModPage;
    public VersionChangelogElement(Minecraft mc, Map<ComparableVersion, String> versions, String url) {
        super(mc);

        Map<String, String> castedVersions = new HashMap<>();

        versions.forEach((key, element) -> castedVersions.put(key.toString(), element));

        this.versions = castedVersions;

        this.scrollList = new GuiStringListElement(mc, (list) -> fill(versions.get(new ComparableVersion(list.get(0)))));
        this.scrollList.add(this.versions.keySet());
        this.description = new GuiText(mc);
        this.buttonVisitModPage = new GuiButtonElement(mc, IKey.lang("mappetextras.main_module.update.button_update_link"), (b) -> this.openUpdateLink(url));

        this.scrollList.flex().relative(this).xy(0, 0).w(0.25F, -5).h(1F);
        this.description.flex().relative(this).x(0.25F).w(0.75F).h(1F, -20);
        this.buttonVisitModPage.flex().relative(this).xy(0.25F, 1F).anchorY(1F).w(0.75F).h(20);

        this.scrollList.background();
        this.description.padding(5);

        this.add(scrollList, description, buttonVisitModPage);
    }

    public void fill(String description) {
        this.description.text(description);
    }

    public void openUpdateLink(String urlString) {
        try
        {
            URI url = new URI(urlString);
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop").invoke(null);
            oclass.getMethod("browse", URI.class).invoke(object, url);
        }
        catch (Throwable throwable1)
        {
            Throwable throwable = throwable1.getCause();
            LogManager.getLogger().error("Couldn't open link: {}", (Object)(throwable == null ? "<UNKNOWN>" : throwable.getMessage()));
        }
    }

    @Override
    public void draw(GuiContext context) {
        this.area.draw(ColorUtils.HALF_BLACK, this.scrollList.area.w + 5, 0, 0,0);
        super.draw(context);
    }
}
