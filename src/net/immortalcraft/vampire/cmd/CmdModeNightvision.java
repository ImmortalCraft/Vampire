package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPerm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdModeNightvision extends CmdModeAbstract
{	
	public CmdModeNightvision()
	{
		this.addAliases("n", "nightvision");
		this.addRequirements(new ReqHasPerm(VPerm.MODE_NIGHTVISION.node));
	}
	
	protected void set(boolean val)
	{
		vme.setUsingNightVision(val);
	}
	
	protected boolean get()
	{
		return vme.isUsingNightVision();
	}
}
