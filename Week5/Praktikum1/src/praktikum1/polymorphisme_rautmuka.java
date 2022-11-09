package praktikum1;

class rautMuka {

    public String Respons() {
        return ("Lihat reaksi wajah saya \n");
    }
}

class Gembira extends rautMuka {

    public String Respons() {
        return ("ha ha... hi.. hi.. saya sedang gembira \n");
    }
}

class Sedih extends rautMuka {

    public String Respons() {
        return ("Hik.. Hik.. teganya dia berbuat itu.. \n");
    }
}

class Marah extends rautMuka {

    public String Respons() {
        return ("Hei!! Jangan dekati saya!\n");
    }
}

public class polymorphisme_rautmuka {

    public static void main(String[] args) {
        System.out.println("Contoh Polymorphisme");
        rautMuka objRaut = new rautMuka();
        Gembira objGembira = new Gembira();
        Sedih objSedih = new Sedih();
        Marah objMarah = new Marah();

        rautMuka[] ekspresi = new rautMuka[4];
        ekspresi[0] = objRaut;
        ekspresi[1] = objGembira;
        ekspresi[2] = objSedih;
        ekspresi[3] = objMarah;

        for (int i = 0; i < ekspresi.length; i++) {
            System.out.println("Ekspresi [" + i + "] = " + ekspresi[i].Respons());
        }
    }
}
