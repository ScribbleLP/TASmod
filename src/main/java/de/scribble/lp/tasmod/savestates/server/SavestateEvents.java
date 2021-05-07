package de.scribble.lp.tasmod.savestates.server;

import de.pfannekuchen.infogui.gui.SettingsGui;
import de.scribble.lp.tasmod.ClientProxy;
import de.scribble.lp.tasmod.CommonProxy;
import de.scribble.lp.tasmod.monitoring.Monitor;
import de.scribble.lp.tasmod.playback.PlaybackPacket;
import de.scribble.lp.tasmod.savetas.SaveTASPacket;
import de.scribble.lp.tasmod.virtual.VirtualKeybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SavestateEvents {
	@SubscribeEvent
	public void onRender(TickEvent.RenderTickEvent ev) {
		if(VirtualKeybindings.isKeyDownExceptTextfield(ClientProxy.savestateSaveKey)){
			
			CommonProxy.NETWORK.sendToServer(new SavestatePacket());
			
		}else if(VirtualKeybindings.isKeyDownExceptTextfield(ClientProxy.savestateLoadKey)){
			
			CommonProxy.NETWORK.sendToServer(new LoadstatePacket());
			
		}else if(VirtualKeybindings.isKeyDownExceptTextfield(ClientProxy.testingKey)) {
			Minecraft.getMinecraft().player.setSprinting(true);
			EntityPlayerSP player=Minecraft.getMinecraft().player;
			player.jumpMovementFactor=(float)((double)player.jumpMovementFactor + (double)0.02F * 0.3D);
			
		}else if(VirtualKeybindings.isKeyDownExceptTextfield(ClientProxy.infoGuiKey)) { // Sorry.. 
			Minecraft.getMinecraft().displayGuiScreen(new SettingsGui());
		}else if(VirtualKeybindings.isKeyDown(ClientProxy.stopkey)) {
			CommonProxy.NETWORK.sendToServer(new PlaybackPacket(false));
		}
	}
}
