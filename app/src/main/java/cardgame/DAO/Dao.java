package cardgame.DAO;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(String key);

    List<T> getAll();

    void save(T t);

    void update(String key, T t);

    void delete(String key);
}