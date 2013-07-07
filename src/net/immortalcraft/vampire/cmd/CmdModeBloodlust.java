package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPerm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdModeBloodlust extends CmdModeAbstract
{	
	public CmdModeBloodlust()
	{
		this.addAliases("b", "bloodlust");
		this.addRequirements(new ReqHasPerm(VPerm.MODE_BLOODLUST.node));
	}
	
	protected void set(boolean val)
	{
		vme.setBloodlusting(val);
	}
	
	protected boolean get()
	{
		return vme.isBloodlusting();
	}
}
