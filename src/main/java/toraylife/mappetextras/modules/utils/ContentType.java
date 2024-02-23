package toraylife.mappetextras.modules.utils;

import mchorse.mappet.api.utils.AbstractData;
import mchorse.mappet.api.utils.IContentType;
import mchorse.mappet.api.utils.manager.IManager;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.mixins.utils.GuiMappetDashboardAccessor;

public enum ContentType implements IContentType {
    DIMENSION() {
        @Override
        public IManager<? extends AbstractData> getManager() {
            return MappetExtras.customDimensionManager;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public GuiMappetDashboardPanel get(GuiMappetDashboard dashboard) {
            return ((GuiMappetDashboardAccessor) dashboard).getDimensionsPanel();
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IKey getPickLabel() {
            return IKey.lang("mappetextras.gui.panels.dimensions");
        }

        @Override
        public String getName() {
            return "DIMENSION";
        }
    }
}