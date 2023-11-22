package toraylife.mappetextras.modules.scripting.mixins.late.UI;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import mchorse.mappet.api.scripts.user.mappet.IMappetUIContext;
import mchorse.mappet.api.ui.components.UIComponent;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.main.mixins.utils.MixinTargetName;
import toraylife.mappetextras.modules.scripting.mixins.utils.CallbackableComponent;

@Mixin(value = UIComponent.class, remap = false)
@MixinTargetName("mchorse.mappet.api.ui.components.UIComponent")
public class MixinUIComponent<T extends UIComponent> implements CallbackableComponent<T> {

    ScriptObjectMirror function;

    /**
     * Add callback function to component, that calls if you call {@link IMappetUIContext#handleCallbacks(mchorse.mappet.api.scripts.user.IScriptEvent)} in handler function.
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
     * <p>
     * c - {@link mchorse.mappet.api.scripts.user.IScriptEvent},
     * <p>
     * component - this component. Fast replacement for {@link IMappetUIContext#get(String)}.
     * <p>
     * context - {@link IMappetUIContext},
     * <p>
     * id - {@link String} component id.
     *
     * </p>
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
    public T callback(ScriptObjectMirror function) {
        this.function = function;
        return (T) (Object) this;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public ScriptObjectMirror getCallback() {
        return this.function;
    }

    @Override
    @mchorse.mappet.api.ui.utils.DiscardMethod // DO NOT REMOVE
    public boolean hasCallback() {
        return this.function != null;
    }
}
