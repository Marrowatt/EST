import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Controller {

	static Scanner s = new Scanner(System.in);
	
	static void findById (List<Product> products) {
    	
    	System.out.print("Informe o código: ");
    	
    	int id = s.nextInt();
    	
    	Product founded = null;
    	
    	for(Product p:products) {
    		if(p.getId() == id) founded = p;
    	}
    	
    	System.out.println(founded != null ? "Encontrado: " + founded.getNome() : "Não encontrado!");
    }
    
	static void findByName (List<Product> products) {
    	
    	System.out.print("Informe o nome: ");
    	
    	String nome = s.next();
    	
    	String prods = "";
    	
    	for(Product p:products) {
    		
    		if(p.getNome().toLowerCase().contains(nome.toLowerCase())) {

    			prods = prods + "ID: " + p.getId() + " - Nome: " + p.getNome() + "\n";
    		}
    	}
    	
    	System.out.println(prods.equals("") == true ? "Não encontrado" : "Encontrado!\n" + prods);
    }

    static PrintStream removeById (List<Product> products, int toStockLimit) {
    	
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
				
				msg = lastInsertStock.getQuantidade() + " produtos retirados, 0 em estoque.\n";
				
				lastInsertStock.setQuantidade(0);
				
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
    
    static void findBinary (List<Product> products) {
    	
    	int min = 0;
    	int max = products.size() - 1;
    	
    	System.out.printf("Informe o id do produto: ");
    	
    	int id = s.nextInt();
    	
    	String msg = "Fora do limite.";
    	
    	if(id <= max && id > min) {
    		products.sort((o1, o2) -> o1.getId() - o2.getId());
        	
        	Product founded = divided(products, min, max, id);
        	
        	msg = founded == null ? "Não encontrado." : "Encontrado: " + founded.getNome();
    	}
    	
        System.out.println(msg);
    }
    
    static Product divided (List<Product> products, int min, int max, int id) {
    	
    	int teste = Math.round((min + max) / 2);
    	
    	int pid = products.get(teste).getId();
    	
    	if(pid == id) return products.get(teste);
    	
    	return id < pid ? divided(products, min, teste, id) : divided(products, teste, max, id);
    }
    
}
