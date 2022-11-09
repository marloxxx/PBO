package uts;

class Shape {

    void draw() {
        System.out.println("drawing....");
    }
}

class Rectangle extends Shape {

    void draw() {
        System.out.println("drawing Rectangle...");
    }
}

class Circle extends Shape {

    void draw() {
        System.out.println("drawing Circle...");
    }
}

class Triangle extends Shape {

    void draw() {
        System.out.println("drawing Triangle...");
    }
}

class soal1_051 {

    public static void main(String[] args) {
        Shape s = new Shape();
        Rectangle r = new Rectangle();
        Circle c = new Circle();
        Triangle t = new Triangle();
        s = r;
        s.draw();
        s = c;
        s.draw();
        s = t;
        s.draw();
    }
}
