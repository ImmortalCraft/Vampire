package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import net.immortalcraft.vampire.*;

public class CmdVampireModeBloodlust extends CmdVampireModeAbstract
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireModeBloodlust()
	{
		// Aliases
		this.addAliases("b", "bloodlust");
		
		// Requirements
		this.addRequirements(new ReqHasPerm(Perm.MODE_BLOODLUST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	protected void set(boolean val)
	{
		vme.setBloodlusting(val);
	}
	
	@Override
	protected boolean get()
	{
		return vme.isBloodlusting();
	}
	
}
