package net.immortalcraft.vampire.entity;

import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;
import net.immortalcraft.vampire.Vampire;

public class UConfColl extends Coll<UConf>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public UConfColl(String name)
	{
		super(name, UConf.class, MStore.getDb(), Vampire.get(), false, true, false);
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
}
