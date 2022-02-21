package cf.towsifkafi.discordmotd;

import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

public class Expansion extends PlaceholderExpansion {
    public String motd;

    @Override
    public @NotNull String getIdentifier() {
        return "discordmotd";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TowsifKafi";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @NotNull boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {

        if (params.equalsIgnoreCase("motd")) {
            this.motd = DiscordMOTD.getMotd();
            return this.motd;
        }

        return this.motd;
    }
}
