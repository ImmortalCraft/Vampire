package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import net.immortalcraft.vampire.*;
import net.immortalcraft.vampire.cmdreq.ReqIsVampire;

public class CmdVampireShriek extends VCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireShriek()
	{
		// Aliases
		this.addAliases("shriek");
		
		// Requirements
		this.addRequirements(ReqIsVampire.get());
		this.addRequirements(ReqHasPerm.get(Perm.SHRIEK.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		vme.shriek();
	}
	
}
