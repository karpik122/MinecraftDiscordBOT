package main.java.minecraftBOT.Timer;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DiscordAllTime extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!alltime")) {
            if(event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                File f = new File("plugins/MinecraftDiscortBOT/playtime.yml");
                YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

                event.getMessage().reply("```"+file.saveToString()+"```").queue();

            }
        }
    }
}
