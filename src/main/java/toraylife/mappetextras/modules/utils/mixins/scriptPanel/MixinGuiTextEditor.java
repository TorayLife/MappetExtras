package toraylife.mappetextras.modules.utils.mixins.scriptPanel;

import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import toraylife.mappetextras.modules.utils.UtilsModule;
import toraylife.mappetextras.modules.utils.client.gui.codeEditor.GuiTextEditorSearchable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(value = GuiTextEditor.class, remap = false)
public abstract class MixinGuiTextEditor extends MixinGuiMultiTextElement implements GuiTextEditorSearchable {

    @Shadow
    private int placements;
    public boolean isSearching = false;


    public Pattern pattern;

    public MixinGuiTextEditor(Minecraft mc) {
        super(mc);
    }

    @Override
    public boolean isSearching() {
        return isSearching;
    }

    @Override
    public void setSearching(boolean searching) {
        this.isSearching = searching;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Inject(method = "drawForeground", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void drawForeground(GuiContext context, CallbackInfo ci) {
        if (!isSearching || pattern == null) {
            return;
        }

        int x = this.area.x + this.padding + this.placements;
        int y = this.area.y + this.padding;

        Matcher matcher = pattern.matcher(this.getText());

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            int length = end - start;
            int indexOfLineBreaker = 0;
            int temp = -1;
            int lines = 0;
            int lastLineBreak = this.getText().lastIndexOf("\n");
            while ((temp = this.getText().indexOf("\n", temp + 1)) < start && temp < lastLineBreak) {
                indexOfLineBreaker = temp;
                lines++;
                if (this.getText().indexOf("\n", temp + 1) == lastLineBreak) {
                    indexOfLineBreaker = lastLineBreak;
                    lines++;
                    break;
                }
            }
            int drawX = x + (indexOfLineBreaker == 0 ? this.padding : 0) + 2 - this.horizontal.scroll;
            int drawY = y + lines * this.lineHeight - this.vertical.scroll;

            if (indexOfLineBreaker > start) {
                drawY -= this.lineHeight;
                drawX += this.padding;
            } else {
                drawX += this.font.getStringWidth(this.getText().substring(indexOfLineBreaker, start));
            }

            this.drawSelectionArea(drawX, drawY, drawX + this.font.getStringWidth(this.getText().substring(start, end)) - 4, drawY);
        }
        matcher.reset();
    }

    private void drawSelectionArea(int x1, int y1, int x2, int y2) {
        final int selectionPad = 2;
        int color = UtilsModule.getInstance().codeSearchColor.get();

        boolean middle = y2 > y1 + this.lineHeight;
        boolean bottom = y2 > y1;

        int endX = bottom || middle ? this.area.ex() : x2 + selectionPad;
        int endY = bottom && !middle ? y2 : y1 + this.font.FONT_HEIGHT;

        if (!bottom && !middle) {
            endY += selectionPad;
        }

        Gui.drawRect(x1 - selectionPad, y1 - selectionPad, endX, endY, color);

        if (middle) {
            Gui.drawRect(this.area.x, y1 + this.font.FONT_HEIGHT, this.area.ex(), y2, color);
        }

        if (bottom) {
            Gui.drawRect(this.area.x, y2, x2 + selectionPad, y2 + this.font.FONT_HEIGHT + selectionPad, color);
        }
    }
}
