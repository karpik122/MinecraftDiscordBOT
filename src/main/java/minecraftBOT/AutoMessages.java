package main.java.minecraftBOT;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.util.TimerTask;

public class AutoMessages extends TimerTask implements Listener {
    @Override
    public void run() {
        int min = 0;
        int max = 7;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

        String message = "Błąd!";

        switch(random_int) {
            case 0:
                message = "\n"+ChatColor.GREEN+"Dołącz na naszego " +ChatColor.DARK_GREEN+"discorda"+ChatColor.GREEN+", aby otrzymywać informacje wcześniej: "+ChatColor.RED+"https://discord.gg/utopiaservers\n\n";
                break;
            case 1:
                message = "\n"+ChatColor.GREEN+"Zakup rangi premium:"+ChatColor.YELLOW+" VIP,"+ChatColor.GOLD+" VIP+"+ChatColor.GREEN+" aby otrzymywać dodatkowe bonusy!\n\n";
                break;
            case 2:
                message = "\n"+ChatColor.GREEN+"Utopia to sieć serwerów w różnych grach. Chcesz zagrać w "+ChatColor.DARK_GREEN+"SCP:SL, Unturned, Garry's Moda? "+ChatColor.GREEN+"Nie zwlekaj! "+ChatColor.RED+"https://discord.gg/utopiaservers\n\n";
                break;
            case 3:
                message = "\n"+ChatColor.GREEN+"Wesprzyj serwer poprzez zakup"+ChatColor.GOLD+" rang"+ChatColor.GREEN+", abyśmy mogli się cały czas rozwijać!\n\n";
                break;
            case 4:
                message = "\n"+ChatColor.GREEN+"Grając na serwerze pamiętaj o przestrzeganiu "+ChatColor.DARK_AQUA+"regulaminu"+ChatColor.GREEN+", znajdziesz go tutaj: "+ChatColor.RED+"https://discord.gg/utopiaservers\n\n";
                break;
            case 5:
                message = "\n"+ChatColor.GREEN+"Jeżeli potrzebujesz pomocy, chcesz uzyskać jakiekolwiek informacje, pisz do administracji znajdującej się na serwerze.\n\n";
                break;
            case 6:
                message = "\n"+ChatColor.GREEN+"Gracz łamie regulamin? Dyskretnie zgłoś go do administracji używając "+ChatColor.RED+"/report\n\n";
                break;
            case 7:
                message = "\n"+ChatColor.GREEN+"Sprawdź swój czas gry na serwerze używając "+ChatColor.RED+"!playtime <nick>"+ChatColor.GREEN+" na naszym "+ChatColor.AQUA+"discordzie!\n\n";
                break;

        }

        String finalMessage = message;
        Bukkit.getOnlinePlayers().forEach(al -> al.sendMessage(finalMessage));
        System.out.println(finalMessage);
    }
}

