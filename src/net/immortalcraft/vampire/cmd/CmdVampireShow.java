package net.immortalcraft.vampire.cmd;

import org.bukkit.entity.Player;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.Multiverse;
import com.massivecraft.mcore.cmd.arg.ARSenderEntity;
import com.massivecraft.mcore.cmd.arg.ArgReader;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;
import net.immortalcraft.vampire.Vampire;
import net.immortalcraft.vampire.Perm;
import net.immortalcraft.vampire.entity.MLang;
import net.immortalcraft.vampire.entity.UConf;
import net.immortalcraft.vampire.entity.UPlayer;
import net.immortalcraft.vampire.entity.UPlayerColl;
import net.immortalcraft.vampire.entity.UPlayerColls;
import net.immortalcraft.vampire.util.SunUtil;

public class CmdVampireShow extends VCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdVampireShow()
	{
		// Aliases
		this.addAliases("s", "show");
		
		// Args
		this.addOptionalArg("player", "you");
		this.addOptionalArg("univ", "you");
		
		// Requirements
		this.addRequirements(new ReqHasPerm(Perm.SHOW.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		if ( vme == null && ! this.argIsSet(0))
		{
			msg(MLang.get().consolePlayerArgRequired);
			return;
		}
		
		Multiverse mv = Vampire.get().playerAspect.getMultiverse();
		String universe = this.arg(1, mv.argReaderUniverse(), senderIsConsole ? MCore.DEFAULT : mv.getUniverse(me));
		if (universe == null) return;
		
		UPlayerColl playerColl = UPlayerColls.get().getForUniverse(universe);
		ArgReader<UPlayer> playerReader = ARSenderEntity.getStartAny(playerColl);
		UPlayer uplayer = this.arg(0, playerReader, vme);
		if (uplayer == null) return;
		
		Player player = uplayer.getPlayer();
		UConf uconf = UConf.get(player);
		
		boolean self = uplayer == vme;
		
		// Test permissions
		if (!self && !Perm.SHOW_OTHER.has(sender, true)) return;
		
		String You = "You";
		//String you = "you";
		String are = "are";
		if ( ! self)
		{
			You = uplayer.getDisplayName();
			//you = uplayer.getId();
			are = "is";
		}
		
		msg(Txt.titleize(Txt.upperCaseFirst(universe)+" Vampire "+uplayer.getDisplayName()));
		if (uplayer.isVampire())
		{
			msg("<i>"+You+" <i>"+are+" a vampire.");
			msg(uplayer.getReasonDesc(self));
		}
		else if (uplayer.isInfected())
		{
			msg("<i>"+You+" <i>"+are+" infected at <h>%d%%<i>.", percent(uplayer.getInfection()));
			msg(uplayer.getReasonDesc(self));
			return;
		}
		else
		{
			msg("<i>"+You+" <i>"+are+" neither vampire nor infected with the dark disease.");
			return;
		}
		
		//msg(uplayer.bloodlust() ? MLang.get().xIsOn : MLang.get().xIsOff, "Bloodlust");
		//msg(uplayer.intend() ? MLang.get().xIsOn : MLang.get().xIsOff, "Infect intent");
		
		this.msg(uplayer.bloodlustMsg());
		this.msg(uplayer.intendMsg());
		this.msg(uplayer.usingNightVisionMsg());
		
		msg("<k>Temperature <v>%d%%", (int)Math.round(uplayer.getTemp()*100));
		if (player == null)
		{
			msg("<k>Irradiation <v>%d%%", percent(uplayer.getRad()));
		}
		else
		{
			int rad = percent(uplayer.getRad());
			int sun = percent(SunUtil.calcSolarRad(player.getWorld()));
			double terrain = 1d-SunUtil.calcTerrainOpacity(player.getLocation().getBlock());
			double armor = 1d-SunUtil.calcArmorOpacity(player);
			int base = percent(uconf.baseRad);
			msg("<k>Irradiation <v>X% <k>= <yellow>sun <lime>*terrain <blue>*armor <silver>-base");
			msg("<k>Irradiation <v>%+d%% <k>= <yellow>%d <lime>*%.2f <blue>*%.2f <silver>%+d", rad, sun, terrain, armor, base);
			
		}
		
	}
	
	public static int percent(double quota)
	{
		return (int)Math.round(quota*100);
	}
	
}
