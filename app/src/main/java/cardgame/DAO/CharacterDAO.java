package cardgame.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import cardgame.model.Character;
import cardgame.model.Element;
import cardgame.utils.DBUtils;

public class CharacterDAO implements Dao<Character> {
    private static final String TABLE_NAME = "characters";
    private final DBUtils dbUtils;

    public CharacterDAO() {
        dbUtils = DBUtils.getInstance();
    }

    @Override
    public Optional<Character> get(String key) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        try {

            ResultSet resultSet = dbUtils.selectQuery(sql);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int health = resultSet.getInt("health");
                int mana = resultSet.getInt("mana");
                Element element = Element.valueOf(resultSet.getString("element"));

                Character character = new Character(name, health, mana, element);

                return Optional.of(character);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Character> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        List<Character> characters = new LinkedList<Character>();

        try {
            ResultSet resultSet = dbUtils.selectQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int health = resultSet.getInt("health");
                int mana = resultSet.getInt("mana");
                Element element = Element.valueOf(resultSet.getString("element"));

                Character character = new Character(name, health, mana, element);

                characters.add(character);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return characters;
    }

    @Override
    public void save(Character t) {
        String sql = "INSERT INTO " + TABLE_NAME
                + " (name, health, mana, element)"
                + " VALUES ("
                + "\"" + t.getName() + "\", "
                + t.getHealth() + ", "
                + t.getMana() + ", "
                + "\"" + t.getElement().toString() + "\""
                + ");";
        dbUtils.updateQuery(sql);
    }

    @Override
    public void update(String key, Character t) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "health = " + t.getHealth() + ", "
                + "mana = " + t.getMana() + ", "
                + "element = \"" + t.getElement().toString() + "\""
                + " WHERE name = \"" + key + "\";";
        dbUtils.updateQuery(sql);
    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        dbUtils.updateQuery(sql);
    }

}
