package me.mrjuliusz.commands;

import me.mrjuliusz.jsresurcepack.jsresourcepack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {

    private static final jsresourcepack plugin = (jsresourcepack) Bukkit.getPluginManager().getPlugin("JsResourcePack");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = "&4&lJs&f&lResourcePack &e→ &f";
        //String url = "https://www.dropbox.com/s/ol3og1cq8xbytin/Conquest_1.12.zip?dl=1";
        if (label.equalsIgnoreCase("txt")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Wpisz &2/" + label + " &2pomoc &fpo wiecej komend."));
                return true;
            }

            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("Pomoc")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&m&l-------------[ &4&lJs&f&lResourcePack &6&m&l]-------------" +
                        "\n&2" +
                        "\n&2/" + cmd.getName() + " pomoc &e- &fWyswietlenie pomocy" +
                        "\n&2/" + cmd.getName() + " pobierz &e- &fPobranie reczne resource pack'a" +
                        "\n&2/" + cmd.getName() + " edit [url] &e- &fAktualizacja resource pack &4*Tylko admin" +
                        "\n&2/" + cmd.getName() + " check &e- &fSprawdzenie linku do resource pack &4*Tylko admin" +
                        "\n&2/" + cmd.getName() + " toggle &e- &fAutomatyczne pytanie gracza o pobranie resource pack przy wejsciu" +
                        "\n&2/" + cmd.getName() + " reload &e- &fPrzeladowanie configu" +
                        "\n&4&lJs&f&lResourcePack &6zostal zaprogramowany przez &4Mr&fJuliusz" +
                        "\n&6&m&l-------------[ &4&lJs&f&lResourcePack &6&m&l]-------------"));
                return true;
            }


            if (args[0].equalsIgnoreCase("pobierz")) {
                if(!(sender instanceof Player))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Tej komendy nie moze uzywac konsola."));
                    return true;
                }

                if(plugin.getConfig().contains("url"))
                {
                    String url = "";
                    if(!(plugin.getConfig().contains("url")))
                    {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Serwer nie ma ustawionego resource pack'a."));
                        return true;
                    }
                    if(plugin.getConfig().contains("url"))
                    {
                        url = plugin.getConfig().getString("url");
                        if (!(url.contains("https")))
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Aktualnie zapisany link, nie jest poprawny"));
                            return true;
                        }
                        else
                        {
                            url = plugin.getConfig().getString("url");
                        }

                    }

                    try {

                        player.setResourcePack(url);
                    } catch (Exception e) {
                        //pasing
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Zainstalowano resource pack'a."));
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("check")) {
                String url = "Brak url w configu.";
                if (!(sender.hasPermission("js.check.admin"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&LBLAD! &fBrak dostepu do tej komendy."));
                    return true;
                }

                if(plugin.getConfig().contains("url"))
                {
                    url = plugin.getConfig().getString("url");
                    if (!(url.contains("https")))
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Aktualnie zapisany link, nie jest poprawny"));
                        return true;
                    }
                }


                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Aktualny link: " + url));
                return true;

            }


            if (args[0].equalsIgnoreCase("edit")) {
                if (!(player.hasPermission("js.check.admin"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&LBLAD! &fBrak dostepu do tej komendy."));
                    return true;
                }
                if (!(args.length == 2)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&4&lBLAD! &fMusisz podac link do resource pack'a"));
                    return true;
                }

                if (!(args[1].contains("https://"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Link musi zawirac &2https://"));
                    return true;
                }

                plugin.getConfig().set("url",args[1].toString());
                plugin.saveConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "Poprawnie ustawiono link do resource pack na &2" + args[1]));
                return true;

            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender.hasPermission("js.check.admin"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&LBLAD! &fBrak dostepu do tej komendy."));
                    return true;

                }
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&fPrzeladowano config."));
                return true;
            }
            if(args[0].equalsIgnoreCase("toggle"))
            {
                if(!(sender.hasPermission("js.check.admin")))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&LBLAD! &fBrak dostepu do tej komendy."));
                    return true;
                }
                if(args.length > 1)
                {
                    if (args[1].equalsIgnoreCase("on"))
                    {
                        plugin.getConfig().set("join",true);
                        plugin.saveConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Zapytania o instalacje resource pack'a przy wejsciu jest &a" + args[1].toString()));
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("off"))
                    {
                        plugin.getConfig().set("join",true);
                        plugin.saveConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "Zapytania o instalacje resource pack'a przy wejsciu jest &c" + args[1].toString()));
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&aON&7/&cOFF"));
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&aON&7/&cOFF"));
                    return true;
                }
            }


        }
        return false;
    }
}