package com.Predvca.lab03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FruitDBRepository implements FruitRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger(FruitDBRepository.class);

    public FruitDBRepository(Properties props) {
        logger.info("Initializing FruitDBRepository with properties: {}", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public List<Fruit> findByDistributor(String distributor) {
        logger.info("Finding fruits by distributor: {}", distributor);
        List<Fruit> fruits = new ArrayList<>();
        String query = "SELECT * FROM fruit WHERE distributor = ?";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, distributor);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                fruits.add(mapResultSetToFruit(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding fruits by distributor: {}", e.getMessage());
        }
        return fruits;
    }

    @Override
    public List<Fruit> findByName(String name) {
        logger.info("Finding fruits by name: {}", name);
        List<Fruit> fruits = new ArrayList<>();
        String query = "SELECT * FROM fruit WHERE name = ?";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                fruits.add(mapResultSetToFruit(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error finding fruits by name: {}", e.getMessage());
        }
        return fruits;
    }

    @Override
    public void add(Fruit elem) {
        logger.info("Adding new fruit: {}", elem);
        String query = "INSERT INTO fruit (name, variety, distributor, quantity, unit) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, elem.getName());
            statement.setString(2, elem.getVariety());
            statement.setString(3, elem.getDistributor());
            statement.setInt(4, elem.getQuantity());
            statement.setString(5, elem.getUnit());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating fruit failed, no rows affected.");
            }

            // Retrieve generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    elem.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating fruit failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Error adding fruit: {}", e.getMessage());
        }
    }

    @Override
    public void update(Integer id, Fruit elem) {
        logger.info("Updating fruit with ID: {}", id);
        String query = "UPDATE fruit SET name = ?, variety = ?, distributor = ?, quantity = ?, unit = ? WHERE id = ?";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, elem.getName());
            statement.setString(2, elem.getVariety());
            statement.setString(3, elem.getDistributor());
            statement.setInt(4, elem.getQuantity());
            statement.setString(5, elem.getUnit());
            statement.setInt(6, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating fruit failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Error updating fruit: {}", e.getMessage());
        }
    }

    @Override
    public Iterable<Fruit> findAll() {
        logger.info("Retrieving all fruits...");
        List<Fruit> fruits = new ArrayList<>();
        String query = "SELECT * FROM fruit";

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                fruits.add(mapResultSetToFruit(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all fruits: {}", e.getMessage());
        }
        return fruits;
    }

    private Fruit mapResultSetToFruit(ResultSet resultSet) throws SQLException {
        return new Fruit(
                resultSet.getString("name"),
                resultSet.getString("variety"),
                resultSet.getString("distributor"),
                resultSet.getInt("quantity"),
                resultSet.getString("unit")
        );
    }
}
