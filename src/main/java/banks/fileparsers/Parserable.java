package banks.fileparsers;

import org.springframework.web.multipart.MultipartFile;

import banks.Bank;

public interface Parserable {
	
	public Bank parse(MultipartFile file);
	
	public static Parserable getParserFrom(String contentType) {
		switch(contentType) {
		case  "text/xml" :
			return new XMLParser();
		case  "application/json" :
			return new JSONParser();
		case  "application/vnd.ms-excel" :
			return new CSVParser();
		default :
			return null;
		}
	}
}
