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
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.items.ItemNpcPicker;
import toraylife.mappetextras.modules.utils.items.ItemNpcSoulstoneEmpty;
import toraylife.mappetextras.modules.utils.items.ItemNpcSoulstoneFilled;

import java.util.Calendar;

public class RegisterHandler {
    public static final Calendar calendar = Calendar.getInstance();
    @SubscribeEvent
    public void onItemsRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(MappetExtras.npcPicker = (new ItemNpcPicker()).setRegistryName(new ResourceLocation("mappetextras", "npc_picker")).setUnlocalizedName("mappetextras.npc_picker"));
        event.getRegistry().register(MappetExtras.npcSoulstoneEmpty = (new ItemNpcSoulstoneEmpty()).setRegistryName(new ResourceLocation("mappetextras", "npc_soulstone_empty")).setUnlocalizedName("mappetextras.npc_soulstone"));
        event.getRegistry().register(MappetExtras.npcSoulstoneFilled = (new ItemNpcSoulstoneFilled()).setRegistryName(new ResourceLocation("mappetextras", "npc_soulstone_filled")).setUnlocalizedName("mappetextras.npc_soulstone"));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelRegistry(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcPicker, 0, getNpcToolTexture("npc_picker"));
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcSoulstoneEmpty, 0, getNpcToolTexture("npc_soulstone_empty"));
        ModelLoader.setCustomModelResourceLocation(MappetExtras.npcSoulstoneFilled, 0, getNpcToolTexture("npc_soulstone_filled"));
    }

    public ModelResourceLocation getNpcToolTexture(String toolname) {
        String postfix = "";

        if (isWinter()) {
            postfix = "_winter";
        }

        if (isChristmas()) {
            postfix = "_christmas";
        } else if (isEaster()) {
            postfix = "_easter";
        } else if (isAprilFoolsDay()) {
            postfix = "_april";
        } else if (isHalloween()) {
            postfix = "_halloween";
        }

        if (isLlamaBirthday()) {
            postfix = "_llama";
        }else if (isDemonBirthday()) {
            postfix = "_demon";
        } else if (isDyamoBirthday()) {
            postfix = "_dyamo";
        }

        return new ModelResourceLocation(MappetExtras.MOD_ID + ":" + toolname + "/" + toolname + postfix, "inventory");
    }

    public boolean isChristmas() {
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;
    }

    public boolean isAprilFoolsDay() {
        return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DATE) <= 2;
    }

    public boolean isWinter() {
        int month = calendar.get(Calendar.MONTH);

        return month == Calendar.DECEMBER || month == Calendar.JANUARY || month == Calendar.FEBRUARY;
    }

    public boolean isEaster() {
        Calendar easterDate = getEasterDate(calendar.get(Calendar.YEAR));

        return calendar.get(Calendar.MONTH) == easterDate.get(Calendar.MONTH) && calendar.get(Calendar.DATE) == easterDate.get(Calendar.DATE);
    }

    public boolean isHalloween() {
        return calendar.get(Calendar.MONTH) == Calendar.OCTOBER && calendar.get(Calendar.DATE) >= 24;
    }

    public boolean isLlamaBirthday(){
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DATE) == 1;
    }

    public boolean isDemonBirthday(){
        return calendar.get(Calendar.MONTH) == Calendar.MAY && calendar.get(Calendar.DATE) == 28;
    }

    public boolean isDyamoBirthday(){
        return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DATE) == 21;
    }

    public Calendar getEasterDate(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int month = (h + l + 114) / 31;
        int day = (h + l + 114) % 31;

        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month - 1, day + 1);

        return calendar;
    }
}
