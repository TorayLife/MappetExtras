package toraylife.mappetextras.modules.main.documentation.mixins;

import com.google.gson.Gson;
import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocClass;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMerger;
import mchorse.mappet.client.gui.scripts.utils.documentation.Docs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Mixin(value = DocMerger.class, remap = false)
public abstract class MixinDocMerger {

    @Inject(
        method = "getMergedDocs",
        at = @At(
            value = "INVOKE_ASSIGN",
            target = "Lcom/google/gson/Gson;fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;"),
        remap = false,
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void addDocs(CallbackInfoReturnable<Docs> cir, Minecraft mc, Language lg, Gson gson, List<Docs> docsList) {
        InputStream stream = GuiDocumentationOverlayPanel.class.getResourceAsStream("/assets/mappetextras/docs.json");
        Scanner scanner = new Scanner(stream, "UTF-8");
        Docs mappetExtrasDocs = gson.fromJson(scanner.useDelimiter("\\A").next(), Docs.class);
        mappetExtrasDocs.source = "Mappet Extras";
        mappetExtrasDocs.classes.forEach(clazz -> {
            clazz.source = mappetExtrasDocs.source;
            clazz.methods.forEach(method -> method.source = mappetExtrasDocs.source);
        });
        docsList.add(mappetExtrasDocs);
    }

    @Redirect(method = "mergeDocs",
        at = @At(
        value = "FIELD",
        target = "Lmchorse/mappet/client/gui/scripts/utils/documentation/DocClass;doc:Ljava/lang/String;",
        opcode = Opcodes.PUTFIELD),
        remap = false
    )
    private static void mergeDocs(DocClass classMain, String input)
    {
        classMain.doc = input.trim().isEmpty() ? classMain.doc : input.trim();
    }
}
