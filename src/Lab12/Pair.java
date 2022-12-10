package Lab12;

public class Pair<V1, V2> {

    private V1 val1;
    private V2 val2;
    
    /**
     * This function is a constructor and set up the given value to the class properties.
     * @param V1 val1
     * @param V2 val2
     */
    public Pair(V1 val1, V2 val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    
    /**
     * This function returns the Generic type value 'val1'.
     * @return V1 val1
     */
    public V1 getVal1() {
        return val1;
    }
    
    /**
     * This function returns the Generic type value 'val2'.
     * @return V2 val2
     */
    public V2 getVal2() {
        return val2;
    }
    
}
