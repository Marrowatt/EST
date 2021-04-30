import java.util.ArrayList;

public class Product {

	private String id;
	private String nome;
	private String amount;
	private ArrayList<Stock> stockFilled;
	
	public Product (String id, String nome, String amount, ArrayList<Stock> stockFilled) {
		this.setId(id);
		this.setNome(nome);
		this.setAmount(amount);
		this.setStockFilled(stockFilled);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public ArrayList<Stock> getStockFilled() {
		return stockFilled;
	}

	public void setStockFilled(ArrayList<Stock> stockFilled) {
		this.stockFilled = stockFilled;
	}

}
