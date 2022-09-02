package com.golden.raspberry.awards.dao;

import com.golden.raspberry.awards.model.Award;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AwardDaoImpl implements AwardDao{

   @Value("${spring.datasource.url}")
   private String springDataSourceUrl;

    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;


    @Override
    public List<Award> findAllWinners() throws SQLException {
        List<Award> awards = new ArrayList<>();
        String jdbcURL = springDataSourceUrl;
        String username = springDatasourceUsername;
        String password = springDatasourcePassword;

        Connection connection = DriverManager.getConnection(jdbcURL, username, password);

        String sql = "SELECT * FROM award WHERE WINNER = 'yes'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String year = resultSet.getString("year");
            String title = resultSet.getString("title");
            String studios = resultSet.getString("studios");
            String producers = resultSet.getString("producers");
            String winner = resultSet.getString("winner");
            Award award = new Award(id,year, title, studios,producers, winner);
            awards.add(award);
        }
        connection.close();
        return awards;
    }
}
