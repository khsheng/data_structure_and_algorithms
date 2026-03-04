package data_management.service;

import ADT.ListADT;
import java.util.function.Predicate;

/**
 *
 * @author KHOO HOU SHENG
 * @param <T> the type of elements stored in the list
 */
public interface CrudService<T> {
    /**
     * Task: Add a new record
     * @param item the object to be added as a new entry
     */
    public void add(T item);

    /**
     * Task: Remove a record at a specified position
     * @param index the integer that specifies the position to remove the record
     */
    public void remove(int index);

    /**
     * Task: Replace (update) a record at a specified position
     * @param index the integer that specifies the position to replace the record
     * @param item the object to replace the existing record
     */
    public void update(int index, T item);

    /**
     * Task: Search records based on a condition
     * @param predicate a condition to filter records
     * @return a list of records that match the condition
     */
    public ListADT<T> search(Predicate<T> predicate);

}