package toraylife.mappetextras.modules.utils.items;

import mchorse.mappet.Mappet;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.MappetExtras;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNpcPicker extends Item {
    public ItemNpcPicker() {
        this.setCreativeTab(Mappet.creativeTab);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(IKey.lang("item.mappetextras.npc_picker.tooltip").toString());
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (player.world.isRemote || !(target instanceof EntityNpc)) {
            player.swingArm(hand);
            return super.itemInteractionForEntity(stack, player, target, hand);
        }

        EntityNpc npc = (EntityNpc) target;
        final NBTTagCompound npcState = npc.getState().serializeNBT();
        final String id = npcState.getString("Id");

        if (id.isEmpty()) {
            player.sendStatusMessage(new TextComponentString(IKey.lang("item.mappetextras.npc_picker.message.notid").toString()), true);
            return false;
        }

        this.saveNPC(npcState);

        player.sendStatusMessage(new TextComponentString(IKey.format("item.mappetextras.npc_picker.message.savednpc",  id).toString()), true);

        return true;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isCreative() && playerIn.isSneaking()) {
            if (worldIn.isRemote) {
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }

            if (this.replaceItem(playerIn, playerIn.getHeldItem(handIn), handIn)) {
                return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private boolean replaceItem(EntityPlayer player, ItemStack stack, EnumHand hand) {
        ItemStack npcSoulstoneEmpty = new ItemStack(MappetExtras.npcSoulstoneEmpty);

        npcSoulstoneEmpty.setTagCompound(stack.getTagCompound());
        player.setHeldItem(hand, npcSoulstoneEmpty);
        return true;
    }

    public void saveNPC(NBTTagCompound npcState) {
        final String id = npcState.getString("Id");

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setTag("default", npcState);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("States", nbtTagCompound);

        Mappet.npcs.save(id, nbt);
    }
}