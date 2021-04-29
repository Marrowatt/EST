import java.util.Scanner;

public class Menu {

	static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        
        initiate();

        menu();
    }

    public static void initiate () {

        // aqui vamos tentar iniciar o csv e setar bonitinho

    }

    public static void menu () {

        // famoso menu

        int control = 0;

        System.out.println("Bem vindo ao ERP System\nO que você quer fazer?");

        while (control < 3) {

            control = s.nextInt();

            if(control == 1) System.out.println("Buscar produto por código");

            if(control == 2) System.out.println("Buscar produto por nome");

            if(control >= 3) System.out.println("Tchaaau");

        }

    }

}
