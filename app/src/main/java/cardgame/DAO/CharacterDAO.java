package cardgame.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import cardgame.model.Character;
import cardgame.model.Element;

public class CharacterDAO implements Dao<Character>{
    private static final String TABLE_NAME = "characters";
    private final Connection conn;

    public CharacterDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Character> get(String key) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                

                return Optional.of(card);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Character> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void save(Character t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(String key, Character t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
