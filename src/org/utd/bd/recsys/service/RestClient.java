package org.utd.bd.recsys.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.utd.bd.recsys.bean.Track;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;


public class RestClient {

	private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";
    private final static String txUri = SERVER_ROOT_URI + "transaction/commit";
    
	 public static List sendTransactionalCypherQuery(String query) {
	        // START SNIPPET: queryAllNodes
	        WebResource resource = Client.create().resource( txUri );
	        
	        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
	        ClientResponse response = resource
	                .accept( MediaType.APPLICATION_JSON )
	                .type( MediaType.APPLICATION_JSON )
	                .entity( payload )
	                .post( ClientResponse.class );
	        ObjectMapper mapper=new ObjectMapper();
	        List dataList=null;
	        try {
				Map resultMap= mapper.readValue(response.getEntity(String.class),HashMap.class);
				dataList=((List)((Map)((List)resultMap.get("results")).get(0)).get("data"));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientHandlerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UniformInterfaceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        response.close();
	        // END SNIPPET: queryAllNodes
	        return dataList;
	    }

}
