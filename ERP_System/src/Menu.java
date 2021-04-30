import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	static Scanner s = new Scanner(System.in);
	
	static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) throws Exception {
        
        initiate(100);

        menu();
    }

    public static void initiate (int limit) {
    	
    	String row;

    	BufferedReader csvReader = null;
    	
    	int i = 0;
    	
		try {
			csvReader = new BufferedReader(new FileReader("../file/Products.csv"));
			
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    
			    i++;
			    
			    if(i < limit) products.add(new Product(data[0], data[20], data[15]));
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void menu () {

        int control = 0;

        System.out.println("Bem vindo ao ERP System\nO que você quer fazer?");

        while (control < 3) {
        	
        	System.out.println("1. Busca por código | 2. Busca por nome | 3. Sair");

            control = s.nextInt();

            if(control == 1) findById();

            if(control == 2) findByName();

            if(control >= 3) System.out.println("Bye");

        }
    }
    
    static void findById () {
    	
    	System.out.print("Informe o código: ");
    	
    	String id = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId().equals(id)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "Não encontrado!");
    }
    
    static void findByName () {
    	
    	System.out.print("Informe o nome: ");
    	
    	String nome = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) { // se eu achar mais de um, preciso mostrar em lista
    		if(p.getNome().contains(nome)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "Não encontrado!");
    }

}
