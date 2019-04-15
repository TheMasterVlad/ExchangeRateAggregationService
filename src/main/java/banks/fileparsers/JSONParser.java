package banks.fileparsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import banks.Bank;
import banks.ExchangeRate;

public class JSONParser implements Parserable {

	@Override
	public Bank parse(MultipartFile file) {
		String filename = null;
		List<ExchangeRate> exchangerate = null;
		try {
			filename = FilenameUtils.removeExtension(file.getOriginalFilename());
			String jsondata = IOUtils.toString(new   ByteArrayInputStream(file.getBytes()), "UTF-8");
			
			ObjectMapper mapper = new ObjectMapper();
			exchangerate = mapper.readValue(jsondata, mapper.getTypeFactory()
					.constructCollectionType(List.class, ExchangeRate.class));
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(exchangerate == null || exchangerate.isEmpty() || filename.isEmpty()) 
			return null;
		else
			return new Bank(filename, exchangerate);
	
	}

}
