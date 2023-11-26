package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;

public class PacketMinecraftHUDCapability implements IMessage {
    NBTTagCompound profile;
    public PacketMinecraftHUDCapability() {
    }

    public PacketMinecraftHUDCapability(NBTTagCompound profile) {
        this.profile = profile;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.profile = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.profile);
    }

    public static class ClientHandler extends ClientMessageHandler<PacketMinecraftHUDCapability> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketMinecraftHUDCapability message) {
            MinecraftHUD minecraftHUD = MinecraftHUD.get(player);

            minecraftHUD.deserializeNBT(message.profile);
        }
    }
}
