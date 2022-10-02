package main.java.MinecraftDiscordBOT.Timer;

import java.io.File;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

public class DiscordAllTime extends ListenerAdapter {
    public DiscordAllTime() {
    }

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!alltime") && event.getMember().hasPermission(new Permission[]{Permission.ADMINISTRATOR})) {
            File f = new File("plugins/UtopiaMC/playtime.yml");
            YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
            event.getMessage().reply("```" + file.saveToString() + "```").queue();
        }

    }
}
