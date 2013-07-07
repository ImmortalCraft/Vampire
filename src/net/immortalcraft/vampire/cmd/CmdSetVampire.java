package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPlayer;
import net.immortalcraft.vampire.InfectionReason;
import net.immortalcraft.vampire.VPerm;
import org.bukkit.entity.Player;

import com.massivecraft.mcore.cmd.arg.ARBoolean;

public class CmdSetVampire extends CmdSetAbstract<Boolean>
{
	public CmdSetVampire()
	{
		targetMustBeOnline = false;
		argReader = ARBoolean.get();
		this.addAliases("v");
		this.setDesc("set vampire (yes or no)");
	}

	@Override
	public Boolean set(VPlayer vplayer, Player player, Boolean val)
	{
		VPerm perm = val ? VPerm.SET_VAMPIRE_TRUE : VPerm.SET_VAMPIRE_FALSE;
		
		if ( ! perm.has(sender, true)) return null;
		
		if (vplayer.isVampire() == val) return val;
		
		vplayer.setReason(InfectionReason.OPERATOR);
		vplayer.setMaker(null);
		vplayer.setVampire(val);
		
		return val;
	}
}
