package main.java.minecraftBOT.Discord;

import main.java.minecraftBOT.MainClass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import java.util.TimerTask;

public class StatusUpdater extends TimerTask {

    JDA jda = MainClass.getJda();

    @Override
    public void run() {
        int pn = Bukkit.getServer().getOnlinePlayers().size();
        int ps = Bukkit.getServer().getMaxPlayers();
        if(pn==0) {
            jda.getPresence().setStatus(OnlineStatus.IDLE);
        }else {
            if (pn == ps) {
                jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            } else {
                jda.getPresence().setStatus(OnlineStatus.ONLINE);
            }
        }

        jda.getPresence().setActivity(Activity.watching(pn+" graczy na serwerze Minecraft!"));
    }
}
