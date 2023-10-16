package vendek.garbagecollectors;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vendek.garbagecollectors.events.CollectGarbage;

public final class GarbageCollectors extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CollectGarbage(), this);
    }

    @Override
    public void onDisable() {
    }
}
