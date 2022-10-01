package main.java.minecraftBOT;

import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import main.java.minecraftBOT.Discord.StatusUpdater;
import main.java.minecraftBOT.Discord.User;
import main.java.minecraftBOT.Logi.CommandLogger;
import main.java.minecraftBOT.Timer.Counter;
import main.java.minecraftBOT.Timer.DiscordAllTime;
import main.java.minecraftBOT.Timer.DiscordCheck;
import main.java.minecraftBOT.Alert;
import main.java.minecraftBOT.AlertDisplayer;
import main.java.minecraftBOT.AutoMessages;
import main.java.minecraftBOT.Reports;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Timer;

public final class MainClass extends JavaPlugin implements Listener {

    @Getter
    public static JDA jda;

    @SneakyThrows
    @Override
    public void onEnable() {
        System.out.println(ChatColor.RED+"minecraftBOT Discord Hook - Ładowanie");

        runBot();

        System.out.println(ChatColor.GREEN+"minecraftBOT Discord Hook - Zalogowano jako " + jda.getSelfUser().getName() + "#" + jda.getSelfUser().getDiscriminator() + " (" + jda.getSelfUser().getId() + ")");

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new CommandLogger(), this);
        this.getServer().getPluginManager().registerEvents(new main.java.minecraftBOT.AutoMessages(), this);
        this.getCommand("report").setExecutor(new Reports());
        this.getCommand("alert").setExecutor(new Alert());

        Timer timer = new Timer();
        timer.schedule(new StatusUpdater(), 0, 30000);
        timer.schedule(new AutoMessages(), 0, 600000);
        timer.schedule(new Counter(), 0, 60000);
        timer.schedule(new AlertDisplayer(), 0, 1000);

        File f = new File("plugins/MinecraftDiscortBOT/alerts.yml");
        YamlConfiguration file = YamlConfiguration.loadConfiguration(f);
        file.set("activealert", false);
        file.save(f);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void runBot() {
        try {
            jda = JDABuilder.createDefault("OTAzMDA5NzIxNjc2NjExNjI1.YXmvVg.Js378vA9Je9TGKmw4tyrNYs7M04",
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.GUILD_INVITES,
                            GatewayIntent.GUILD_BANS,
                            GatewayIntent.GUILD_INVITES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_WEBHOOKS,
                            GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                            GatewayIntent.DIRECT_MESSAGE_TYPING,
                            GatewayIntent.DIRECT_MESSAGES
                    )

                    .addEventListeners(
                            new User(),
                            new DiscordCheck(),
                            new DiscordAllTime()
                    )
                    .build();
        } catch (LoginException e) {
            System.out.println(ChatColor.RED+"minecraftBOT Discord Hook - BŁĄD");
            e.printStackTrace();
        }

    }
}
