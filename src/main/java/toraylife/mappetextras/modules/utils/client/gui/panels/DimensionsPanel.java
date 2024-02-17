package toraylife.mappetextras.modules.utils.client.gui.panels;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.ScriptExecutionFork;
import mchorse.mappet.api.utils.IContentType;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiCirculateElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTrackpadElement;
import mchorse.mclib.client.gui.utils.Elements;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.world.*;
import toraylife.mappetextras.modules.utils.ContentType;
import toraylife.mappetextras.modules.utils.MPEIcons;
import toraylife.mappetextras.modules.utils.dimensions.FlatDimensionProvider;
import toraylife.mappetextras.modules.utils.dimensions.Dimension;
import toraylife.mappetextras.modules.utils.dimensions.VoidDimensionProvider;
import toraylife.mappetextras.modules.utils.network.PacketChangeDimension;
import toraylife.mappetextras.modules.utils.network.PacketRegistrationDimension;
import toraylife.mappetextras.network.Dispatcher;

import java.util.ArrayList;

public class DimensionsPanel extends GuiMappetDashboardPanel<Dimension> {

    public GuiTrackpadElement dimensionId;
    public GuiToggleElement initializeOnStartup;
    public GuiCirculateElement worldProvider;
    public GuiElement settings;

    public GuiIconElement tpToDimension;
    public GuiIconElement tpToOverworld;
    public GuiIconElement registerDimension;
    public GuiIconElement unregisterDimension;

    public DimensionsPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
        this.namesList.setFileIcon(Icons.SPHERE);
        this.dimensionId = new GuiTrackpadElement(mc, t -> {
            this.data.dimensionId.set(t.intValue());
        //    this.registerDimension.setEnabled(DimensionManager.getRegisteredDimensions().entrySet().stream().flatMapToInt(e -> Arrays.stream(e.getValue().toIntArray())).noneMatch(d -> d == t.intValue()));
        });
        this.initializeOnStartup = new GuiToggleElement(mc, IKey.lang("mappetextras.gui.dimensions.initializeOnStartup"), b -> this.data.initializeOnStartup.set(b.isToggled()));
        this.worldProvider = new GuiCirculateElement(mc, this::setWorldProvider);
        for (String provider : getWorldProviders()) {
            this.worldProvider.addLabel(IKey.str(provider.substring(provider.lastIndexOf(".") + 1)));
        }
        settings = Elements.column(mc, 10, 10,
                Elements.label(IKey.lang("mappetextras.gui.dimensions.id")),
                this.dimensionId,
                this.initializeOnStartup,
                Elements.label(IKey.lang("mappetextras.gui.dimensions.worldProvider")),
                this.worldProvider
        );
        settings.flex().relative(this.editor).wh(0.5F, 1F);
        this.editor.add(settings);

        this.tpToDimension = new GuiIconElement(mc, Icons.SHIFT_FORWARD, icon -> Dispatcher.sendToServer(new PacketChangeDimension(data.dimensionId.get())));
        this.tpToDimension.tooltip(IKey.lang("mappetextras.utils.dimensions.tpToDimension"));
        this.tpToOverworld = new GuiIconElement(mc, Icons.SHIFT_BACKWARD, icon -> Dispatcher.sendToServer(new PacketChangeDimension(0)));
        this.tpToOverworld.tooltip(IKey.lang("mappetextras.utils.dimensions.tpToOverworld"));
        this.registerDimension = new GuiIconElement(mc, MPEIcons.PAINT_PENCIL, icon -> {
            this.save();
            //TODO rewrite with Task API
            CommonProxy.eventHandler.addExecutable(new ScriptExecutionFork(null, a-> Dispatcher.sendToServer(new PacketRegistrationDimension(this.data.getId())), 5));
        });
        this.registerDimension.tooltip(IKey.lang("mappetextras.utils.dimensions.registerDimension"));
        this.registerDimension.disabledColor(0xFF880000);
        this.unregisterDimension = new GuiIconElement(mc, MPEIcons.PAINT_ERASER, icon -> {
            Dispatcher.sendToServer(new PacketRegistrationDimension(this.data.getId(), true));
        });
        this.unregisterDimension.tooltip(IKey.lang("mappetextras.utils.dimensions.unregisterDimension"));



        iconBar.add(this.tpToDimension, this.tpToOverworld, this.registerDimension, this.unregisterDimension);

        this.fill(data);
    }

    public void fill(Dimension data, boolean allowed) {
        super.fill(data, allowed);
        this.settings.setVisible(data != null);
        if (data == null) {
            return;
        }
        //this.registerDimension.setEnabled(DimensionManager.getRegisteredDimensions().entrySet().stream().flatMapToInt(e -> Arrays.stream(e.getValue().toIntArray())).noneMatch(d -> d == data.dimensionId.get()));
        this.dimensionId.setValue(data.dimensionId.get());
        this.initializeOnStartup.toggled(data.initializeOnStartup.get());
        this.worldProvider.setValue(getWorldProviders().indexOf(data.worldProvider.getClass().getName()));
    }

    public void setWorldProvider(GuiCirculateElement guiCirculateElement) {
        try {
            String name = getWorldProviders().get(guiCirculateElement.getValue());
            this.data.worldProvider = (WorldProvider) Class.forName(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IContentType getType() {
        return ContentType.DIMENSION;
    }

    @Override
    public String getTitle() {
        return "mappetextras.gui.panels.dimensions";
    }

    public ArrayList<String> getWorldProviders() {
        ArrayList<String> worldProviders = new ArrayList<>();
        worldProviders.add(WorldProviderSurface.class.getName());
        worldProviders.add(WorldProviderHell.class.getName());
        worldProviders.add(WorldProviderEnd.class.getName());
        worldProviders.add(FlatDimensionProvider.class.getName());
        worldProviders.add(VoidDimensionProvider.class.getName());
        // TODO: maybe use reflections and find all worldProviders from mods?
        return worldProviders;
    }
}
