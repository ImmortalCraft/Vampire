package net.immortalcraft.vampire.cmd;

import net.immortalcraft.vampire.VPlayer;
import net.immortalcraft.vampire.VPerm;
import org.bukkit.entity.Player;

import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.MUtil;

/**
 *
 * @author Will
 */
public class CmdSetfood extends CmdSetAbstract<Integer>
{
	public CmdSetfood()
	{
		targetMustBeOnline = true;
		argReader = ARInteger.get();
		
		this.addAliases("f");
		this.addRequirements(ReqHasPerm.get(VPerm.SET_FOOD.node));
	}

	@Override
	public Integer set(VPlayer vplayer, Player player, Integer val)
	{
		Integer res = MUtil.limitNumber(val, 0, 20);
		player.setFoodLevel(res);
		return res;
	}
}
