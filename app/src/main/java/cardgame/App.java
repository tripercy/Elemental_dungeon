package cardgame;

import java.sql.*;
import java.util.LinkedList;

import cardgame.dao.CardDAO;
import cardgame.model.Card;
import cardgame.model.Element;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/element_cg";
        String username = "root";
        String password = "12345678";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            CardDAO cardDAO = new CardDAO();

            cardDAO.update("Slash", new Card("Slash", 1, Element.FIRE, new LinkedList<String>()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}