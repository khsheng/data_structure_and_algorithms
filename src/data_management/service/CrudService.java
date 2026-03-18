package data_management.service;

import ADT.ListADT;
import java.util.function.*;;


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
     * Task: Add multiple records
     * @param items a list of objects to be added as new entries
     */
    public void add(ListADT<T> items);

    /**
     * Task: Remove a record at a specified position
     * @param index the integer that specifies the position to remove the record
     */
    public void remove(int index);


    /**
     * Task: Replace (update) a record at a specified position
     * @param index the integer that specifies the position to replace the record
     * @param set the condition to update the record
     */
    public void update(int index, Consumer<T> set);


    /**
     * Task: Search for records that match the specified criteria
     * @param predicate the condition to filter the records
     * @return a list of records that match the specified criteria
     */
    public ListADT<T> search(Predicate<T> predicate);

}