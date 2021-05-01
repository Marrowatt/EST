import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {

	static Scanner s = new Scanner(System.in);
	
	static ArrayList<Product> products = new ArrayList<Product>();
	
	static int toStockLimit = 20;

    public static void main(String[] args) throws Exception {
        
        initiate(100);

        menu();
    }

    static ArrayList<Stock> toStock (String product_id) {
    	
    	ArrayList<Stock> estoque = new ArrayList<Stock>();

    	for(int i = 0; i < toStockLimit; i++) {
    		
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
			    
			    ArrayList<Stock> stockFilled = toStock(data[0]);
			    
			    if(i < limit) products.add(new Product(data[0], data[20], data[15], stockFilled));
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void menu () {

        boolean loop = true;

        System.out.println("Bem vindo ao ERP System\nO que voc� quer fazer?");

        while (loop) {
        	
        	System.out.println("1. Busca por c�digo | 2. Busca por nome | 3. Sair");

            int control = s.nextInt();

            if(control == 1) findById();

            if(control == 2) findByName();
            
            if(control == 3) removeById();

            if(control >= 4) loop = false;

        }
        
        System.out.println("Bye");
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

    static PrintStream removeById () {
    	
    	System.out.println("Para remover, informe o c�digo e a quantidade.");
    	
    	System.out.print("C�digo: ");
    	
    	String id = s.next();

    	System.out.print("Quantidade: ");
    	
    	int quantidade = s.nextInt();

    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId().equals(id)) founded = p;
    	}

    	String msg = "Produto n�o encontrado!\n";
    	
    	if(founded == null) return System.out.printf(msg);

		Stock lastInsertStock = founded.getStockFilled().get(toStockLimit - 1);
		
		if(lastInsertStock.getQuantidade() < quantidade) {
			
			System.out.println("Cara, n�o tem o tanto que tu quer.\nTu quer tirar tudo o que tem?\n1. Sim | 2. N�o");
			
			int test = s.nextInt();
			
			msg = "Respondeste algo errado...\n";
			
			if(test == 1) {
				
				lastInsertStock.setQuantidade(0);
				
				msg = "Produto retirado, 0 em estoque.\n";
				
			}
				
			if(test == 2) msg = "Produto n�o alterado.\n";
			
			return System.out.printf(msg);
		}
    	
    	lastInsertStock.setQuantidade(lastInsertStock.getQuantidade() - quantidade);
    	
		return System.out.printf("Produto retirado.\n");
    }
}
