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
            return data.getString(this.name());
        }
    };

    ClientData(){

    }

    public abstract Object process(NBTTagCompound data);
}