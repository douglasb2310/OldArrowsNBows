package io.github.douglasb2310.commands.sub;

import io.github.douglasb2310.OldArrowsNBows;
import io.github.douglasb2310.utils.CommandInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandInterface {
    private final OldArrowsNBows oldArrowsNBows;
    public HelpCommand(OldArrowsNBows oldArrowsNBows) {
        this.oldArrowsNBows = oldArrowsNBows;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        final boolean PERMISSIONS_ENABLED = oldArrowsNBows.getConfig().getBoolean("general.permissions_enabled");
        final boolean HELP_ALLOWED = oldArrowsNBows.getConfig().getBoolean("no_permissions_plugin.help_allowed");

        final String MSG_HELP_MASTER = oldArrowsNBows.getConfig().getString("messages.help_message_master");
        final String MSG_HELP_GENERIC = oldArrowsNBows.getConfig().getString("messages.help_message");
        final String MSG_HELP_CONSOLE = oldArrowsNBows.getConfig().getString("messages.console_toggle_help");
        final String MSG_NO_PERMISSION = oldArrowsNBows.getConfig().getString("messages.no_permission");

        if((!PERMISSIONS_ENABLED & HELP_ALLOWED)
        |!(sender instanceof Player)
        |sender.hasPermission("oldarrowsnbows.seehelp")) {
            if(sender instanceof Player && sender.hasPermission("oldarrowsnbows.*")){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MSG_HELP_MASTER));
                return true;
            }
            if (sender instanceof Player && !(sender.hasPermission("oldarrowsnbows.*"))){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MSG_HELP_GENERIC));
                return true;
            }
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MSG_HELP_CONSOLE));
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MSG_NO_PERMISSION));
            return false;
        }
        return false;
    }
}
