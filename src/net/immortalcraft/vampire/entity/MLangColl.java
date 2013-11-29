package net.immortalcraft.vampire.entity;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;
import net.immortalcraft.vampire.Const;
import net.immortalcraft.vampire.Vampire;

public class MLangColl extends Coll<MLang>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MLangColl i = new MLangColl();
	public static MLangColl get() { return i; }
	private MLangColl()
	{
		super(Const.COLLECTION_MLANG, MLang.class, MStore.getDb(), Vampire.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void init()
	{
		super.init();
		MLang.i = this.get(MCore.INSTANCE, true);
	}
	
}
