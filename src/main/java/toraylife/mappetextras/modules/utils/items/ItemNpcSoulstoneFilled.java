package toraylife.mappetextras.modules.utils.items;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNpcSoulstoneFilled extends Item {
    public ItemNpcSoulstoneFilled(){
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(IKey.lang("item.mappetextras.npc_soulstone.tooltip").toString());
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (!worldIn.isRemote) {
            if (!player.isCreative()) {
                return EnumActionResult.PASS;
            }

            EntityNpc entity = new EntityNpc(worldIn);
            BlockPos posOffset = pos.offset(facing);
            entity.setPosition((double)posOffset.getX() + hitX, (double)posOffset.getY() + hitY, (double)posOffset.getZ() + hitZ);

            NpcState npcState = new NpcState();
            npcState.deserializeNBT(stack.getTagCompound());

            entity.setState(npcState, true);
            entity.world.spawnEntity(entity);
            entity.initialize();
        }

        return stack.getItem() == Mappet.npcTool ? EnumActionResult.SUCCESS : super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
