package org.lite;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ContractDecoderEntity {
	
	public ContractDecoderEntity(String jsonstr) throws IOException
	{
		this.decode(jsonstr);
	}
	
	public static class DecoderEntity
	{
		public String name;
		public String type;
		public String data;
		public int dataAsInt()
		{
			return Integer.valueOf(data);
		}
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("name:"+name).append(",type="+type).append(",data:"+data);
			if(type.contains("int")) {
				sb.append(",intValue:"+dataAsInt());
			}
			return sb.toString();
		}
	}

	ArrayList<DecoderEntity> entity_list = new ArrayList<DecoderEntity>();
	public String listToString()
	{
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<entity_list.size();i++)
		{
			sb.append(i).append(":").append(entity_list.get(i).toString()).append("\n");
		}
		return sb.toString();
	}
	
    public void decode(String jsonstr) throws IOException
    {
    	ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonstr); // 读取Json
        int index = 0;
        
        while(rootNode.has(index) ) 
        {
        	DecoderEntity de = new DecoderEntity();
        	try
        	{
        		de.name = rootNode.get(index).get("name").asText();
        	}catch(Exception e)
        	{
        		de.name="";
        	}
        	de.type = rootNode.get(index).get("type").asText();
        	de.data = rootNode.get(index).get("data").asText();
        	entity_list.add(de);
        	index++;
        }
    }
	
}
