package cf.towsifkafi.discordmotd;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.*;
import github.scarsz.discordsrv.dependencies.jda.api.JDA;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.dependencies.jda.api.entities.User;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;

public class DiscordSRVListener {

    public DiscordMOTD plugin;

    public DiscordSRVListener(DiscordMOTD pl) {
        this.plugin = pl;
    }

    @Subscribe
    public void discordReadyEvent(DiscordReadyEvent event) {

    }

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void discordMessageReceived(DiscordGuildMessageReceivedEvent event) {

    }

}

