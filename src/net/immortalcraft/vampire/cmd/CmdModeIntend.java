package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPerm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdModeIntend extends CmdModeAbstract
{	
	public CmdModeIntend()
	{
		this.addAliases("i", "intend");
		this.addRequirements(new ReqHasPerm(VPerm.MODE_INTENT.node));
	}
	
	protected void set(boolean val)
	{
		vme.setIntending(val);
	}
	
	protected boolean get()
	{
		return vme.isIntending();
	}
}
