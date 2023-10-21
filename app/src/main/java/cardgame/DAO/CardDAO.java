package cardgame.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import cardgame.model.Card;
import cardgame.model.Element;
import cardgame.utils.DBUtils;

public class CardDAO implements Dao<Card> {
    private static final String TABLE_NAME = "cards";
    private DBUtils dbUtils;

    public CardDAO() {
        this.dbUtils = DBUtils.getInstance();
    }

    @Override
    public Optional<Card> get(String key) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        ResultSet resultSet = dbUtils.selectQuery(sql);
        try {
            if (resultSet.next()) {
                int cost = resultSet.getInt("cost");
                Element element = Element.valueOf(resultSet.getString("element"));
                String effects = resultSet.getString("effects");
                Queue<String> effectsQueue = new LinkedList<String>();

                // Split the effects string into a queue, delimited by $
                for (String effect : effects.split("$")) {
                    effectsQueue.add(effect);
                }

                Card card = new Card(key, cost, element, effectsQueue);

                return Optional.of(card);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Card> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        List<Card> cards = new LinkedList<Card>();

        try {
            ResultSet resultSet = dbUtils.selectQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                Element element = Element.valueOf(resultSet.getString("element"));
                String effects = resultSet.getString("effects");
                Queue<String> effectsQueue = new LinkedList<String>();

                // Split the effects string into a queue, delimited by $
                for (String effect : effects.split("$")) {
                    effectsQueue.add(effect);
                }

                Card card = new Card(name, cost, element, effectsQueue);
                cards.add(card);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cards;
    }

    @Override
    public void save(Card t) {
        String effects = "";
        for (String effect : t.getEffects()) {
            effects += effect + "$";
        }

        String sql = "INSERT INTO " + TABLE_NAME +
                " (name, cost, element, effects) VALUES ("
                + "\"" + t.getName()
                + "\"," + t.getCost() + ", " + "\""
                + t.getElement() + "\", "
                + "\"" + effects + "\");";

        dbUtils.updateQuery(sql);
    }

    @Override
    public void update(String key, Card t) {
        String effects = "";
        for (String effect : t.getEffects()) {
            effects += effect + "$";
        }

        String sql = "UPDATE " + TABLE_NAME + " SET " +
                "name = \"" + t.getName() + "\", " +
                "cost = " + t.getCost() + ", " +
                "element = \"" + t.getElement() + "\", " +
                "effects = \"" + effects + "\" " +
                "WHERE name = \"" + t.getName() + "\";";

        dbUtils.updateQuery(sql);
    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";

        dbUtils.updateQuery(sql);
    }

}
