package ADT;

/**
 *
 * @author KHOO HOU SHENG
 * @param <T> the type of elements stored in the list
 */
public interface InterfaceADT<T> {

    /**
     * Task: add a new element to the end of the list
     * @param element the object to be added as a new entry
     */
    public void add(T element);
    
    /**
     * Task: Adds (insert) a new entry at a specified position of the list.
     * @param index the integer that specifies the position to add the element
     * @param element the object to be added as a new element
     */
    public void add(int index, T element);
    
    /**
     * Task: Removes the element at a specified position
     * @param index the integer that specifies the position to remove the element
     */
    public void remove(int index);
    
    /**
     * Task: Clear all element in the list
     */
    public void clear();
    
    /**
     * Task: Replace (update) a new entry at a specified position within the list.
     * @param index the integer that specifies the position to replace the element
     * @param element the object to be replace as a new element
     */
    public void replace(int index, T element);
    
    /**
     * Task: Returns a string representation of all elements in the list
     * @return a string containing all elements in the list in order, formatted like [a, b, c]
     */
    @Override
    public String toString();

    /**
     * Task: Retrieves the element at a given position in the list
     * @param index an integer that indicates the position of the element
     * @return a reference to the indicated element
     */
    public T get(int index);
    
    /**
     * Task: Find the index of the element that match with the given element 
     * @param element the element to search for
     * @return the first index of the matching element or -1 for no match is found
     */
    public int find(T element);
    
    /**
     * Task: Gets the number of element in the list
     * @return the integer number of the element currently in the list
     */
    public int len();
}
