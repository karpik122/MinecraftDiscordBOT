package main.java.MinecraftDiscordBOT.Logi;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.internal.entities.channel.concrete.TextChannelImpl;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;
import main.java.MinecraftDiscordBOT.MainClass;

import java.time.Instant;

public class CommandLogger implements @NotNull Listener {

    JDA jda = MainClass.
            getJda();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        try {
            if (!(event.getMessage().contains("/login"))) {
                if (!(event.getMessage().contains("/register"))) {
                    if (!(event.getMessage().contains("/changepassword"))) {
                        if (!(event.getMessage().contains("/changepass"))) {
                            if (!(event.getMessage().contains("/l"))) {

                                if (!(event.getMessage().contains("/help"))) {
                                    System.out.println(ChatColor.RED + event.getPlayer().getName() + " użył komendy " + event.getMessage());

                                    EmbedBuilder emb = new EmbedBuilder();
                                    emb.setAuthor(event.getPlayer().getName());
                                    emb.setColor(0xFFA500);
                                    emb.setTitle("Log użycia komendy");
                                    emb.addField("Komenda", event.getMessage(), true);
                                    emb.addField("Administrator", event.getPlayer().getName(), true);
                                    emb.setThumbnail("https://minotar.net/helm/" + event.getPlayer().getName() + "/300.png");
                                    emb.setFooter("Komenda użyta");
                                    emb.setTimestamp(Instant.now());
                                    TextChannel tc = jda.getTextChannelById("903009938987679765");
                                    tc.sendMessage(emb.build()).queue();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(ChatColor.RED + "Wystąpił nieznany błąd. Skontaktuj się z CoronaCreeper#4884");
        }
    }
}
