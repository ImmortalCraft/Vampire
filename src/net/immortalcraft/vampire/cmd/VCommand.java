package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.MCommand;
import net.immortalcraft.vampire.VPlayer;

public abstract class VCommand extends MCommand
{
	public VPlayer vme;
	
	@Override
	public void fixSenderVars()
	{
		this.vme = VPlayer.get(this.sender);
	}
}
