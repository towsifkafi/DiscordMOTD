package cf.towsifkafi.discordmotd;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordMOTD extends JavaPlugin implements Listener {
    private DiscordSRVListener discordsrvListener = new DiscordSRVListener(this);
    private static DiscordMOTD instance;

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

        new Expansion().register();
        setInstance(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DiscordSRV.api.unsubscribe(discordsrvListener);
    }

    public static DiscordMOTD getInstance() {
        return instance;
    };

    public static void setInstance(DiscordMOTD instance) {
        DiscordMOTD.instance = instance;
    };
}
