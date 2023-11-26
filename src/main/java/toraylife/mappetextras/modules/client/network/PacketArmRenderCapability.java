package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.offHand.OffHand;

public class PacketArmRenderCapability implements IMessage {
    NBTTagCompound profile;
    public PacketArmRenderCapability() {
    }

    public PacketArmRenderCapability(NBTTagCompound profile) {
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

    public static class ClientHandler extends ClientMessageHandler<PacketArmRenderCapability> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketArmRenderCapability message) {
            int hand = message.profile.getInteger("hand");

            if(hand == 0){
                MainHand mainHand = new MainHand().get(player);

                mainHand.deserializeNBT(message.profile);
            }else{
                OffHand offHand = new OffHand().get(player);

                offHand.deserializeNBT(message.profile);
            }
        }
    }
}