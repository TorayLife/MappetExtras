package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.capabilities.camera.Camera;
import toraylife.mappetextras.capabilities.mainHand.MainHand;
import toraylife.mappetextras.capabilities.minecraftHUD.MinecraftHUD;
import toraylife.mappetextras.capabilities.offHand.OffHand;
import toraylife.mappetextras.capabilities.shake.Shake;
import toraylife.mappetextras.modules.client.AccessType;

public class PacketCapability implements IMessage {
    NBTTagCompound profile;
    AccessType type;
    public PacketCapability() {
    }

    public PacketCapability(NBTTagCompound profile, AccessType type) {
        this.profile = profile;
        this.type = type;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.profile = ByteBufUtils.readTag(buf);
        this.type = AccessType.valueOf(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.profile);
        ByteBufUtils.writeUTF8String(buf, this.type.toString());
    }

    public static class ClientHandler extends ClientMessageHandler<PacketCapability> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketCapability message) {
            AccessType type = message.type;

            switch (type){
                case ARM_RENDER:
                    this.armRender(player, message);
                case CAMERA:
                    this.camera(player, message);
                case MINECRAFT_HUD:
                    this.minecraftHUD(player, message);
                case SHAKE:
                    this.shake(player, message);
            }
        }

        private void shake(EntityPlayerSP player, PacketCapability message){
            Shake shake = Shake.get(player);

            shake.deserializeNBT(message.profile);
        }

        private void minecraftHUD(EntityPlayerSP player, PacketCapability message){
            MinecraftHUD minecraftHUD = MinecraftHUD.get(player);

            minecraftHUD.deserializeNBT(message.profile);
        }

        private void camera(EntityPlayerSP player, PacketCapability message){
            Camera camera = Camera.get(player);

            camera.deserializeNBT(message.profile);
        }

        private void armRender(EntityPlayerSP player, PacketCapability message){
            int hand = message.profile.getInteger("hand");

            if(hand == 0){
                MainHand mainHand = MainHand.get(player);

                mainHand.deserializeNBT(message.profile);
            }else{
                OffHand offHand = OffHand.get(player);

                offHand.deserializeNBT(message.profile);
            }
        }
    }
}
