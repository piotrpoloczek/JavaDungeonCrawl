package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.ADD_PLAYER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.UPDATE_PLAYER);

            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setInt(5, player.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(Queries.GET_PLAYER);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int hp = resultSet.getInt("hp");
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");

                PlayerModel playerModel = new PlayerModel(playerName, x, y);
                playerModel.setHp(hp);

                return playerModel;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }

        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        List<PlayerModel> playerModels = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(Queries.GET_ALL_PLAYERS);

            while(resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int hp = resultSet.getInt("hp");
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");

                PlayerModel playerModel = new PlayerModel(playerName, x, y);
                playerModel.setHp(hp);

                playerModels.add(playerModel);
            }

            return playerModels;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
