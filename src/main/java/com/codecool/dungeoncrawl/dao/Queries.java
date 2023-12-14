package com.codecool.dungeoncrawl.dao;

public class Queries {

    public static final String ADD_GAME_MAP = "INSERT INTO game_map (current_map, saved_at, player_id) " +
            "VALUES(?, NOW(), ?)";
    public static final String ADD_GAME_STATE = "INSERT INTO game_state (current_map, saved_at, player_id) " +
                                                "VALUES(?, NOW(), ?)";
    public static final String GET_GAME_STATE = "SELECT * FROM game_state WHERE id = ?";

    public static final String GET_GAME_MAP = "SELECT * FROM game_map WHERE id = ?";


    public static final String GET_ALL_GAME_STATES = "SELECT * FROM game_state";

    public static final String UPDATE_GAME_STATE = "UPDATE game_state SET current_map = ?, saved_at = ?, player_id = ? WHERE id = ?";

    public static final String ADD_PLAYER = "INSERT INTO player (player_name, hp, x, y) VALUES (?, ?, ?, ?)";

    public static final String UPDATE_PLAYER = "UPDATE player SET player_name = ?, hp = ?, x = ?, y = ? WHERE id = ?";

    public static final String GET_PLAYER = "SELECT * FROM player WHERE id = ?";

    public static final String GET_ALL_PLAYERS = "SELECT * FROM player";
}
