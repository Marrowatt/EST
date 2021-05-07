import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	static Scanner s = new Scanner(System.in);
	
	static void findById (ArrayList<Product> products) {
    	
    	System.out.print("Informe o c�digo: ");
    	
    	String id = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId().equals(id)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "N�o encontrado!");
    }
    
    static void findByName (ArrayList<Product> products) {
    	
    	System.out.print("Informe o nome: ");
    	
    	String nome = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) { // se eu achar mais de um, preciso mostrar em lista
    		if(p.getNome().contains(nome)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "N�o encontrado!");
    }

    static PrintStream removeById (ArrayList<Product> products, int toStockLimit) {
    	
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
	
    static void findByTree (ArrayList<Product> products, Node root) {
        
    	System.out.printf("Informe o id do produto: ");
    	
    	int id = s.nextInt();
    	
        Node focus = root;
        
        while (focus.getId() != id) {
        	
            if (id < focus.getId()) {
            	focus = focus.getLeft();
            } else {
            	focus = focus.getRight();
            }
            
        }
        
        if(focus == null) {
        	System.out.println("N�o encontrado.");
        } else {
        	System.out.println("Encontrado: " + focus.getProduct().toString());
        }
       
    }
    
}
