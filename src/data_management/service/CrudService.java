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
     * Task: Replace (update) a record for Staff only
     * @param index the integer that specifies the position to replace the record
     * @param name the updated name
     * @param age the updated age
     * @param position the updated position
     */
    public void update(int index, String name, int age, String position);

    /**
     * Task: Replace (update) a record for Student only
     * @param index the integer that specifies the position to replace the record
     * @param name the updated name
     * @param age the updated age
     * @param program the updated program
     * @param borrowedBooks the updated number of borrowed books
     */
    public void update(int index, String name, int age, String program, int borrowedBooks);

    /**
     * Task: Search records based on a condition
     * @param predicate a condition to filter records
     * @return a list of records that match the condition
     */
    public ListADT<T> search(Predicate<T> predicate);

}