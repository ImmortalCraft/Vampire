package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import net.immortalcraft.vampire.VPerm;

public class CmdAccept extends VCommand
{
	public CmdAccept()
	{
		this.addAliases("a", "accept");
		
		this.addRequirements(ReqHasPerm.get(VPerm.TRADE_ACCEPT.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		vme.tradeAccept();
	}
}
