package cf.towsifkafi.discordmotd;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class DiscordMOTD extends JavaPlugin implements Listener {
    private DiscordSRVListener discordsrvListener = new DiscordSRVListener(this);
    private static DiscordMOTD instance;
    public static String motd = "MOTD is updating..";

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Plugin startup logic
        DiscordSRV.api.subscribe(discordsrvListener);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().info("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                TextChannel channel = DiscordUtil.getTextChannelById(DiscordMOTD.getInstance().getConfig().getString("motd-channel"));
                channel.getHistory().retrievePast(1).queue(messages -> {
                    DiscordMOTD.setMotd(messages.get(0).getContentDisplay());
                });
            }
        }, 1200L, 1200L);

        new Expansion().register();
        setInstance(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordSRV.api.unsubscribe(discordsrvListener);
    }

    //get motd
    public static String getMotd() {
        return motd;
    };

    //set motd
    public static void setMotd(String motd) {
        DiscordMOTD.motd = motd;
    }

    public static DiscordMOTD getInstance() {
        return instance;
    };

    public static void setInstance(DiscordMOTD instance) {
        DiscordMOTD.instance = instance;
    };
}
