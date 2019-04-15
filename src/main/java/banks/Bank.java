package banks;

import java.util.List;

public class Bank{
	private String name;
	private List<ExchangeRate> exchangerate;

	public Bank(String name, List<ExchangeRate> exchangerate) {
		this.name = name;
		this.exchangerate = exchangerate;
	}
	
	public Bank() {
	}

	public String getName() {
		return name;
	}
	
	public List<ExchangeRate> getExchangeRate() {
		return exchangerate;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setExchangeRate(List<ExchangeRate> exchangerate) {
		this.exchangerate = exchangerate;
	}
	
	public double getSellingRate(String currency) {
		return exchangerate.stream().filter(t -> t.getCurrencyCode().equals(currency)).findFirst().get().getSelling();
	}
	
	public double getBuyingRate(String currency) {
		return exchangerate.stream().filter(t -> t.getCurrencyCode().equals(currency)).findFirst().get().getBuying();
	}
	
	public void setSellingRate(String currency, double selling) {
		exchangerate.stream().filter(t -> t.getCurrencyCode().equals(currency)).findFirst().get().setSelling(selling);
	}
	
	public void setBuyingRate(String currency, double buying) {
		exchangerate.stream().filter(t -> t.getCurrencyCode().equals(currency)).findFirst().get().setBuying(buying);
	}
	
	public void deleteCurrency(String currency) {
		exchangerate.remove(exchangerate.stream().filter(t -> t.getCurrencyCode().equals(currency)).findFirst().get());
	}
	
}
