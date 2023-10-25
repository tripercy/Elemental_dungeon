package cardgame.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import cardgame.model.Element;
import cardgame.model.Enemy;
import cardgame.utils.DBUtils;

public class EnemyDAO implements Dao<Enemy> {
    private static final String TABLE_NAME = "enemies";
    private DBUtils dbUtils;

    public EnemyDAO() {
        this.dbUtils = DBUtils.getInstance();
    }

    @Override
    public Optional<Enemy> get(String key) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        ResultSet resultSet = dbUtils.selectQuery(sql);
        try {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Element element = Element.valueOf(resultSet.getString("element"));
                int maxHealth = resultSet.getInt("health");
                int attack = resultSet.getInt("attack");

                Enemy enemy = new Enemy(name, maxHealth, attack, element);
                return Optional.of(enemy);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Enemy> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        List<Enemy> enemies = new LinkedList<Enemy>();

        try {
            ResultSet resultSet = dbUtils.selectQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Element element = Element.valueOf(resultSet.getString("element"));
                int maxHealth = resultSet.getInt("health");
                int attack = resultSet.getInt("attack");

                Enemy enemy = new Enemy(name, maxHealth, attack, element);
                enemies.add(enemy);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return enemies;
    }

    @Override
    public void save(Enemy t) {
        String sql = "INSERT INTO " + TABLE_NAME
                + "(name, health, element, attack)"
                + " VALUES (\""
                + t.getName() + "\", "
                + t.getMaxHp() + ", \""
                + t.getElement() + "\", "
                + t.getAttack() + ");";
        dbUtils.updateQuery(sql);
    }

    @Override
    public void update(String key, Enemy t) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "name = \"" + t.getName() + "\", "
                + "health = " + t.getMaxHp() + ", "
                + "element = \"" + t.getElement() + "\", "
                + "attack = " + t.getAttack() + " "
                + "WHERE name = \"" + key + "\";";
        dbUtils.updateQuery(sql);
    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        dbUtils.updateQuery(sql);
    }

}
