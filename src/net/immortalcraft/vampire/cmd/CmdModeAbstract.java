package net.immortalcraft.vampire.cmd;

import com.massivecraft.mcore.cmd.arg.ARBoolean;
import net.immortalcraft.vampire.cmdreq.ReqIsVampire;

public abstract class CmdModeAbstract extends VCommand
{	
	public CmdModeAbstract()
	{
		this.addOptionalArg("bool", "flip");
		
		this.addRequirements(ReqIsVampire.get());
	}
	
	@Override
	public void perform()
	{
		Boolean val = this.arg(0, ARBoolean.get(), ! this.get());
		if (val == null) return;
			
		this.set(val);
	}
	
	protected abstract void set(boolean val);
	protected abstract boolean get();
}
