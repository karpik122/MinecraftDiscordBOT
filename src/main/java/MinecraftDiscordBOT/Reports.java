package main.java.MinecraftDiscordBOT;

import java.time.Instant;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.ServerOperator;
import org.jetbrains.annotations.NotNull;

public class Reports implements Listener, CommandExecutor {
    JDA jda = MainClass.getJda();

    public Reports() {
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String Reports_channel = getConfig().getString("Reports_chanel")
        TextChannel reportChannel = this.jda.getTextChannelById(Reports_channel);
        if (sender instanceof Player) {
            Player p = (Player)sender;
            Player reported = null;
            if (args.length > 1) {
                reported = Bukkit.getPlayer(args[0]);
                String content = "";

                for(int i = 1; i < args.length; ++i) {
                    content = content + " " + args[i];
                }

                try {
                    p.sendMessage(ChatColor.GREEN + "Pomyślnie zgłoszono gracza " + ChatColor.RED + reported.getName() + ChatColor.GREEN + " za " + ChatColor.RED + content);
                } catch (Exception var12) {
                    p.sendMessage(ChatColor.RED + "Takiego gracza nie ma na serwerze!");
                }

                Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach((ops) -> {
                    ops.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.0F, 0.0F);
                });
                Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach((ops) -> {
                    ops.sendMessage(ChatColor.GREEN + "Gracz " + ChatColor.RED + reported.getName() + ChatColor.GREEN + " został zgłoszony za" + ChatColor.RED + content);
                });
                EmbedBuilder emb = new EmbedBuilder();
                emb.setAuthor("Zgłoszenie gracza", (String)null, "https://minotar.net/helm/" + p.getName() + "/300.png");
                emb.setThumbnail("https://minotar.net/helm/" + reported.getName() + "/300.png");
                emb.addField("Zgłaszający", p.getName(), true);
                emb.addField("Zgłoszony", reported.getName(), true);
                emb.addField("Treść", content, false);
                emb.setColor(16711680);
                emb.setFooter("Zgłoszenie z");
                emb.setTimestamp(Instant.now());
                reportChannel.sendMessage(emb.build()).queue();
            } else {
                p.sendMessage(ChatColor.RED + "Niepoprawna składnia!\n" + ChatColor.GREEN + "Poprawne użycie: /report <gracz> <treść>");
            }
        }

        return false;
    }
}
