package ADT;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 *
 * @author KHOO HOU SHENG
 */
public class ListADT<T> implements InterfaceADT<T>{
    private T[] array;
    private int numberOfElement = 0;
    private static final int DEFAULT_CAPACITY = 8;

    public ListADT(){
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T element){
        if (isArrayFull()){
            increaseArraySize();
        }

        array[numberOfElement] = element;
        numberOfElement++;
    }

    @Override
    public void add(int newIndex, T element){
        // validate index for adding an element
        if (newIndex < 0){
            throw new IndexOutOfBoundsException(
                "Invalid index: " + newIndex
            );
        }else if (newIndex > numberOfElement){
            newIndex = numberOfElement;
        }

        if (isArrayFull()){
            increaseArraySize();
        }

        // Shift elements to the right
        for (int index = numberOfElement - 1; index >= newIndex; index--){
            array[index + 1] = array[index]; 
        }

        array[newIndex] = element;
        numberOfElement++;
    }

    @Override
    public void remove(int index){
        inBoundsValidation(index, "remove");

        for (int i = index; i < numberOfElement; i++){
            array[i] = array[i+1];
        }

        array[numberOfElement -1] = null;
        numberOfElement = numberOfElement - 1;
    }

    @Override
    public void clear(){
        for (int i = 0; i <= numberOfElement -1 ; i++){
            array[i] = null;
        }

        numberOfElement = 0;
    }

    @Override
    public void replace(int index, T element){
        inBoundsValidation(index, "replace");
        array[index] = element;
    }

    @Override
    public String toString(){
        if (numberOfElement == 0) return "[]";

        StringBuilder newString = new StringBuilder();
        newString.append("[");
        for (int i = 0; i < numberOfElement; i++){
            newString.append(array[i]);
            if (i != numberOfElement - 1) newString.append(", ");
        }

        newString.append("]");
        return newString.toString();
    }

    @Override
    public T get(int index){
        inBoundsValidation(index, "get");
        return array[index];
    }

    @Override
    public int find(T element){
        for (int i = 0; i <= numberOfElement - 1; i++){
            if (array[i].equals(element)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public  ListADT<Integer> findAll(T element){
        ListADT<Integer> indexList = new ListADT<>();

        for (int i = 0; i <= numberOfElement - 1; i++){
            if (array[i].equals(element)){
                indexList.add(i);
            }
        }

        if (indexList.len() == 0) {
            indexList.add(-1);
        }

        return indexList;
    }


    @Override
    public ListADT<Integer> findAll(Predicate<T> condition) {
        ListADT<Integer> indexList = new ListADT<>();

        for (int i = 0; i < numberOfElement; i++) {
            if (condition.test(array[i])) {
                indexList.add(i);
            }
        }

        if (indexList.len() == 0) {
            indexList.add(-1);
        }

        return indexList;
    }
    
    @Override
    public int len(){
        return numberOfElement;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        T[] sortting_array = toArray();
        Arrays.sort(sortting_array,  comparator);

        array = sortting_array;
    }

    @Override
    public T[] toArray(){
        T[] newArray = (T[]) new Object[numberOfElement];
        for (int i = 0; i < numberOfElement; i++){
            newArray[i] = array[i];
        }

        return newArray;
    }

    private boolean isArrayFull(){
        return numberOfElement == array.length;
    }

    private void increaseArraySize(){
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    } 

    private void inBoundsValidation(int index, String process){
        if ((index >= numberOfElement) || (index < 0)) {
            throw new IndexOutOfBoundsException(
                "Index '" + index + "' not exist and invalide for " + process
            );
        }
    }
}


