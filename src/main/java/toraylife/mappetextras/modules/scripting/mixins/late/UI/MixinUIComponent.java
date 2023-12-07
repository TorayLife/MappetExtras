package toraylife.mappetextras.modules.scripting.mixins.late.UI;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.user.mappet.IMappetUIContext;
import mchorse.mappet.api.ui.components.UIComponent;
import mchorse.mappet.api.ui.components.UIIconButtonComponent;
import mchorse.mappet.api.ui.utils.UIContextItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.mixins.utils.CallbackableComponent;

import java.util.HashMap;
import java.util.List;

@Mixin(value = UIComponent.class, remap = false)
@MixinTargetName("mchorse.mappet.api.ui.components.UIComponent")
public abstract class MixinUIComponent implements CallbackableComponent<UIComponent> {

    @Shadow
    protected abstract void change(String... properties);

    @Shadow
    public List<UIContextItem> context;
    ScriptObjectMirror function;
    public HashMap<String, ScriptObjectMirror> contextFunctions = new HashMap<>();

    /**
     * Add callback function to component, that calls if you call {@link IMappetUIContext#handleCallbacks(IScriptEvent)} in handler function.
     *
     * <pre>{@code
     * function main(c) {
     *     var ui = mappet.createUI(c, "handler").background();
     *     var button = ui.button("Start...").id("button").callback(function(c, component, context, id) {
     *         component.label("Pressed: " + context.data.getInt(id));
     *     });
     *     button.rxy(0.5, 0.5).wh(160, 20).anchor(0.5);
     *     c.getSubject().openUI(ui);
     * }
     *
     * function handler(c) {
     *     var context = c.player.getUIContext();
     *     context.handleCallbacks(c);
     * }
     * }</pre>
     *
     * <p>
     * Arguments:
     *
     * c - {@link mchorse.mappet.api.scripts.user.IScriptEvent},
     *
     * component - this component. Fast replacement for {@link IMappetUIContext#get(String)}.
     *
     * context - {@link IMappetUIContext},
     *
     * id - {@link String} component id.
     *
     * <p>You can also use pre-defined functions:</p>
     *
     * <pre>{@code
     * function main(c) {
     *     var ui = mappet.createUI(c, "handler").background();
     *     var button = ui.button("Start...").id("button").callback(buttonCallback);
     *     button.rxy(0.5, 0.5).wh(160, 20).anchor(0.5);
     *     c.getSubject().openUI(ui);
     * }
     *
     * function handler(c) {
     *     var context = c.player.getUIContext();
     *     context.handleCallbacks(c);
     * }
     *
     * function buttonCallback(c, component, context, id) {
     *     component.label("Pressed: " + context.data.getInt(id));
     * }
     * }</pre>
     *
     * <p>If you don't define handler function, callback will called automatically.</p>
     *
     * <p>Also, you don't have to write all the arguments.</p>
     *
     * <pre>{@code
     * function main(c) {
     *     var ui = mappet.createUI().background();
     *     var button = ui.button("Start...").id("button").callback(function(c) {
     *         c.send("Hello world!");
     *     });
     *     button.rxy(0.5, 0.5).wh(160, 20).anchor(0.5);
     *     c.getSubject().openUI(ui);
     * }
     * }</pre>
     *
     * @param function - function that will be called.
     * @return UIComponent - this component
     */
    public UIComponent callback(ScriptObjectMirror function) {
        this.function = function;
        return (UIComponent) (Object) this;
    }


    /**
     * Add callback function to component context item.
     *
     * <pre>{@code
     * function main(c) {
     *     var ui = mappet.createUI().background();
     *     var label = ui.label("Hello!").id("label").tooltip("Right click me...");
     *     label.rxy(0.5, 0.5).wh(160, 20).anchor(0.5).labelAnchor(0.5);
     *     label.context("bubble", "a", "How are you?");
     *
     *     label.callback("a", function() {
     *         label.label("I'm fine, and you?");
     *     });
     *
     *     c.getSubject().openUI(ui);
     * }
     * }</pre>
     *
     * @param function - function that will be called.
     */
    public UIComponent callback(String action, ScriptObjectMirror function) {
        this.contextFunctions.put(action, function);
        return (UIComponent) (Object) this;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public ScriptObjectMirror getCallback() {
        return this.function;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public HashMap<String, ScriptObjectMirror> getContextCallbacks() {
        return this.contextFunctions;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public boolean hasCallback() {
        return this.function != null;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public boolean hasContextCallbacks() {
        return this.contextFunctions.size() != 0;
    }

    /**
     * Add a context menu item with callback.
     *
     * <pre>{@code
     *    function main(c) {
     *    var ui = mappet.createUI().background();
     *    var label = ui.label("Hello!").id("label").tooltip("Right click me...");
     *
     *    label.rxy(0.5, 0.5).wh(160, 20).anchor(0.5).labelAnchor(0.5);
     *    label.context("bubble", "a", "How are you?", 0, function() {
     *         label.label("I'm fine, and you?");
     *    });
     *    label.context("remove", "b", "...", 0xff0033, function(c, component, context) {
     *        context.get("label").label("");
     *    });
     *
     *    c.getSubject().openUI(ui);
     *    }
     * }</pre>
     *
     * @param icon     Icon ID (see {@link UIIconButtonComponent}).
     * @param action   Action ID that will be used for handling with {@link IMappetUIContext#getContext()}.
     * @param label    Label that will be displayed in the context menu item.
     * @param color    Background color highlight (in RGB hex format).
     * @param function Function that will be called. (see {@link UIComponent#callback(ScriptObjectMirror)}
     */
    public UIComponent context(String icon, String action, String label, int color, ScriptObjectMirror function) {
        this.contextFunctions.put(action, function);
        return ((UIComponent) (Object) this).context(icon, action, label, color);
    }
}
