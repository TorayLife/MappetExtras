package toraylife.mappetextras.modules.client.providers;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Mouse;
import toraylife.mappetextras.modules.client.ClientData;

import java.awt.*;

public class MousePositionProvider implements IClientDataProvider{
    @Override
    public NBTTagCompound getData(NBTTagCompound nbtTagCompound) {
        int x;
        int y;
        if(nbtTagCompound.getBoolean("isInsideWindow")){
            x = Mouse.getX();
            y = Mouse.getY();
        }else{
            Point location = MouseInfo.getPointerInfo().getLocation();
            x = (int) location.getX();
            y = (int) location.getY();
        }

        NBTTagCompound object = new NBTTagCompound();
        object.setInteger("x", x);
        object.setInteger("y", y);

        nbtTagCompound.setTag(ClientData.MOUSEPOSITION.toString(), object);

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
        final NBTTagCompound tag = data.getCompoundTag(ClientData.MOUSEPOSITION.toString());
        final boolean isInsideWindow = data.getBoolean("isInsideWindow");

        int x = tag.getInteger("x");
        int y = tag.getInteger("y");

        if(isInsideWindow){
            Mouse.setCursorPosition(x, y * -1 + Minecraft.getMinecraft().displayHeight);
        }else{
            try {
                Robot robot = new Robot();
                robot.mouseMove(x, y);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}