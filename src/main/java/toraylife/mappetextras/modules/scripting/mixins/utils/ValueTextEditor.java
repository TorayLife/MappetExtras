package toraylife.mappetextras.modules.scripting.mixins.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.netty.buffer.ByteBuf;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.utils.GuiLabel;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.config.gui.GuiConfigPanel;
import mchorse.mclib.config.values.*;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.scripting.ScriptingModule;

import java.util.Arrays;
import java.util.List;

public class ValueTextEditor extends GenericValue<String> implements IServerValue, IConfigGuiProvider {
    public ValueTextEditor(String id) {
        super(id, "");
    }

    public ValueTextEditor(String id, String defaultValue) {
        super(id, defaultValue);
    }

    public void resetServer() {
        this.serverValue = null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<GuiElement> getFields(Minecraft mc, GuiConfigPanel gui) {
        GuiElement element = new GuiElement(mc);
        GuiLabel label = Elements.label(IKey.lang(this.getConfig().getValueLabelKey(this)), 20).anchor(0.0F, 0.5F);
        GuiTextEditor textEditor = new GuiTextEditor(mc, this::setValue);
        textEditor.setText(((ScriptingModule) ScriptingModule.getInstance()).defaultTextScript.get());
        textEditor.background().flex().wh(240, 80);
        element.flex().h(100).column(0);
        element.add(label, textEditor.removeTooltip());
        return Arrays.asList(element.tooltip(IKey.lang(this.getConfig().getValueCommentKey(this))));
    }

    public void valueFromJSON(JsonElement element) {
        this.set(element.getAsString());
    }

    public JsonElement valueToJSON() {
        return new JsonPrimitive((String)this.value);
    }

    public void valueFromNBT(NBTBase tag) {
        if (tag instanceof NBTTagString) {
            this.set(((NBTTagString)tag).getString());
        }

    }

    public NBTBase valueToNBT() {
        return new NBTTagString(this.value == null ? "" : (String)this.value);
    }

    public boolean parseFromCommand(String value) {
        this.set(value);
        return true;
    }

    public void copy(Value value) {
        if (value instanceof ValueTextEditor) {
            this.value = ((ValueTextEditor)value).value;
        }

    }

    public void copyServer(Value value) {
        if (value instanceof ValueTextEditor) {
            this.serverValue = ((ValueTextEditor)value).value;
        }

    }

    public void fromBytes(ByteBuf buffer) {
        this.superFromBytes(buffer);
        this.value = ByteBufUtils.readUTF8String(buffer);
        this.defaultValue = ByteBufUtils.readUTF8String(buffer);
    }

    public void toBytes(ByteBuf buffer) {
        this.superToBytes(buffer);
        ByteBufUtils.writeUTF8String(buffer, this.value == null ? "" : (String)this.value);
        ByteBufUtils.writeUTF8String(buffer, this.defaultValue == null ? "" : (String)this.defaultValue);
    }

    public void valueFromBytes(ByteBuf buffer) {
        this.value = ByteBufUtils.readUTF8String(buffer);
    }

    public void valueToBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String(buffer, this.value == null ? "" : (String)this.value);
    }

    public String toString() {
        return (String)this.value;
    }

    public ValueTextEditor copy() {
        ValueTextEditor clone = new ValueTextEditor(this.id);
        clone.defaultValue = this.defaultValue;
        clone.value = this.value;
        clone.serverValue = this.serverValue;
        return clone;
    }
}
