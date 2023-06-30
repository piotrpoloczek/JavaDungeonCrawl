package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;


import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.List;

public class GameMapDaoJdbc implements GameMapDao {

    private DataSource dataSource;

    public GameMapDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameMap gameMap) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(gameMap);
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.ADD_GAME_MAP);

            statement.setBinaryStream(1, bais);
            statement.setString(2, gameMap.getPlayer().getName());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameMap gameMap) {

    }

    @Override
    public GameMap get(int id) {
        GameMap gameMap = null;



        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.GET_GAME_MAP);
            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    byte[] currentMap = resultSet.getBytes("current_map");
                    Date savedAt = resultSet.getDate("saved_at");
                    int playerId = resultSet.getInt("player_id");

                    ByteArrayInputStream bi = new ByteArrayInputStream(currentMap);
                    ObjectInputStream oi = new ObjectInputStream(bi);

                    gameMap = (GameMap) oi.readObject();


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return gameMap;
    }

    @Override
    public List<GameMap> getAll() {
        return null;
    }
}
