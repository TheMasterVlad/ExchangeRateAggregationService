package banks.fileparsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import banks.Bank;
import banks.ExchangeRate;

public class XMLParser implements Parserable {

	@Override
	public Bank parse(MultipartFile file) {
		String filename = null;
		List<ExchangeRate> exchangerate = null;
		
		try {
			
			filename = FilenameUtils.removeExtension(file.getOriginalFilename());
			String xmldata = IOUtils.toString(new ByteArrayInputStream(file.getBytes()), "UTF-8");
			XmlMapper xmlMapper = new XmlMapper();
			
			MappingIterator<ExchangeRate> mappingIterator = xmlMapper.readerFor(ExchangeRate.class).readValues(xmldata);
			exchangerate = new ArrayList<>();
	        while (mappingIterator.hasNext()) {
	        	exchangerate.add(mappingIterator.next());
	        }
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(exchangerate == null || exchangerate.isEmpty() || filename.isEmpty()) 
			return null;
		else
			return new Bank(filename, exchangerate);
		
		
	}

}
