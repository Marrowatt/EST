import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {

	static Scanner s = new Scanner(System.in);
	
	static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) throws Exception {
        
        initiate(100);

        menu();
    }

    static ArrayList<Stock> toStock (int limit, String product_id) {
    	
    	ArrayList<Stock> estoque = new ArrayList<Stock>();

    	for(int i = 0; i < limit; i++) {
    		
    		Random rand = new Random();
    		
    		estoque.add(new Stock(product_id, "2020-04-30", (rand.nextInt(99) + 1)));
    		
    	}
    	
    	return estoque;
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
			    
			    ArrayList<Stock> stockFilled = toStock(20, data[0]);
			    
			    if(i < limit) products.add(new Product(data[0], data[20], data[15], stockFilled));
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void menu () {

        int control = 0;

        System.out.println("Bem vindo ao ERP System\nO que voc� quer fazer?");

        while (control < 3) {
        	
        	System.out.println("1. Busca por c�digo | 2. Busca por nome | 3. Sair");

            control = s.nextInt();

            if(control == 1) findById();

            if(control == 2) findByName();
            
            if(control == 3) removeById();

            if(control >= 4) System.out.println("Bye");

        }
    }
    
    static void findById () {
    	
    	System.out.print("Informe o c�digo: ");
    	
    	String id = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId().equals(id)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "N�o encontrado!");
    }
    
    static void findByName () {
    	
    	System.out.print("Informe o nome: ");
    	
    	String nome = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) { // se eu achar mais de um, preciso mostrar em lista
    		if(p.getNome().contains(nome)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "N�o encontrado!");
    }

    static void removeById () {
    	
    	System.out.println("Para remover, informe o c�digo e a quantidade.");
    	
    	System.out.print("C�digo: ");
    	
    	String id = s.next();

    	System.out.print("Quantidade: ");
    	
    	String quantidade = s.next();

    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId().equals(id)) founded = p;
    	}

    	String msg = "Produto n�o encontrado!";
    	
    	System.out.println(msg);
    }
}
