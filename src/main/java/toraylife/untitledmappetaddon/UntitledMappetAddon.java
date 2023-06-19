package toraylife.untitledmappetaddon;

import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = UntitledMappetAddon.MOD_ID,
        version = UntitledMappetAddon.VERSION,
        dependencies =
                "required-after:mclib@[@MCLIB@,);"+
                "required-after:mappet@[@MAPPET@,);"
)
public class UntitledMappetAddon
{
    public static final String MOD_ID = "untitledmappetaddon";

    public static final String VERSION = "@VERSION@";

    @Mod.Instance
    public static UntitledMappetAddon instance;

}
