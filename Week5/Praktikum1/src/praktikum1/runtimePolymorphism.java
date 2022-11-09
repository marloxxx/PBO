package praktikum1;

interface MyinterfaceA {

    public void m1();
}

interface MyinterfaceB extends MyinterfaceA {

    public void m2();
}

class MyClassA implements MyinterfaceB {

    public void m1() {
        System.out.println("m1 from MyClassA");
    }

    public void m2() {
        System.out.println("m2 from MyClassA");
    }
}

class MyClassB implements MyinterfaceB {

    public void m1() {
        System.out.println("m1 from MyClassB");
    }

    public void m2() {
        System.out.println("m2 from MyClassB");
    }
}

public class runtimePolymorphism {

    public static void main(String[] args) {
        MyinterfaceB mib;
        mib = new MyClassA();
        mib.m1();
        mib.m2();
        mib = new MyClassB();
        mib.m1();
        mib.m2();
    }
}
