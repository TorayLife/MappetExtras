package toraylife.mappetextras.modules.client;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec2f;

public enum ClientData {
    PESPECTIVE{
        @Override
        public Object process(NBTTagCompound data) {
                return data.getInteger(this.name());
        }
    },

    MOUSEPOSITION{
        @Override
        public Object process(NBTTagCompound data) {
            NBTTagCompound pos = data.getCompoundTag(this.name());
            return new Vec2f(pos.getInteger("x"), pos.getInteger("y"));
        }
    },

    CLIPBOARD{
        @Override
        public Object process(NBTTagCompound data) {
            return data.getString(this.name());
        }
    },

    SETTING{
        @Override
        public Object process(NBTTagCompound data) {
            String setting = data.getString(this.name());
            return SETTING.converter(setting);
        }
    },

    RESOLUTION{
        @Override
        public Object process(NBTTagCompound data) {
            NBTTagCompound display = data.getCompoundTag(this.name());
            return new Vec2f(display.getInteger("x"), display.getInteger("y"));
        }
    };

    ClientData(){

    }

    public abstract Object process(NBTTagCompound data);

    private Object converter(String setting){
        try {
            return Integer.parseInt(setting);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(setting);
            } catch (NumberFormatException g) {
                try {
                    return Float.parseFloat(setting);
                } catch (NumberFormatException f) {
                    try {
                        return Boolean.parseBoolean(setting);
                    } catch (NumberFormatException x) {
                        return setting;
                    }
                }
            }
        }
    }
}