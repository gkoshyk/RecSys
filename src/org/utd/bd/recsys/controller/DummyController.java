package org.utd.bd.recsys.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.utd.bd.recsys.bean.Artist;
import org.utd.bd.recsys.bean.ArtistLocn;
import org.utd.bd.recsys.bean.SimilarTrack;
import org.utd.bd.recsys.bean.Tag;
import org.utd.bd.recsys.bean.Track;
import org.utd.bd.recsys.bean.Year;
import org.utd.bd.recsys.bean.response.StandardResponse;
import org.utd.bd.recsys.service.RestClient;


@Controller
public class DummyController {

	private static int PAGE_SIZE = 5;
	
	private static StandardResponse<ArtistLocn> locationCountMapResponse=new StandardResponse<>();
	private static Map<String,Integer> artistTrackCountMap=new HashMap<String, Integer>();
	private static StandardResponse<ArtistLocn> tagMapResponse=new StandardResponse<>();
	
	static{
		String locationquery="MATCH (n:Artist) RETURN distinct n.location as loc , count(*)as count  order by count desc LIMIT 50";
		String artistCountquery="MATCH (n:Track) RETURN distinct n.artistName as name, count(*) ";
		String tagCountquery="MATCH (a:Artist)-[r:ATAGGED_AS]->(t:Tag) RETURN t.id, count(r) as cnt order by cnt desc  LIMIT 50";
		System.out.println("Building static Maps");
		List<Map> locationDataList=RestClient.sendTransactionalCypherQuery(locationquery);
		List<Map> artistDataList=RestClient.sendTransactionalCypherQuery(artistCountquery);
		List<Map> tagDataList=RestClient.sendTransactionalCypherQuery(tagCountquery);
		
		ObjectMapper mapper=new ObjectMapper();
		ArtistLocn artistLoc =null;
		List artistLocList=new ArrayList<>();
		List tagList=new ArrayList<>();
		
		try{
		if(locationDataList!=null && locationDataList.size()>0)
		{
			
			for(Map row:locationDataList)
			{
				List rowList=(List)row.get("row");
				if(((String)rowList.get(0)).equalsIgnoreCase("NULL"))
					continue;
				artistLoc=new ArtistLocn((String)rowList.get(0),(Integer)rowList.get(1));
				artistLocList.add(artistLoc);
			}
			
			locationCountMapResponse.setPageNo(1);
			locationCountMapResponse.setTotalPages(1);
			locationCountMapResponse.setPgStartRecord(1);
			locationCountMapResponse.setPgEndRecord(artistLocList.size());
			locationCountMapResponse.setTotalRecords(artistLocList.size());
			locationCountMapResponse.setData(artistLocList);
			
		}
		System.out.println("<--------------Done building locationCountMapResponse---------->");
		
		if(tagDataList!=null && tagDataList.size()>0)
		{
			
			for(Map row:tagDataList)
			{
				List rowList=(List)row.get("row");
				artistLoc=new ArtistLocn((String)rowList.get(0),(Integer)rowList.get(1));
				tagList.add(artistLoc);
			}
			
			tagMapResponse.setPageNo(1);
			tagMapResponse.setTotalPages(1);
			tagMapResponse.setPgStartRecord(1);
			tagMapResponse.setPgEndRecord(tagList.size());
			tagMapResponse.setTotalRecords(tagList.size());
			tagMapResponse.setData(tagList);
			
		}
		System.out.println("<--------------Done building tagMapResponse---------->");
		
		if(artistDataList!=null && artistDataList.size()>0)
		{
			
			for(Map row:artistDataList)
			{
				List rowList=(List)row.get("row");
				if(((String)rowList.get(0)).equalsIgnoreCase("NULL"))
					continue;
				artistTrackCountMap.put((String)rowList.get(0),(Integer)rowList.get(1));
				
			}
			
			
		}
		System.out.println("<--------------Done building artistTrackCountMap---------->");
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/artist/{id}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse getLocationCount() {
		//MATCH (n:Artist) RETURN distinct n.location as loc , count(*)as count  order by count desc LIMIT 50
		//MATCH (n:Artist)where n.location='California - LA' RETURN n LIMIT 50
		//MATCH (n:Track) where n.artistName='Egg' RETURN n LIMIT 25
		//MATCH (t1:Track)-[r:SOUNDS_SIMILAR]-(t2:Track)WHERE t1.trackId='TRAAAAW128F429D538' RETURN t2,r order by r.degree desc LIMIT 25
		
		
		//MATCH (a:Artist)-[r:ATAGGED_AS]->(t:Tag) RETURN t, count(r) as cnt order by cnt desc  LIMIT 25 -- static load
		//MATCH (a:Artist)-[r:ATAGGED_AS]->(t:Tag) where t.id='rock' RETURN a  LIMIT 50
		//MATCH (n:Track) where n.artistName='Egg' RETURN n LIMIT 25
		//MATCH (t1:Track)-[r:SOUNDS_SIMILAR]-(t2:Track)WHERE t1.trackId='TRAAAAW128F429D538' RETURN t2,r order by r.degree desc LIMIT 25
				
		// artist - count - static
		//MATCH (n:Track) RETURN distinct n.artistName as name, count(*) LIMIT 25
		
		//MATCH ()-[r:ATAGGED_AS]->() RETURN r LIMIT 25
		/*Artist a = artistDS.get(id);
		return a;*/
		String query="MATCH (n:Artist) RETURN distinct n.location as loc , count(*)as count  order by count desc LIMIT 50";
		List<Map> dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		ArtistLocn artistLoc =null;
		List artistLocList=new ArrayList<>();
		StandardResponse<ArtistLocn> response = new StandardResponse<ArtistLocn>();
		
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			for(Map row:dataList)
			{
				List rowList=(List)row.get("row");
				if(((String)rowList.get(0)).equalsIgnoreCase("NULL"))
					continue;
				artistLoc=new ArtistLocn((String)rowList.get(0),(Integer)rowList.get(1));
				artistLocList.add(artistLoc);
			}
			
			response.setPageNo(1);
			response.setTotalPages(1);
			response.setPgStartRecord(1);
			response.setPgEndRecord(artistLocList.size());
			response.setTotalRecords(artistLocList.size());
			response.setData(artistLocList);
			
		}
		} catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/track/{id}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	Track getTrack(@PathVariable String id) {
		/*Track t = trackDS.get(id);
		return t;*/
		String query="MATCH (n:Track) WHERE n.trackId='"+id+"' RETURN n";
		List dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		Track track =null;
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			Map trackMap=(Map)(((List)((Map)(dataList).get(0)).get("row")).get(0));
			String trackMapstr=mapper.writeValueAsString(trackMap);
			track= mapper.readValue(trackMapstr,Track.class);
		
			
		}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return track;
		
		
	}
	
	
	//START--------LOCATION SEARCH--

