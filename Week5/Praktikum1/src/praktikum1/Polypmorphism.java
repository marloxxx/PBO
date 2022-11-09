package praktikum1;

class OverridingParent {

    public int myMethod(int a, int b) {
        return (a + b);
    }
}

class OverridingChild extends OverridingParent {

    public int myMethod(int x, int y) {
        return (x * y);
    }
}

public class Polypmorphism {

    public static void main(String[] args) {
        OverridingParent objParent = new OverridingParent();
        OverridingChild objChild = new OverridingChild();
        System.out.println("myMethod on Parent class: " + objParent.myMethod(3, 17));
        System.out.println("myMethod on Child class: " + objChild.myMethod(7, 32));
    }
}
