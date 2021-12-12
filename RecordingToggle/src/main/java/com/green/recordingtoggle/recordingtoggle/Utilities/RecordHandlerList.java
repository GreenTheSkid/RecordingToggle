package com.green.recordingtoggle.recordingtoggle.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.stream.Collectors;


public class RecordHandlerList {

    private final Team team = init();
    private final static RecordHandlerList Instance = new RecordHandlerList();

    public RecordHandlerList() {
        init();
    }

    Team init() {
        Scoreboard mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team recorders = mainScoreboard.getTeam("recorders");
        if (recorders == null) recorders = mainScoreboard.registerNewTeam("recorders");

        recorders.setSuffix(Utils.color(" &4&lRECORDING"));
        recorders.setOption(Team.Option.NAME_TAG_VISIBILITY,Team.OptionStatus.ALWAYS);
        return recorders;
    }

    public void addPlayer(OfflinePlayer player) {
        if (player != null) team.addEntry(player.getName());
    }

    public void removePlayer(OfflinePlayer player) {
        if (player != null) team.removeEntry(player.getName());
    }

    public static RecordHandlerList getInstance() {
        return Instance;
    }

    public List<OfflinePlayer> getRecorders() {
        return team.getEntries().stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList());
    }

    public boolean isPlayerRecording(OfflinePlayer player) {
        return player != null && team.hasEntry(player.getName());
    }

    public boolean toggle(OfflinePlayer player) {
        if (player == null) return false;
        if (team.hasEntry(player.getName())) removePlayer(player);
  else addPlayer(player);
        return team.hasEntry(player.getName());
    }

    public Team getTeam() {
        return team;
    }

}
