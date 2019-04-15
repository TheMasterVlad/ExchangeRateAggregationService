package banks;

import java.io.IOException;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import banks.BankService;
import banks.fileparsers.*;

@Controller
public class FileUploadController {
	private final BankService bankService;
	
	@Autowired
	public FileUploadController(BankService bankService) {
		this.bankService = bankService;
	}
	
	@GetMapping("/")
    public String startpage() throws IOException {
        return "uploadForm";
    }
	
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String filename = FilenameUtils.removeExtension(file.getOriginalFilename());
		Parserable parser = Parserable.getParserFrom(file.getContentType());
		if (parser != null) {
			Bank bank = parser.parse(file);
				if (bank != null) {
					bankService.addBank(bank);
					redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + filename + " data to the system.");
				} else {
					redirectAttributes.addFlashAttribute("message", "Could not upload " + filename + " data. Wrong data format.");
				}
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Files of type " + file.getContentType() + " are not allowed for upload!");
		}
		
		return "redirect:/";
	}
	
}
