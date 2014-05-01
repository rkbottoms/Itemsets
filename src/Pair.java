/**
 * User: Ryan Bottoms
 * Date: 5/1/2014
 * Time: 5:57 PM
 */
public class Pair<K,V> {
    private final K element1;
    private final V element2;

    public static <K, V> Pair<K, V> createPair(K element1, V element2) {
        return new Pair<K, V>(element1, element2);
    }

    public Pair(K element1, V element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public K getElement1() {
        return element1;
    }

    public V getElement2() {
        return element2;
    }

    @Override
    public String toString() {
    return "{" + element1 + ", " + element2 + "}";
    }

}
