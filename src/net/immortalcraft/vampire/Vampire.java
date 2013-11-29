package net.immortalcraft.vampire;

import com.massivecraft.mcore.Aspect;
import com.massivecraft.mcore.AspectColl;
import com.massivecraft.mcore.MPlugin;
import net.immortalcraft.vampire.cmd.CmdVampire;
import net.immortalcraft.vampire.entity.MConfColl;
import net.immortalcraft.vampire.entity.MLangColl;
import net.immortalcraft.vampire.entity.UConfColls;
import net.immortalcraft.vampire.entity.UPlayerColls;

public class Vampire extends MPlugin 
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static Vampire i;
	public static Vampire get() { return i; }
	public Vampire() { Vampire.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Aspects
	public Aspect playerAspect;
	public Aspect configAspect;
	
	// Commands
	public CmdVampire cmdBase;

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Aspects
		this.playerAspect = AspectColl.get().get(Const.ASPECT_PLAYER, true);
		this.playerAspect.register();
		this.playerAspect.setDesc(
			"<i>Everything player related:", 
			"<i>Is the player a vampire or not?",
			"<i>What was the infection reason?",
			"<i>Check <h>"+Const.ASPECT_CONF+" <i>for rules and balancing."
		);
		
		this.configAspect = AspectColl.get().get(Const.ASPECT_CONF, true);
		this.configAspect.register();
		this.configAspect.setDesc(
			"<i>Config options for balancing:", 
			"<i>What is the splash potion radius for holy water?",
			"<i>What items are considered wooden stakes?",
			"<i>Check <h>"+Const.ASPECT_PLAYER+" <i>for player state."
		);
		
		// Database
		MConfColl.get().init();
		MLangColl.get().init();
		UConfColls.get().init();
		UPlayerColls.get().init();
		
		// Commands
		this.cmdBase = new CmdVampire();
		this.cmdBase.register();
		
		// Tasks
		TheTask.get().schedule(this);
	
		// Listeners
		ListenerMain.get().activate();
		
		postEnable();
	}
	
}