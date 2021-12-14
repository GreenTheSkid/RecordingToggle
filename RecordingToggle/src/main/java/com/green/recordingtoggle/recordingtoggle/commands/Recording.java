package com.green.recordingtoggle.recordingtoggle.commands;


import com.earth2me.essentials.User;
import com.earth2me.essentials.messaging.IMessageRecipient;
import com.earth2me.essentials.messaging.SimpleMessageRecipient;
import com.green.recordingtoggle.recordingtoggle.Utilities.RecordHandlerList;
import com.green.recordingtoggle.recordingtoggle.Utilities.Utils;
import net.ess3.api.events.PrivateMessageSentEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;

public class Recording implements CommandExecutor, Listener{

    JavaPlugin plugin = JavaPlugin.getProvidingPlugin(Recording.class);
    FileConfiguration config = plugin.getConfig();
    String Recordingmessage = config.getString("Recordingmessage");
    final RecordHandlerList recordHandlerList = RecordHandlerList.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        if (!(sender instanceof Player player)) {
            sender.sendMessage("§4§l(!) §4Only players can do this!");
            return true;
        }


        if (cmd.getName().equalsIgnoreCase("Recording")) {
            if (!sender.hasPermission("RecordingToggle.Recording")) {
                player.sendMessage(Utils.color("&cYou dont have permission to use this command!"));
                return true;

            }
            sender.sendMessage(Utils.color("&eYour recording status has been "+(recordHandlerList.toggle(player) ? "&a&lENABLED" : "&cDISABLED" )));
            
        }

        return true;


    }

    @EventHandler
    public void onDM(PrivateMessageSentEvent e) {
        Player sender = getPlayer(e.getSender());
        if (sender != null && recordHandlerList.isPlayerRecording(getPlayer(e.getRecipient()))) {
            sender.sendMessage(Utils.color("&c" + e.getRecipient().getName() + " " + Recordingmessage));
        }
    }

    Player getPlayer(IMessageRecipient m) {
        User user = getUser(m);
        return user != null ? user.getBase() : null;
    }

    User getUser(IMessageRecipient m) {
        try {
            Method method = SimpleMessageRecipient.class.getDeclaredMethod("getUser",IMessageRecipient.class);
            method.setAccessible(true);
            User user = (User) method.invoke(null,m);
            method.setAccessible(false);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}

