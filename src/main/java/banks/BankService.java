package banks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BankService{
	private List<Bank> banks  = new ArrayList<>();
	
	public List<Bank> getAllBanks(){
		return banks;
	}
	
	public void addBank(Bank bank) {
		banks.add(bank);
	}
	
	public void updateBankSellingRate(String bankname, String currency, Double selling) {
		banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().setSellingRate(currency, selling);
	}
	
	public void updateBankBuyingRate(String bankname, String currency, Double buying) {
		banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().setBuyingRate(currency, buying);
	}
		
	public void deleteBankCurrency(String bankname, String currency) {
		banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().deleteCurrency(currency);
	}

	public Map<String, Double> getAllSellingRates(String currency) {
		Map<String, Double> selling = new HashMap<>();
		banks.stream()
		.forEach(b -> b.getExchangeRate()
				.stream().filter(e -> e.getCurrencyCode()
						.equals(currency) && e.getSelling() > 0)
				.forEach(x -> selling.put(b.getName(), x.getSelling()) )
				);		
		return selling;
	}
	
	public Map<String, Double> getAscendingSortedSellingRates (String currency){
		return ascendingSort(getAllSellingRates(currency));
	}
	
	public Map<String, Double> getDescendingSortedSellingRates (String currency){
		return descendingSort(getAllSellingRates(currency)); 
	}
	
	public Map<String, Double> getAllBuyingRates(String currency) {
		Map<String, Double> buying = new HashMap<>();
		banks.stream()
			.forEach(b -> b.getExchangeRate()
					.stream().filter(e -> e.getCurrencyCode()
							.equals(currency) && e.getBuying() > 0)
					.forEach(x -> buying.put(b.getName(), x.getBuying()) )
					);
		return buying;
	}

	public Map<String, Double> getAscendingSortedBuyingRates (String currency){
		return ascendingSort(getAllBuyingRates(currency));
	}
	
	public Map<String, Double> getDescendingSortedBuyingRates (String currency){
		return descendingSort(getAllBuyingRates(currency)); 
	}

	
	public double getBankByuingRate(String bankname, String currency) {
		return banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().getBuyingRate(currency);
	}

	public void setBankSellingRate(String bankname, String currency, double selling) {
		banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().setSellingRate(currency, selling);
	}

	public void setBankByuingRate(String bankname, String currency, double buying) {
		banks.stream().filter(x -> x.getName().equals(bankname)).findFirst().get().setBuyingRate(currency, buying);
	}
	
	public Map<String, String> bestSellingsAndBuyings(){
		Map<String, String> report = new HashMap<>();
		banks.stream().forEach(b -> b.getExchangeRate()
				.stream().forEach(e -> report.put(e.getCurrencyCode(), " best selling rate : " 
						+ getAscendingSortedBuyingRates(e.getCurrencyCode()).values()
							.stream().filter(d -> d > 0).findFirst().orElse(0.0) + " by " 
						+ getAscendingSortedBuyingRates(e.getCurrencyCode()).keySet().stream().findFirst().orElse("no buying rate")
						+ " , best buying rate : " 
						+ getDescendingSortedSellingRates(e.getCurrencyCode()).values()
					        .stream().filter(z -> z > 0).findFirst().orElse(0.0) + " by " 
					    + getDescendingSortedSellingRates(e.getCurrencyCode()).keySet().stream().findFirst().orElse("no selling rate")
					    + ".")));
		return report;
	}
	
	private Map<String, Double> ascendingSort(Map<String, Double> map){
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,	LinkedHashMap::new));
	}
	
	private Map<String, Double> descendingSort(Map<String, Double> map){
		return map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(
		        		Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}

}

