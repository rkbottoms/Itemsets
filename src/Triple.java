/**
 * User: Ryan Bottoms
 * Date: 5/1/2014
 * Time: 6:53 PM
 */
public class Triple<A,B,C> {
    private final A element1;
    private final B element2;
    private final C element3;

    public static <A, B, C> Triple<A, B, C> createTriple(A element1, B element2, C element3) {
        return new Triple<A,B,C>(element1, element2, element3);
    }

    public Triple(A element1, B element2, C element3) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }

    public A getElement1() {
        return element1;
    }

    public B getElement2() {
        return element2;
    }

    public C getElement3() {
        return element3;
    }

    @Override
    public String toString() {
        return "{" + element1 + ", " + element2 + ", " + element3 + '}';
    }
}
