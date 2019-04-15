package banks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"currencyCode", "buying", "selling"})
public class ExchangeRate {
	 @JsonProperty("currencyCode")
	private String currencyCode;
	
	 @JsonProperty("buying")
	private double buying;
	
	 @JsonProperty("selling")
	private double selling;
	
	public ExchangeRate() {};
	
	public ExchangeRate(String currencyCode, double buying, double selling) {
		this.currencyCode = currencyCode;
		this.buying = buying;
		this.selling = selling;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public double getBuying() {
		return buying;
	}
	public void setBuying(double buying) {
		this.buying = buying;
	}
	public double getSelling() {
		return selling;
	}
	public void setSelling(double selling) {
		this.selling = selling;
	}
	
	
}