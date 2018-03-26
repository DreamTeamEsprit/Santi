/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author user
 */
import java.util.List;

public interface IService<T, R> {

    void add(T t);

    void update(T t);

    void remove(R r);

    T findById(R r);

    List<T> getAll();
}
