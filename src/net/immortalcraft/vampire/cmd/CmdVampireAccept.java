package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import net.immortalcraft.vampire.Perm;

public class CmdVampireAccept extends VCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireAccept()
	{
		// Aliases
		this.addAliases("a", "accept");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.TRADE_ACCEPT.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		vme.tradeAccept();
	}
	
}
