package banks;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/banks")
	public List<Bank> banks() {			
		return bankService.getAllBanks();
	}
	
	@GetMapping("selling/{currency}")
	public Map<String, Double> getSellingRate(@PathVariable("currency") String currency) {	
		return bankService.getAllSellingRates(currency);
	}
	
	@GetMapping("selling/{currency}/ascendingsort")
	public Map<String, Double> getAscSortedSellingRate(@PathVariable("currency") String currency) {	
		return bankService.getAscendingSortedSellingRates(currency);
	}
	
	@GetMapping("selling/{currency}/descendingsort")
	public Map<String, Double> getDesSortedSellingRate(@PathVariable("currency") String currency) {	
		return bankService.getDescendingSortedSellingRates(currency);
	}
	
	@GetMapping("buying/{currency}")
	public Map<String, Double> getBuyingRate(@PathVariable("currency") String currency) {	
		return bankService.getAllBuyingRates(currency);
	}
	
	@GetMapping("buying/{currency}/ascendingsort")
	public Map<String, Double> getAscSortedBuyingRate(@PathVariable("currency") String currency) {	
		return bankService.getAscendingSortedBuyingRates(currency);
	}
	
	@GetMapping("buying/{currency}/descendingsort")
	public Map<String, Double> getDesSortedBuyingRate(@PathVariable("currency") String currency) {	
		return bankService.getDescendingSortedBuyingRates(currency);
	}
	
	@RequestMapping("report")
	public Map<String, String> getReport() {	
		return bankService.bestSellingsAndBuyings();
	}
	
	@PostMapping("/banks")
	public void addBank(@RequestBody Bank bank) {	
		bankService.addBank(bank);
	}
	
	@PutMapping("/banks/{bank}/selling/{currency}")
	public void updateSellingRate(@PathVariable("bank") String bankname, 
			@PathVariable("currency")String currency, @RequestParam("value") Double value) {	
		bankService.updateBankSellingRate(bankname, currency, value);
	}
	
	@PutMapping("/banks/{bank}/buying/{currency}")
	public void updateBuyingRate(@PathVariable("bank") String bankname, 
			@PathVariable("currency")String currency, @RequestParam("value") Double value) {	
		bankService.updateBankBuyingRate(bankname, currency, value);
	}
	
	@DeleteMapping("/banks/{bank}/{currency}")
	public void deleteBank(@PathVariable("bank") String bankname, 
			@PathVariable("currency")String currency) {	
		bankService.deleteBankCurrency(bankname, currency);
	}

	
}
