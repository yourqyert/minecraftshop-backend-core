package me.yourqyert.minecraftshop.webplugin.task;

import me.yourqyert.minecraftshop.webplugin.manager.ApiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONObject;

public class PollTask extends BukkitRunnable {
    private final ApiManager apiManager;
    private final JavaPlugin plugin;

    public PollTask(ApiManager apiManager, JavaPlugin plugin) {
        this.apiManager = apiManager;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        try {
            JSONArray tasks = apiManager.fetchTasks();
            if (tasks.isEmpty()) return;

            Bukkit.getScheduler().runTask(plugin, () -> {
                for (int i = 0; i < tasks.length(); i++) {
                    processTask(tasks.getJSONObject(i));
                }
            });
        } catch (Exception e) {
            plugin.getLogger().warning("Site is unavailable: " + e.getMessage());
        }
    }

    private void processTask(JSONObject json) {
        long id = json.getLong("id");
        String command = json.getString("command");

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        apiManager.confirmTask(id);

        if (plugin.getConfig().getBoolean("notifications.broadcast.enabled")) {
            String msg = plugin.getConfig().getString("notifications.broadcast.message")
                    .replace("{player}", "Player") //nickname from json
                    .replace("&", "§");
            Bukkit.broadcastMessage(msg);
        }
    }
}