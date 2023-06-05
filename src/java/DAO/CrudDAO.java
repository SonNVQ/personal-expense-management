package DAO;

import java.util.List;

/**
 *
 * @author nguyenson
 * @param <T> object type
 * @param <S> id type
 */
public interface CrudDAO<T, S> {

    T add(T obj);

    List<T> getAlls();

    T getById(S id);

    T updateById(S id, T newObj);

    boolean deleteById(S id);

}
