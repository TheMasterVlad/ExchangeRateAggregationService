package banks.fileparsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.ArrayList;

import banks.Bank;
import banks.ExchangeRate;

public class CSVParser implements Parserable {

	@Override
	public Bank parse(MultipartFile file) {
		String filename = null;
		List<ExchangeRate> exchangerate = null;
		
		try {
			filename = FilenameUtils.removeExtension(file.getOriginalFilename());
			String csvdata = IOUtils.toString(new   ByteArrayInputStream(file.getBytes()), "UTF-8");
			exchangerate = (List) deserialize(csvdata, ExchangeRate.class, true);
         
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

	private List<Object> deserialize(final String csv, final Class type, final Boolean hasHeaders) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema;
        if (hasHeaders) {
            csvSchema = csvMapper.schemaFor(type).withHeader();
        } else {
            csvSchema = csvMapper.schemaFor(type).withoutHeader();
        }
        MappingIterator<Object> mappingIterator = csvMapper.readerFor(type).with(csvSchema).readValues(csv);
        List<Object> objects = new ArrayList<>();
        while (mappingIterator.hasNext()) {
            objects.add(mappingIterator.next());
        }
        return objects;
    }
	
}
