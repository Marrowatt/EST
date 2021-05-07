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
	
	static Node root;
	
	static int toStockLimit = 20;
	
	static int limitingProducts = 100;

    public static void main(String[] args) throws Exception {

        initiate(limitingProducts);
        
    	seedingATree();

        System.out.println("Bem vindo ao ERP System\nO que você quer fazer?");

        menu();
        
        System.out.println("Bye");
        
        s.close();
    }
    
    static void seedingATree () {
    	
    	for(Product p:products) {
    		Node.insert(Integer.parseInt(p.getId()), root);
    	}
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
    	
    	String splitador = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    	
    	int i = 0;
    	
		try {
			csvReader = new BufferedReader(new FileReader("../file/Products.csv"));
			
			while ((row = csvReader.readLine()) != null && i < limit) {
				
				if(i > 0) {
					
				    String[] data = row.split(splitador);
				    
				    ArrayList<Stock> stockFilled = toStock(data[0]);
				    
				    products.add(new Product(data[0], data[21], data[15], stockFilled));
				}
				
				i++;
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void menu () {
        	
    	System.out.println("1. Busca por código | 2. Busca por nome | 3. Remover por Id |"
    			+ " 4. Busca pela árvore | 5. Sair");

        int control = s.nextInt();

        if(control == 1) Controller.findById(products);

        if(control == 2) Controller.findByName(products);
        
        if(control == 3) Controller.removeById(products, toStockLimit);

        if(control == 4) Controller.findByTree(products, root);
        
        menu();
    }

}
