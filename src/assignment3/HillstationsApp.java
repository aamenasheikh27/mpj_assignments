package assignment3;

class Hillstations {

    String name;

    Hillstations(String name) {
        this.name = name;
    }

    void famousfood() {
        System.out.println(name + " is famous for its local cuisine.");
    }

    void famousfor() {
        System.out.println(name + " is a beautiful hill station.");
    }
}

class Mahabaleshwar extends Hillstations {

    Mahabaleshwar() {
        super("Mahabaleshwar");
    }

    @Override
    void famousfood() {
        System.out.println("Mahabaleshwar is famous for Strawberries, Corn, and Mulberries.");
    }

    @Override
    void famousfor() {
        System.out.println("Mahabaleshwar is famous for its strawberry farms, Venna Lake, and scenic viewpoints.");
    }
}

class Ooty extends Hillstations {

    Ooty() {
        super("Ooty");
    }

    @Override
    void famousfood() {
        System.out.println("Ooty is famous for Homemade Chocolate, Varkey, and Ooty Bread.");
    }

    @Override
    void famousfor() {
        System.out.println("Ooty is famous for its Nilgiri Mountains, tea gardens, and Ooty Lake.");
    }
}

class Manali extends Hillstations {

    Manali() {
        super("Manali");
    }

    @Override
    void famousfood() {
        System.out.println("Manali is famous for Siddu, Dham, Chha Gosht, and Trout Fish.");
    }

    @Override
    void famousfor() {
        System.out.println("Manali is famous for adventure sports, Rohtang Pass, and the Beas River.");
    }
}

public class HillstationsApp {

    public static void main(String[] args) {

        Hillstations h;

        System.out.println("=== Method Overriding - Hill Stations ===\n");

        h = new Mahabaleshwar();
        System.out.println("--- " + h.name + " ---");
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Ooty();
        System.out.println("--- " + h.name + " ---");
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Manali();
        System.out.println("--- " + h.name + " ---");
        h.famousfood();
        h.famousfor();
    }
}