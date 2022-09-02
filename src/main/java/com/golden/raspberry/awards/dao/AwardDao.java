package com.golden.raspberry.awards.dao;

import com.golden.raspberry.awards.model.Award;

import java.sql.SQLException;
import java.util.List;

public interface AwardDao {

    List<Award> findAllWinners() throws SQLException;

}
