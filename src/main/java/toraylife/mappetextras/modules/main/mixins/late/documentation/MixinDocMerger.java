package toraylife.mappetextras.modules.main.mixins.late.documentation;

import com.google.gson.Gson;
import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMerger;
import mchorse.mappet.client.gui.scripts.utils.documentation.Docs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Mixin(value = DocMerger.class, remap = false)
public abstract class MixinDocMerger {

    @Inject(
            method = "addAddonsDocs",
            at = @At(value = "TAIL"),
            remap = false,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void addDocs(Gson gson, List<Docs> docsList, CallbackInfo ci) {
        InputStream stream = GuiDocumentationOverlayPanel.class.getResourceAsStream("/assets/mappetextras/docs.json");
        Scanner scanner = new Scanner(stream, "UTF-8");
        Docs mappetExtrasDocs = gson.fromJson(scanner.useDelimiter("\\A").next(), Docs.class);
        mappetExtrasDocs.source = "MappetExtras";
        mappetExtrasDocs.classes.forEach(clazz -> {
            clazz.source = mappetExtrasDocs.source;
            clazz.methods.forEach(method -> method.source = mappetExtrasDocs.source);
        });
        docsList.add(mappetExtrasDocs);
    }
}
