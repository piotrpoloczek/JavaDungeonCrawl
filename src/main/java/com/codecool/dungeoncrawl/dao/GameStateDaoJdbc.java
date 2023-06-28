package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;
    private PlayerDao playerDao;

    @Override
    public void add(GameState state) {
        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.ADD_GAME_STATE);

            statement.setString(1, state.getCurrentMap());
            statement.setInt(2, state.getPlayer().getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.UPDATE_GAME_STATE);

            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.GET_GAME_STATE);
            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    String currentMap = resultSet.getString("current_map");
                    Date savedAt = resultSet.getDate("saved_at");
                    int playerId = resultSet.getInt("player_id");

                    PlayerModel playerModel = playerDao.get(playerId);

                    return new GameState(currentMap, savedAt, playerModel);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<GameState> getAll() {
        List<GameState> gameStates = new ArrayList<>();

        try(Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.GET_ALL_GAME_STATES);

            while(resultSet.next()) {
                String currentMap = resultSet.getString("current_map");
                Date savedAt = resultSet.getDate("saved_at");

                int playerId = resultSet.getInt("player_id");
                PlayerModel playerModel = playerDao.get(playerId);

                gameStates.add(new GameState(currentMap, savedAt, playerModel));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return gameStates;
    }
}
