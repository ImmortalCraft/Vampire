package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.MCommand;
import net.immortalcraft.vampire.entity.UPlayer;

public abstract class VCommand extends MCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public UPlayer vme;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void fixSenderVars()
	{
		this.vme = UPlayer.get(this.sender);
	}
	
}