	@RequestMapping(method = RequestMethod.GET, value = "/locations", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse<ArtistLocn> getAllLocations() {
		
		return locationCountMapResponse;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/artist/locn/{id}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse getArtistsForLocation(@PathVariable String id) {
		/*Track t = trackDS.get(id);
		return t;*/
		String query="MATCH (n:Artist)where n.location='"+id+"' RETURN n.name LIMIT 50";
		List<Map> dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		ArtistLocn artistLoc =null;
		List artistLocList=new ArrayList<>();
		StandardResponse<ArtistLocn> response = new StandardResponse<ArtistLocn>();
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			for(Map row:dataList)
			{
				List rowList=(List)row.get("row");
				if(((String)rowList.get(0)).equalsIgnoreCase("NULL"))
					continue;
				artistLoc=new ArtistLocn((String)rowList.get(0),artistTrackCountMap.get((String)rowList.get(0)));
				artistLocList.add(artistLoc);
			}
			
			response.setPageNo(1);
			response.setTotalPages(1);
			response.setPgStartRecord(1);
			response.setPgEndRecord(artistLocList.size());
			response.setTotalRecords(artistLocList.size());
			response.setData(artistLocList);
			
		}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tracks/artist/{id}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse getTracksForArtist(@PathVariable String id) {
		/*Track t = trackDS.get(id);
		return t;*/
		String query="MATCH (n:Track) where n.artistName='"+id+"' RETURN n LIMIT 25";
		List<Map> dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		Track track =null;
		List trackList=new ArrayList<>();
		StandardResponse<ArtistLocn> response = new StandardResponse<ArtistLocn>();
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			for(Map row:dataList)
			{
				List rowList=(List)row.get("row");
				Map dataMap=(Map)rowList.get(0);
				track=new Track();
				track.setArtistName((String)dataMap.get("artistName"));
				track.setSongId((String)dataMap.get("songId"));
				track.setTitle((String)dataMap.get("title"));
				track.setTrackId((String)dataMap.get("trackId"));
				
				trackList.add(track);
			}
			
			response.setPageNo(1);
			response.setTotalPages(1);
			response.setPgStartRecord(1);
			response.setPgEndRecord(trackList.size());
			response.setTotalRecords(trackList.size());
			response.setData(trackList);
			
		}
		
		
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/similarTrack/{id}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse getSimilarTracks(@PathVariable String id) {
		/*Track t = trackDS.get(id);
		return t;*/
		String query="MATCH (t1:Track)-[r:SOUNDS_SIMILAR]-(t2:Track)WHERE t1.trackId='"+id+"' " +
				"RETURN distinct t2,r order by r.degree desc LIMIT 25";
		List<Map> dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		Track track =null;
		List trackList=new ArrayList<>();
		StandardResponse<ArtistLocn> response = new StandardResponse<ArtistLocn>();
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			for(Map row:dataList)
			{
				List rowList=(List)row.get("row");
				Map dataMap=(Map)rowList.get(0);
				track=new Track();
				track.setArtistName((String)dataMap.get("artistName"));
				track.setSongId((String)dataMap.get("songId"));
				track.setTitle((String)dataMap.get("title"));
				track.setTrackId((String)dataMap.get("trackId"));
				
				trackList.add(track);
			}
			
			response.setPageNo(1);
			response.setTotalPages(1);
			response.setPgStartRecord(1);
			response.setPgEndRecord(trackList.size());
			response.setTotalRecords(trackList.size());
			response.setData(trackList);
			
		}
		
		
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
		
		
	}
	
	// TAG SEARCH--------
	
	@RequestMapping(method = RequestMethod.GET, value = "/tags", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse<ArtistLocn> getAllTags() {
		
		return tagMapResponse;
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/artists/tag/{tagName}", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse<Artist> getArtistByTag(@PathVariable String tagName
			) {
		String query="MATCH (a:Artist)-[r:ATAGGED_AS]->(t:Tag) where t.id='"+tagName+"' RETURN a  LIMIT 50";
		List<Map> dataList=RestClient.sendTransactionalCypherQuery(query);
		ObjectMapper mapper=new ObjectMapper();
		Artist artist =null;
		List artistList=new ArrayList<>();
		StandardResponse<Artist> response = new StandardResponse<Artist>();
		try{
		if(dataList!=null && dataList.size()>0)
		{
			
			for(Map row:dataList)
			{
				List rowList=(List)row.get("row");
				Map dataMap=(Map)rowList.get(0);
				artist=new Artist();
				artist.setName((String)dataMap.get("name"));
				
				artistList.add(artist);
			}
			
			response.setPageNo(1);
			response.setTotalPages(1);
			response.setPgStartRecord(1);
			response.setPgEndRecord(artistList.size());
			response.setTotalRecords(artistList.size());
			response.setData(artistList);
			
		}
		
		
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return response;
		}
	
	@RequestMapping(method = RequestMethod.GET, value = "/artists/Fb", headers = "Accept=application/xml, application/json")
	public @ResponseBody
	StandardResponse<ArtistLocn> checkArtistFromFB(@RequestParam("artistList") String artistListStr) {
		StandardResponse<ArtistLocn> artistCountMapResponse = new StandardResponse<ArtistLocn>();
		ObjectMapper mapper = new  ObjectMapper();
		List artistLocList=new ArrayList<>();
		ArtistLocn artistLocn;
		try {
			ArrayList artistList=mapper.readValue(artistListStr,java.util.ArrayList.class);
			Iterator itr=artistList.iterator();
			
			while(itr.hasNext())
			{
				Map artistMap=(Map)itr.next();
				String name=(String)artistMap.get("name");
				if(artistTrackCountMap.containsKey(name))
				{
					artistLocn=new ArtistLocn(name, artistTrackCountMap.get(name));
					artistLocList.add(artistLocn);
				}
				
				
			}
			
			artistCountMapResponse.setPageNo(1);
			artistCountMapResponse.setTotalPages(1);
			artistCountMapResponse.setPgStartRecord(1);
			artistCountMapResponse.setPgEndRecord(artistLocList.size());
			artistCountMapResponse.setTotalRecords(artistLocList.size());
			artistCountMapResponse.setData(artistLocList);
		
			return artistCountMapResponse;
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
