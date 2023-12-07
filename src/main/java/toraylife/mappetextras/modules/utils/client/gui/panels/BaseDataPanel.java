package toraylife.mappetextras.modules.utils.client.gui.panels;

import mchorse.mappet.api.utils.AbstractData;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.client.gui.utils.GuiStringFolderSearchListElement;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.framework.elements.list.GuiListElement;
import mchorse.mclib.client.gui.framework.elements.modals.GuiModal;
import mchorse.mclib.client.gui.framework.elements.modals.GuiPromptModal;
import mchorse.mclib.client.gui.framework.elements.utils.GuiContext;
import mchorse.mclib.client.gui.framework.elements.utils.GuiDrawable;
import mchorse.mclib.client.gui.mclib.GuiDashboardPanel;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import toraylife.mappetextras.modules.utils.DataType;
import toraylife.mappetextras.modules.utils.network.DataInteractionType;
import toraylife.mappetextras.modules.utils.network.PacketData;
import toraylife.mappetextras.network.Dispatcher;

public abstract class BaseDataPanel<T extends AbstractData> extends GuiDashboardPanel<GuiMappetDashboard> {

	public GuiElement iconBar;
	public GuiIconElement toggleSidebar;
	public GuiElement sidebar;

	public GuiElement buttons;
	public GuiIconElement add;
	public GuiIconElement dupe;
	public GuiIconElement rename;
	public GuiIconElement remove;
	public GuiStringFolderSearchListElement list;

	public BaseDataPanel(Minecraft mc, GuiMappetDashboard dashboard) {
		super(mc, dashboard);

		this.buttons = new GuiElement(mc);

		this.sidebar = new GuiElement(mc);
		this.sidebar.flex().relative(this).x(1F).w(200).h(1F).anchorX(1F);

		this.iconBar = new GuiElement(mc);
		this.iconBar.flex().relative(this.sidebar).x(-20).w(20).h(1F).column(0).stretch();

		this.toggleSidebar = new GuiIconElement(mc, Icons.RIGHTLOAD, (element) -> this.toggleSidebar());
		this.iconBar.add(this.toggleSidebar);

		GuiDrawable drawable = new GuiDrawable((context) -> this.font.drawStringWithShadow(I18n.format(this.getTitle()), this.list.area.x, this.area.y + 10, 0xffffff));

		this.add = new GuiIconElement(mc, Icons.ADD, this::addNewData);
		this.add.context(() ->
		{
			GuiSimpleContextMenu menu = new GuiSimpleContextMenu(mc);

			menu.action(Icons.ADD, IKey.lang("mappet.gui.panels.context.add_folder"), this::addFolder);

			return menu.shadow();
		});
		this.dupe = new GuiIconElement(mc, Icons.DUPE, this::dupeData);
		this.rename = new GuiIconElement(mc, Icons.EDIT, this::renameData);
		this.rename.context(() ->
		{
			GuiSimpleContextMenu menu = new GuiSimpleContextMenu(mc);

			menu.action(Icons.EDIT, IKey.lang("mappet.gui.panels.context.rename_folder"), this::renameFolder);

			return menu.shadow();
		});
		this.remove = new GuiIconElement(mc, Icons.REMOVE, this::removeData);
		this.remove.context(() ->
		{
			GuiSimpleContextMenu menu = new GuiSimpleContextMenu(mc);

			menu.action(Icons.REMOVE, IKey.lang("mappet.gui.panels.context.remove_folder"), this::removeFolder);

			return menu.shadow();
		});

		this.list = new GuiStringFolderSearchListElement(mc, (list) -> this.pickData(list.get(0)));
		this.list.flex().relative(this.sidebar).xy(10, 25).w(1F, -20).h(1F, -35);
		this.sidebar.add(drawable, this.list, this.buttons);

		this.buttons.flex().relative(this.list).x(1F).y(-20).anchorX(1F).row(0).resize();
		this.buttons.add(this.add, this.dupe, this.rename, this.remove);

		this.add(this.sidebar, this.iconBar);
	}

	private void pickData(String s) {

	}

	private void removeFolder() {
	}

	private void removeData(GuiIconElement element) {
	}

	private void renameFolder() {
	}

	private void renameData(GuiIconElement element) {
	}

	private void dupeData(GuiIconElement element) {
	}

	private void addFolder() {
	}

	protected void addNewData(GuiIconElement element) {
		this.addNewData(element, null);
	}

	protected void addNewData(GuiIconElement element, T data) {
		GuiModal.addFullModal(this.sidebar, () -> new GuiPromptModal(this.mc, IKey.lang("mappet.gui.panels.modals.add"), (name) -> this.addNewData(name, data)).filename());
	}

	public abstract DataType getType();

	protected void addNewData(String name, T data) {
		Dispatcher.sendToServer(new PacketData(DataInteractionType.SAVE, this.getType(), name, data));

		if (data == null) {
			data = (T) this.getType().manager.create(name);
			this.getType().manager.create(data.getId(), data.serializeNBT());
		} else {
			data.setId(name);
		}

		this.fill(data);
	}

	public abstract GuiListElement<T> initiateList();

	public abstract String getTitle();

	public abstract void fill(T data);

	private void toggleSidebar() {
		this.sidebar.toggleVisible();
		this.toggleSidebar.both(this.sidebar.isVisible() ? Icons.RIGHTLOAD : Icons.LEFTLOAD);

		if (this.sidebar.isVisible()) {
			//this.toggleWithSidebar();
			this.iconBar.flex().relative(this.sidebar).x(-20);
		} else {
			//this.toggleFull();
			this.iconBar.flex().relative(this).x(1F, -20);
		}

		this.resize();
	}

	@Override
	public abstract void appear();

	@Override
	public abstract void disappear();

	@Override
	public abstract void open();

	@Override
	public abstract void close();

	@Override
	public void draw(GuiContext context) {

		this.iconBar.area.draw(0x77000000);

		if (this.sidebar.isVisible()) {
			this.sidebar.area.draw(0xaa000000);
		}

		super.draw(context);
	}
}
