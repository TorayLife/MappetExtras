package toraylife.mappetextras.events;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.utils.items.ItemNpcPicker;
import toraylife.mappetextras.modules.utils.items.ItemNpcSoulstoneEmpty;
import toraylife.mappetextras.modules.utils.items.ItemNpcSoulstoneFilled;

public class RegisterHandler {
    public RegisterHandler() {
    }

    ModelResourceLocation npcPickerTexture = new ModelResourceLocation("mappetextras:npc_picker", "inventory");
    ModelResourceLocation npcSoulstoneEmptyTexture = new ModelResourceLocation("mappetextras:npc_soulstone_empty", "inventory");
    ModelResourceLocation npcSoulstoneFilledTexture = new ModelResourceLocation("mappetextras:npc_soulstone_filled", "inventory");

    @SubscribeEvent
    public void onItemsRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(MappetExtras.npcPicker = ((Item) (new ItemNpcPicker()).setRegistryName(new ResourceLocation("mappetextras", "npc_picker"))).setUnlocalizedName("mappetextras.npc_picker"));
        event.getRegistry().register(MappetExtras.npcSoulstoneEmpty = ((Item) (new ItemNpcSoulstoneEmpty()).setRegistryName(new ResourceLocation("mappetextras", "npc_soulstone_empty"))).setUnlocalizedName("mappetextras.npc_soulstone"));
        event.getRegistry().register(MappetExtras.npcSoulstoneFilled = ((Item) (new ItemNpcSoulstoneFilled()).setRegistryName(new ResourceLocation("mappetextras", "npc_soulstone_filled"))).setUnlocalizedName("mappetextras.npc_soulstone"));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelRegistry(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcPicker, 0, npcPickerTexture);
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcSoulstoneEmpty, 0, npcSoulstoneEmptyTexture);
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcSoulstoneFilled, 0, npcSoulstoneFilledTexture);
    }
}
