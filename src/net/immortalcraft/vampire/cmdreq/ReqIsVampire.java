package net.immortalcraft.vampire.cmdreq;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.req.ReqAbstract;
import net.immortalcraft.vampire.entity.MLang;
import net.immortalcraft.vampire.entity.UPlayer;

public class ReqIsVampire extends ReqAbstract
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqIsVampire i = new ReqIsVampire();
	public static ReqIsVampire get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MCommand command)
	{
		UPlayer uplayer = UPlayer.get(sender);
		if (uplayer == null) return false;
		return uplayer.isVampire();
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MCommand command)
	{
		return String.format(MLang.get().onlyVampsCanX, (command == null ? "do that" : command.getDesc()));
	}
	
}
