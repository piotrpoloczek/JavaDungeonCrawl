package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.gameobject.actors.player.Player;
import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import lombok.Getter;
import lombok.Setter;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class GameDatabaseManager {
    @Setter
    private PlayerDao playerDao;
    @Getter
    private GameStateDaoJdbc gameStateDao;
    @Getter
    private GameMapDao gameMapDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
        gameMapDao = new GameMapDaoJdbc(dataSource);
    }

    public void savePlayer(PlayerModel playerModel) {
        playerDao.add(playerModel);
    }

    public void saveGameMap(GameMap gameMap) throws IOException {
        gameMapDao.add(gameMap);
    }

    public GameMap getGameMap(int id) {
        return gameMapDao.get(id);
    }

    public void saveGameState(GameState gameState) {
        gameStateDao.add(gameState);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeoncrawl";
        String user = "postgres";
        String password = "postgres";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
