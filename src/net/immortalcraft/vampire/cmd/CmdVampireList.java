package net.immortalcraft.vampire.cmd;

import java.util.*;

import org.bukkit.ChatColor;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.Multiverse;
import com.massivecraft.mcore.cmd.VisibilityMode;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;
import net.immortalcraft.vampire.*;
import net.immortalcraft.vampire.entity.UPlayer;
import net.immortalcraft.vampire.entity.UPlayerColls;

public class CmdVampireList extends VCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireList()
	{
		// Aliases
		this.addAliases("l", "list");
		
		// Args
		this.addOptionalArg("page", "1");
		this.addOptionalArg("universe", "you");
		
		this.setVisibilityMode(VisibilityMode.SECRET);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		Multiverse mv = Vampire.get().playerAspect.getMultiverse();
		String universe = this.arg(1, mv.argReaderUniverse(), senderIsConsole ? MCore.DEFAULT : mv.getUniverse(me));
		if (universe == null) return;
		
		List<String> vampiresOnline = new ArrayList<String>();
		List<String> vampiresOffline = new ArrayList<String>();
		List<String> infectedOnline = new ArrayList<String>();
		List<String> infectedOffline = new ArrayList<String>();
		
		for (UPlayer uplayer : UPlayerColls.get().getForUniverse(universe).getAll())
		{
			if (uplayer.isVampire())
			{
				if (uplayer.isOnline())
				{
					vampiresOnline.add(ChatColor.WHITE.toString() + uplayer.getDisplayName());
				}
				else
				{
					vampiresOffline.add(ChatColor.WHITE.toString() + uplayer.getDisplayName());
				}
			}
			else if (uplayer.isInfected())
			{
				if (uplayer.isOnline())
				{
					infectedOnline.add(ChatColor.WHITE.toString() + uplayer.getDisplayName());
				}
				else
				{
					infectedOffline.add(ChatColor.WHITE.toString() + uplayer.getDisplayName());
				}
			}
		}

		// Create Messages
		List<String> lines = new ArrayList<String>();
		
		if (vampiresOnline.size() > 0)
		{
			lines.add("<h>=== Vampires Online ===");
			lines.add(Txt.implodeCommaAndDot(vampiresOnline, "<i>"));
		}
		
		if (vampiresOffline.size() > 0)
		{
			lines.add("<h>=== Vampires Offline ===");
			lines.add(Txt.implodeCommaAndDot(vampiresOffline, "<i>"));
		}
		
		if (infectedOnline.size() > 0)
		{
			lines.add("<h>=== Infected Online ===");
			lines.add(Txt.implodeCommaAndDot(infectedOnline, "<i>"));
		}
		
		if (infectedOffline.size() > 0)
		{
			lines.add("<h>=== Infected Offline ===");
			lines.add(Txt.implodeCommaAndDot(infectedOffline, "<i>"));
		}
		
		// Send them
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, Txt.upperCaseFirst(universe)+" Vampire Players", sender));	
	}
	
}
