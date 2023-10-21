package cardgame.DAO;

import cardgame.Model.Card;
import cardgame.Model.Element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class CardDAO implements Dao<Card> {
    private static final String TABLE_NAME = "cards";
    private final Connection conn;

    public CardDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Card> get(String key) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
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
            Statement statement = conn.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
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

        String sql = "INSERT INTO " + TABLE_NAME + " (name, cost, element, effects) VALUES (";
        sql += "\"" + t.getName() + "\"," + t.getCost() + ", " + "\"" +t.getElement() +"\", " + "\"" + effects + "\");";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }
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
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(sql);
        }
    }

    @Override
    public void delete(String key) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE name = \"" + key + "\";";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
