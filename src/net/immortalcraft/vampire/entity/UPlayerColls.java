package net.immortalcraft.vampire.entity;

import com.massivecraft.mcore.Aspect;
import com.massivecraft.mcore.store.Colls;
import net.immortalcraft.vampire.Const;
import net.immortalcraft.vampire.Vampire;

public class UPlayerColls extends Colls<UPlayerColl, UPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static UPlayerColls i = new UPlayerColls();
	public static UPlayerColls get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE: COLLS
	// -------------------------------------------- //
	
	@Override
	public UPlayerColl createColl(String collName)
	{
		return new UPlayerColl(collName);
	}

	@Override
	public Aspect getAspect()
	{
		return Vampire.get().playerAspect;
	}

	@Override
	public String getBasename()
	{
		return Const.COLLECTION_UPLAYER;
	}
	
}
