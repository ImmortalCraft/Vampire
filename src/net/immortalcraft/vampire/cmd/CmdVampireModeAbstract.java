package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.arg.ARBoolean;
import net.immortalcraft.vampire.cmdreq.ReqIsVampire;

public abstract class CmdVampireModeAbstract extends VCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireModeAbstract()
	{
		// Args
		this.addOptionalArg("bool", "flip");
		
		// Requirements
		this.addRequirements(ReqIsVampire.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		Boolean val = this.arg(0, ARBoolean.get(), ! this.get());
		if (val == null) return;
			
		this.set(val);
	}
	
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	protected abstract void set(boolean val);
	protected abstract boolean get();
	
}
