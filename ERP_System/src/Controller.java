import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	static Scanner s = new Scanner(System.in);
	
	static void findById (ArrayList<Product> products) {
    	
    	System.out.print("Informe o código: ");
    	
    	int id = s.nextInt();
    	
    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId() == id) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "Não encontrado!");
    }
    
    static void findByName (ArrayList<Product> products) {
    	
    	System.out.print("Informe o nome: ");
    	
    	String nome = s.next();
    	
    	Product founded = null;
    	
    	for(Product p:products) { // se eu achar mais de um, preciso mostrar em lista
    		if(p.getNome().contains(nome)) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "Não encontrado!");
    }

    static PrintStream removeById (ArrayList<Product> products, int toStockLimit) {
    	
    	System.out.println("Para remover, informe o código e a quantidade.");
    	
    	System.out.print("Código: ");
    	
    	int id = s.nextInt();

    	System.out.print("Quantidade: ");
    	
    	int quantidade = s.nextInt();

    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId() == id) founded = p;
    	}

    	String msg = "Produto não encontrado!\n";
    	
    	if(founded == null) return System.out.printf(msg);

		Stock lastInsertStock = founded.getStockFilled().get(toStockLimit - 1);
		
		if(lastInsertStock.getQuantidade() < quantidade) {
			
			System.out.println("Cara, não tem o tanto que tu quer.\nTu quer tirar tudo o que tem?\n1. Sim | 2. Não");
			
			int test = s.nextInt();
			
			msg = "Respondeste algo errado...\n";
			
			if(test == 1) {
				
				lastInsertStock.setQuantidade(0);
				
				msg = "Produto retirado, 0 em estoque.\n";
			}
				
			if(test == 2) msg = "Produto não alterado.\n";
			
			return System.out.printf(msg);
		}
    	
    	lastInsertStock.setQuantidade(lastInsertStock.getQuantidade() - quantidade);
    	
		return System.out.printf("Produto retirado.\n");
    }
	
    static void findByTree (Node root) {
        
    	System.out.printf("Informe o id do produto: ");
    	
    	int id = s.nextInt();
        
        Node founded = rec(root, id);
        
        String msg = founded == null ? "Não encontrado." : "Encontrado: " + founded.getProduct().getNome();
       
        System.out.println(msg);
    }
    
    static Node rec (Node root, int id) {
    	
    	Node focus = root;
    	
    	while (focus.getId() != id) {
            
            focus = id < focus.getId() ? focus.getLeft() : focus.getRight();
            
            if(focus == null) return null;
        }
    	
    	return focus;
    }
    
    static void findBinary (ArrayList<Product> products) {
    	
    	int min = 0;
    	int max = products.size() - 1;
    	
    	System.out.printf("Informe o id do produto: ");
    	
    	int id = s.nextInt();
    	
    	Product founded = divided(products, min, max, id);
    	
    	String msg = founded == null ? "Não encontrado." : "Encontrado: " + founded.getNome();
        
        System.out.println(msg);
    }
    
    static Product divided (ArrayList<Product> products, int min, int max, int id) {
    	
    	int teste = Math.round((min + max) / 2);
    	
    	Product ret = null;
    	
    	int p_id = products.get(teste).getId();
    	
    	if(p_id == id) return products.get(teste);
    	
    	ret = id < p_id ? divided(products, min, teste, id) : divided(products, teste, max, id);
    	
    	return ret;
    }
    
}
