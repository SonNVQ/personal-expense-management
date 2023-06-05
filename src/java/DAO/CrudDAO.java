package DAO;

import java.util.List;

/**
 *
 * @author nguyenson
 * @param <T> object type
 * @param <S> id type
 */
public interface CrudDAO<T, S> {

    T save(T obj);

    List<T> findAll();

    T findByID(S id);

    T update(T obj);

    T delete(T obj);

}
