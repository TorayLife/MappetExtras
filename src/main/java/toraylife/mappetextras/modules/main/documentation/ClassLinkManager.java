package toraylife.mappetextras.modules.main.documentation;

import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocClass;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocEntry;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMethod;
import mchorse.mappet.client.gui.utils.overlays.GuiOverlay;
import mchorse.mappet.client.gui.utils.text.GuiText;
import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.main.mixins.late.documentation.MixinGuiDocumentationOverlayPanelAccessor;
import toraylife.mappetextras.modules.utils.MPEIcons;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassLinkManager {

    private static final Pattern CLASS_PATTERN = Pattern.compile("§6(.+?)(§r|$)");
    private static final Pattern CLASS_METHOD_PATTERN = Pattern.compile("§6(.+?)§r\\.§7(.+?)\\(.*?\\)§r");
    private final Minecraft mc;

    public ClassLinkManager(Minecraft mc) {
        this.mc = mc;
    }

    public Set<String> parseLinks(String text) {
        Set<String> links = new HashSet<>();
        findMatches(text, CLASS_PATTERN, links, false);
        findMatches(text, CLASS_METHOD_PATTERN, links, true);
        return links;
    }

    private void findMatches(String text, Pattern pattern, Set<String> matches, boolean methodsFlag) {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matches.add(methodsFlag ? (matcher.group(1) + "." + matcher.group(2)) : matcher.group(1));
        }
    }

    public Set<DocEntry> getEntries(Set<String> links) {
        Set<DocEntry> entries = new HashSet<>();
        for (String link : links) {
            DocEntry entry = findEntry(link);
            if (entry != null) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private DocEntry findEntry(String link) {
        String[] parts = link.split("\\.");
        String className = parts[0];
        String methodName = parts.length > 1 ? parts[1] : "";

        for (DocClass docClass : MixinGuiDocumentationOverlayPanelAccessor.getFieldDocs().classes) {
            if (docClass.getName().equals(className)) {
                if (methodName.isEmpty()) {
                    return docClass;
                }
                for (DocMethod method : docClass.methods) {
                    if (method.name.equals(methodName)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

    private GuiSimpleContextMenu createContextMenu(Set<DocEntry> entries) {
        GuiSimpleContextMenu menu = new GuiSimpleContextMenu(mc);
        for (DocEntry entry : entries) {
            String key = ((entry instanceof DocMethod) ? entry.parent.getName() + "." : "") + entry.getName();
            menu.action(MPEIcons.OTHER_GLOSARY, IKey.format("mappet.gui.scripts.context.docs", key), () -> openOverlay(entry), MappetExtras.mainColor);
        }
        return menu;
    }

    private void openOverlay(DocEntry entry) {
        GuiDocumentationOverlayPanel overlay = new GuiDocumentationOverlayPanel(mc, entry);
        GuiOverlay.addOverlay(GuiBase.getCurrent(), overlay, 0.9F, 0.9F);
    }

    public void addLinks(GuiText text, Set<DocEntry> searched) {
        GuiSimpleContextMenu contextMenu = this.createContextMenu(searched);
        text.context(() -> contextMenu);
    }
}
