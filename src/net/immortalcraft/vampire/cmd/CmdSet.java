package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.VisibilityMode;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import net.immortalcraft.vampire.VPerm;

public class CmdSet extends VCommand
{
	public CmdSetVampire cmdSetVampire = new CmdSetVampire();
	public CmdSetinfection cmdSetInfection = new CmdSetinfection();
	public CmdSetfood cmdSetFood = new CmdSetfood();
	public CmdSetHealth cmdSetHealth = new CmdSetHealth();
	
	
	public CmdSet()
	{
		super();
		this.addAliases("set");
		
		this.addSubCommand(cmdSetVampire);
		this.addSubCommand(cmdSetInfection);
		this.addSubCommand(cmdSetFood);
		this.addSubCommand(cmdSetHealth);
		
		this.setVisibilityMode(VisibilityMode.SECRET);
		this.addRequirements(ReqHasPerm.get(VPerm.SET.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}
