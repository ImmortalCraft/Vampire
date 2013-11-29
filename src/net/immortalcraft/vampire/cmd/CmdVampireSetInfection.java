package net.immortalcraft.vampire.cmd;

import org.bukkit.entity.Player;

import com.massivecraft.mcore.cmd.arg.ARDouble;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.MUtil;
import net.immortalcraft.vampire.*;
import net.immortalcraft.vampire.entity.MLang;
import net.immortalcraft.vampire.entity.UPlayer;

public class CmdVampireSetInfection extends CmdVampireSetAbstract<Double>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireSetInfection()
	{
		targetMustBeOnline = false;
		argReader = ARDouble.get();
		
		// Aliases
		this.addAliases("i");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.SET_INFECTION.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public Double set(UPlayer uplayer, Player player, Double val)
	{
		Double res = MUtil.limitNumber(val, 0D, 100D);
		if (uplayer.isVampire())
		{
			msg(MLang.get().xIsAlreadyVamp, uplayer.getDisplayName());
			return null;
		}
		
		uplayer.setReason(InfectionReason.OPERATOR);
		uplayer.setMaker(null);
		uplayer.setInfection(res);
		uplayer.addInfection(0, InfectionReason.OPERATOR, null);
		return res;
	}
	
}
