package toraylife.mappetextras.network;

import java.util.Iterator;
import mchorse.mappet.network.client.blocks.ClientHandlerEditConditionModel;
import mchorse.mappet.network.client.blocks.ClientHandlerEditEmitter;
import mchorse.mappet.network.client.blocks.ClientHandlerEditRegion;
import mchorse.mappet.network.client.blocks.ClientHandlerEditTrigger;
import mchorse.mappet.network.client.content.ClientHandlerContentData;
import mchorse.mappet.network.client.content.ClientHandlerContentNames;
import mchorse.mappet.network.client.content.ClientHandlerServerSettings;
import mchorse.mappet.network.client.content.ClientHandlerStates;
import mchorse.mappet.network.client.crafting.ClientHandlerCraft;
import mchorse.mappet.network.client.crafting.ClientHandlerCraftingTable;
import mchorse.mappet.network.client.dialogue.ClientHandlerDialogueFragment;
import mchorse.mappet.network.client.events.ClientHandlerEventPlayerHotkeys;
import mchorse.mappet.network.client.events.ClientHandlerPlayerJournal;
import mchorse.mappet.network.client.factions.ClientHandlerFactions;
import mchorse.mappet.network.client.huds.ClientHandlerHUDMorph;
import mchorse.mappet.network.client.huds.ClientHandlerHUDScene;
import mchorse.mappet.network.client.items.ClientHandlerScriptedItemInfo;
import mchorse.mappet.network.client.logs.ClientHandlerLogs;
import mchorse.mappet.network.client.npc.ClientHandlerNpcList;
import mchorse.mappet.network.client.npc.ClientHandlerNpcState;
import mchorse.mappet.network.client.npc.ClientHandlerNpcStateChange;
import mchorse.mappet.network.client.quests.ClientHandlerQuest;
import mchorse.mappet.network.client.quests.ClientHandlerQuests;
import mchorse.mappet.network.client.scripts.ClientHandlerEntityRotations;
import mchorse.mappet.network.client.scripts.ClientHandlerRepl;
import mchorse.mappet.network.client.scripts.ClientHandlerSound;
import mchorse.mappet.network.client.scripts.ClientHandlerWorldMorph;
import mchorse.mappet.network.client.ui.ClientHandlerCloseUI;
import mchorse.mappet.network.client.ui.ClientHandlerUI;
import mchorse.mappet.network.client.ui.ClientHandlerUIData;
import mchorse.mappet.network.common.blocks.PacketEditConditionModel;
import mchorse.mappet.network.common.blocks.PacketEditEmitter;
import mchorse.mappet.network.common.blocks.PacketEditRegion;
import mchorse.mappet.network.common.blocks.PacketEditTrigger;
import mchorse.mappet.network.common.content.PacketContentData;
import mchorse.mappet.network.common.content.PacketContentExit;
import mchorse.mappet.network.common.content.PacketContentFolder;
import mchorse.mappet.network.common.content.PacketContentNames;
import mchorse.mappet.network.common.content.PacketContentRequestData;
import mchorse.mappet.network.common.content.PacketContentRequestNames;
import mchorse.mappet.network.common.content.PacketRequestServerSettings;
import mchorse.mappet.network.common.content.PacketRequestStates;
import mchorse.mappet.network.common.content.PacketServerSettings;
import mchorse.mappet.network.common.content.PacketStates;
import mchorse.mappet.network.common.crafting.PacketCraft;
import mchorse.mappet.network.common.crafting.PacketCraftingTable;
import mchorse.mappet.network.common.dialogue.PacketDialogueFragment;
import mchorse.mappet.network.common.dialogue.PacketFinishDialogue;
import mchorse.mappet.network.common.dialogue.PacketPickReply;
import mchorse.mappet.network.common.events.PacketEventHotkey;
import mchorse.mappet.network.common.events.PacketEventHotkeys;
import mchorse.mappet.network.common.events.PacketPlayerJournal;
import mchorse.mappet.network.common.factions.PacketFactions;
import mchorse.mappet.network.common.factions.PacketRequestFactions;
import mchorse.mappet.network.common.huds.PacketHUDMorph;
import mchorse.mappet.network.common.huds.PacketHUDScene;
import mchorse.mappet.network.common.items.PacketScriptedItemInfo;
import mchorse.mappet.network.common.logs.PacketLogs;
import mchorse.mappet.network.common.logs.PacketRequestLogs;
import mchorse.mappet.network.common.npc.PacketNpcJump;
import mchorse.mappet.network.common.npc.PacketNpcList;
import mchorse.mappet.network.common.npc.PacketNpcState;
import mchorse.mappet.network.common.npc.PacketNpcStateChange;
import mchorse.mappet.network.common.npc.PacketNpcTool;
import mchorse.mappet.network.common.quests.PacketQuest;
import mchorse.mappet.network.common.quests.PacketQuestAction;
import mchorse.mappet.network.common.quests.PacketQuestVisibility;
import mchorse.mappet.network.common.quests.PacketQuests;
import mchorse.mappet.network.common.scripts.PacketClick;
import mchorse.mappet.network.common.scripts.PacketEntityRotations;
import mchorse.mappet.network.common.scripts.PacketRepl;
import mchorse.mappet.network.common.scripts.PacketSound;
import mchorse.mappet.network.common.scripts.PacketWorldMorph;
import mchorse.mappet.network.common.ui.PacketCloseUI;
import mchorse.mappet.network.common.ui.PacketUI;
import mchorse.mappet.network.common.ui.PacketUIData;
import mchorse.mappet.network.server.blocks.ServerHandlerEditConditionModel;
import mchorse.mappet.network.server.blocks.ServerHandlerEditEmitter;
import mchorse.mappet.network.server.blocks.ServerHandlerEditRegion;
import mchorse.mappet.network.server.blocks.ServerHandlerEditTrigger;
import mchorse.mappet.network.server.content.ServerHandlerContentData;
import mchorse.mappet.network.server.content.ServerHandlerContentExit;
import mchorse.mappet.network.server.content.ServerHandlerContentFolder;
import mchorse.mappet.network.server.content.ServerHandlerContentRequestData;
import mchorse.mappet.network.server.content.ServerHandlerContentRequestNames;
import mchorse.mappet.network.server.content.ServerHandlerRequestServerSettings;
import mchorse.mappet.network.server.content.ServerHandlerRequestStates;
import mchorse.mappet.network.server.content.ServerHandlerServerSettings;
import mchorse.mappet.network.server.content.ServerHandlerStates;
import mchorse.mappet.network.server.crafting.ServerHandlerCraft;
import mchorse.mappet.network.server.crafting.ServerHandlerCraftingTable;
import mchorse.mappet.network.server.dialogue.ServerHandlerFinishDialogue;
import mchorse.mappet.network.server.dialogue.ServerHandlerPickReply;
import mchorse.mappet.network.server.events.ServerHandlerEventHotkey;
import mchorse.mappet.network.server.events.ServerHandlerPlayerJournal;
import mchorse.mappet.network.server.factions.ServerHandlerRequestFactions;
import mchorse.mappet.network.server.items.ServerHandlerScriptedItemInfo;
import mchorse.mappet.network.server.logs.ServerHandlerLogs;
import mchorse.mappet.network.server.npc.ServerHandlerNpcJump;
import mchorse.mappet.network.server.npc.ServerHandlerNpcList;
import mchorse.mappet.network.server.npc.ServerHandlerNpcState;
import mchorse.mappet.network.server.npc.ServerHandlerNpcTool;
import mchorse.mappet.network.server.quests.ServerHandlerQuestAction;
import mchorse.mappet.network.server.quests.ServerHandlerQuestVisibility;
import mchorse.mappet.network.server.scripts.ServerHandlerClick;
import mchorse.mappet.network.server.scripts.ServerHandlerRepl;
import mchorse.mappet.network.server.ui.ServerHandlerUI;
import mchorse.mappet.network.server.ui.ServerHandlerUIData;
import mchorse.mclib.network.AbstractDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import toraylife.mappetextras.MappetExtras;

public class Dispatcher {
    public static final AbstractDispatcher DISPATCHER = new AbstractDispatcher(MappetExtras.MOD_ID) {
        public void register() {

        }
    };

    public static void sendToTracked(Entity entity, IMessage message) {
        EntityTracker tracker = ((WorldServer)entity.world).getEntityTracker();
        Iterator var3 = tracker.getTrackingPlayers(entity).iterator();

        while(var3.hasNext()) {
            EntityPlayer player = (EntityPlayer)var3.next();
            sendTo(message, (EntityPlayerMP)player);
        }

    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        DISPATCHER.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        DISPATCHER.sendToServer(message);
    }

    public static void register() {
        DISPATCHER.register();
    }
}
