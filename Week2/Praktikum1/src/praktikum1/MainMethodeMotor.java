package praktikum1;

public class MainMethodeMotor {
    public static void main(String[] args) {
        NewClassMotor motor1 = new NewClassMotor();
        System.out.println("Ini pertama");
        motor1.display();
        NewClassMotor motor2 = new NewClassMotor("Vario",2018,"Honda");
        System.out.println("\n hasil kedua");
        motor2.display();
    }
}
