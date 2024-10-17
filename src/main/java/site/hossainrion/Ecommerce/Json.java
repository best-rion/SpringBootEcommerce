package site.hossainrion.Ecommerce;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Json
{
	public static String jsonToString(String fileName) throws IOException
	{
		File jsonFile = ResourceUtils.getFile("classpath:"+fileName);
		String jsonToString = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
		
		return jsonToString;
	}
	
	public static Product[] getProducts(String jsonContent) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper objMapper = new ObjectMapper();
		
		objMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		
		Product[] products = objMapper.readValue(jsonContent, Product[].class);
		
		return products;
	}
}