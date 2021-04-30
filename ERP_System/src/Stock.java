
public class Stock {

	private String id;
	private String product_id;
	private String data;
	private int quantidade;
	
	public Stock(String product_id, String data, int quantidade) {
//		this.setId(id);
		this.setProduct_id(product_id);
		this.setData(data);
		this.setQuantidade(quantidade);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
