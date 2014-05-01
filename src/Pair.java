/**
 * User: Ryan Bottoms
 * Date: 5/1/2014
 * Time: 5:57 PM
 */
public class Pair<K,V> {
    private final K element0;
    private final V element1;

    public static <K, V> Pair<K, V> createPair(K element0, V element1) {
        return new Pair<K, V>(element0, element1);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getElement0() {
        return element0;
    }

    public V getElement1() {
        return element1;
    }
    @Override
    public String toString() {
    return "{" + element0 + ", " + element1 + "}";
    }

}
