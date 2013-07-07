package net.immortalcraft.vampire;

import org.bukkit.Bukkit;

import com.massivecraft.mcore.Aspect;
import com.massivecraft.mcore.AspectColl;
import com.massivecraft.mcore.MPlugin;
import net.immortalcraft.vampire.cmd.CmdBase;

public class P extends MPlugin 
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static P p;
	public static P get() { return p; }
	public P() { P.p = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	public CmdBase cmdBase;
	
	// Aspects
	public Aspect playerAspect;
	public Aspect configAspect;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Init aspects
		this.playerAspect = AspectColl.get().get(Const.playerAspectId, true);
		this.playerAspect.register();
		this.playerAspect.setDesc(
			"<i>Everything player related:", 
			"<i>Is the player a vampire or not?",
			"<i>What was the infection reason?",
			"<i>Check <h>"+Const.configAspectId+" <i>for rules and balancing."
		);
		
		this.configAspect = AspectColl.get().get(Const.configAspectId, true);
		this.configAspect.register();
		this.configAspect.setDesc(
			"<i>Config options for balancing:", 
			"<i>What is the splash potion radius for holy water?",
			"<i>What items are considered wooden stakes?",
			"<i>Check <h>"+Const.playerAspectId+" <i>for player state."
		);
		
		// Load Conf from disk
		Lang.i.load();
		ConfServer.i.load();
		ConfColls.i.init();
		VPlayerColls.i.init();
		
		// Initialize collections
		// VPlayerColl.i.init();
		
		// Add Base Commands
		this.cmdBase = new CmdBase();
		this.cmdBase.register(this, true);
		
		// Start timer
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TheTask(), 0, ConfServer.taskInterval);
	
		// Register events
		new TheListener(this);
		
		postEnable();
	}
	
}