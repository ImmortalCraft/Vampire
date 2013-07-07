package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPerm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import net.immortalcraft.vampire.cmdreq.ReqIsVampire;

public class CmdShriek extends VCommand
{
	public CmdShriek()
	{
		this.addAliases("shriek");
		
		this.addRequirements(ReqIsVampire.get());
		this.addRequirements(ReqHasPerm.get(VPerm.SHRIEK.node));
	}
	
	@Override
	public void perform()
	{
		vme.shriek();
	}
}
