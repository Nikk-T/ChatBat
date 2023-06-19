package com.creativeinstall.chatbat.dao;

import com.creativeinstall.chatbat.business.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements Dao<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private JdbcTemplate jdbcTemplate;
    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM STUDENTS AS S LEFT OUTER JOIN GROUPS AS G ON S.GROUP_ID = G.ID WHERE S.ID=?";
    public static final String FIND_ALL_USERS_SQL = "SELECT * FROM STUDENTS AS S LEFT OUTER JOIN GROUPS AS G ON S.GROUP_ID = G.ID";
    public static final String SAVE_USER_SQL = "INSERT INTO STUDENTS (GROUP_ID, FIRST_NAME, SECOND_NAME) VALUES(?, ?, ?)";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM STUDENTS WHERE ID=?";
    public static final String UPDATE_USER_SQL = "UPDATE STUDENTS SET GROUP_ID=?, FIRST_NAME=?, SECOND_NAME=? WHERE ID=?";

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    RowMapper<User> rowMapper = (rs, rowNum) -> {User user = new User();
        user.setId(rs.getInt("ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("SECOND_NAME"));
        return user;};

    @Override
    public Optional<User> get(int id) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_USER_BY_ID_SQL, new Object[] {id}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.info("Record not found {}", id);
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_USERS_SQL, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Records not found");
            return null;
        }
    }
    @Override
    public User save(User user) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(SAVE_USER_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getPasswordHash());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            return preparedStatement;
        }, generatedKeyHolder);
        user.setId((int) generatedKeyHolder.getKeys().get("id"));
        return user;
    }


    @Override
    public int update(User user) {
        try{
            return jdbcTemplate.update(UPDATE_USER_SQL, user.getPasswordHash(), user.getLastName(), user.getFirstName(), user.getId());
        } catch (EmptyResultDataAccessException e) {
            logger.info("Record not found for update {}", user.getId());
            return 0;
        }
    }

    @Override
    public int delete(User user) {
        try{
            return jdbcTemplate.update(DELETE_BY_ID_SQL, user.getId());
        } catch (EmptyResultDataAccessException e) {
            logger.info("Record not found for deletion {}", user.getId());
            return 0;
        }
    }
}
