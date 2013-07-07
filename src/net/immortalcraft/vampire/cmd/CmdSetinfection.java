package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPlayer;
import net.immortalcraft.vampire.Lang;
import net.immortalcraft.vampire.InfectionReason;
import net.immortalcraft.vampire.VPerm;
import org.bukkit.entity.Player;

import com.massivecraft.mcore.cmd.arg.ARDouble;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.MUtil;


/**
 *
 * @author Will
 */
public class CmdSetinfection extends CmdSetAbstract<Double>
{
	public CmdSetinfection()
	{
		targetMustBeOnline = false;
		argReader = ARDouble.get();
		this.addAliases("i");
		this.addRequirements(ReqHasPerm.get(VPerm.SET_INFECTION.node));
	}

	@Override
	public Double set(VPlayer vplayer, Player player, Double val)
	{
		Double res = MUtil.limitNumber(val, 0D, 100D);
		if (vplayer.isVampire())
		{
			msg(Lang.xIsAlreadyVamp, vplayer.getDisplayName());
			return null;
		}
		
		vplayer.setReason(InfectionReason.OPERATOR);
		vplayer.setMaker(null);
		vplayer.setInfection(res);
		vplayer.addInfection(0, InfectionReason.OPERATOR, null);
		return res;
	}
}
