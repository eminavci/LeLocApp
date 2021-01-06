package com.webproject.rest.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.webproject.core.common.api.Util;
import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Country;
import com.webproject.core.models.entities.Region;
import com.webproject.core.models.entities.Town;
import com.webproject.core.services.CityService;
import com.webproject.core.services.CountryService;
import com.webproject.core.services.RegionService;
import com.webproject.core.services.TownService;
import com.webproject.core.services.exceptions.PlaceException;

@Controller
public class TestController {

	
	@Autowired
	CountryService countryService;
	
	@Autowired
	RegionService regionService;
	
	@Autowired
	CityService cityService;
	@Autowired
	TownService townService;
	
	@RequestMapping(value = "/testim", method = RequestMethod.GET)
	public ResponseEntity<String> parseCities() throws InvalidFormatException, FileNotFoundException, IOException {
		
		//File[] fss = Util.filesFinder("/home/emin/Personal/M.Sc.Machine_Learning&Data_Mining/semester_1/programming_web/project/openaddr-collected-europe/fr", ".xlsx");
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("/home/emin/devs/emin_france.xlsx")));
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        List<Region> list = new ArrayList<>();
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	i++;
        	if(i == 1 )
        		continue;
        	
    		Cell cRegion = row.getCell(7);
    		Cell cCityPostCode = row.getCell(8);
    		Cell cCityName = row.getCell(9);
    		
    		if(cRegion == null || cCityName == null || cCityPostCode == null)
    			continue;
    		
    		String region = cRegion.toString().trim(), postCode = cCityPostCode.toString().trim(), city = cCityName.toString().trim();
    		postCode = postCode.indexOf(".") == -1 ? postCode : postCode.substring(0, postCode.indexOf("."));
    		//System.out.println(region + String.format("%" + (30 - region.length()) + "s", "") + postCode + String.format("%" + (36 - postCode.length()) + "s", "") + city);
        
    		if(region.equalsIgnoreCase("Île-de-France"))
    			region = "Ile-de-France";
 
    		
    		City cty = new City();
            cty.setCode(postCode);
            cty.setName(city);
            
            boolean found = false; int found2 = -1;;
            for (int j = 0; j < list.size(); j++) {
				if(list.get(j).getName().equalsIgnoreCase(region)){
					
					for (City c : list.get(j).getCities()) {
						if(c.getName().equalsIgnoreCase(cty.getName()) && c.getCode().equals(cty.getCode())){
							found2 = -1;
							break;
						} else {
							found2 = j;
						}
					}
					found = true;
					break;
				} else {
					found = false;
				}
			}
            if(!found){
            	Region r = new Region();
            	r.setName(region);
            	cty.fname = Util.convertNonAscii(cty.getName()) + ".csv";
				r.getCities().add(cty);
				list.add(r);
            } else {
            	if(found2 != -1){
            		cty.fname = Util.convertNonAscii(cty.getName()) + ".csv";
            		list.get(found2).getCities().add(cty);
            	}
            }
        }
        
        Collections.sort(list, new Comparator<Region>() {
			@Override
			public int compare(Region o1, Region o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
        
        for (Region region : list) {
        	List<City> cc = region.getCities();
        	Collections.sort(cc, new Comparator<City>() {

				@Override
				public int compare(City o1, City o2) {
					if(!(Util.isInteger(o1.getCode()) || Util.isInteger(o2.getCode())))
						return -1;
					if(Integer.valueOf(o1.getCode()) > Integer.valueOf(o2.getCode()))
						return 1;
					return -1;
				}
			});
        }
       
        
		savetoDB(list);
		return new ResponseEntity<String>("fgfdg", HttpStatus.OK);
	}
	
	private void savetoDB(List<Region> list) throws InvalidFormatException, FileNotFoundException, IOException{
		File[] fss = Util.filesFinder("/home/emin/devs/frlocs/txtes", ".txt");
		Country c = countryService.findById((long) 76);
		
		if(c == null)
			throw new PlaceException(5);

		//int ri = 0, ci = 0;
		
		 for (Region region : list) {
			//if(ri == 1) break;
			 region.setCountry(c);
				for (City cty : region.getCities()) {
					boolean bb = false;
					for (File file : fss) {
						if(!FilenameUtils.getBaseName(file.getName()).equals(cty.fname))
							bb = false;
						else {
							bb = true;
							break;
						}
					}
					if(bb){//cty.fname
						//if(ci == 1) break;
						cty.setRegion(region);
						
						List<Town> towns = parseSub2(cty.fname+".txt");
						cty.setTowns(towns);
						for (Town town : cty.getTowns()) {
							town.setCity(cty);
							townService.save(town);
						}
						//ci++;
					}
				}
				//ri++;
			}
		
		
//		for (Region region : list) {
//			region.setCountry(c);
//			for (City city : region.getCities()) {
//				city.setRegion(region);
//				for (Town town : city.getTowns()) {
//					town.setCity(city);
//					for (Street street : town.getStreets()) {
//						street.setTown(town);
//						streetService.create(street);
//					}
//				}
//			}
//		}
	}
		
		
		private List<Town> parseSub2(String dosya) throws IOException {
		String ppath = "/home/emin/devs/frlocs/txtes/";
		FileInputStream fis = new FileInputStream(new File(ppath+dosya));
		 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 List<Town> list = new ArrayList<Town>();
		String line = null;
		int i = 0;
		while ((line = br.readLine()) != null) {
			i++;
			if(i==1) continue;
			
			Town town = new Town();
			try {
				if(line.split("\\*")[0].trim().length()==0 || line.split("\\*")[1].trim().length()==0)
					continue;
				town.setName(line.split("\\*")[0].trim());
				town.setPostCode(line.split("\\*")[1].trim());
			} catch (Exception e) {
				continue;
			}
			list.add(town);
			
		}
	 
		br.close();
			
		return list;
	}

		private List<Town> parseSub(String fileN) throws InvalidFormatException, FileNotFoundException, IOException{
		System.out.println("CALLEDDDD : " + fileN);
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("/home/emin/Personal/M.Sc.Machine_Learning&Data_Mining/semester_1/programming_web/project/openaddr-collected-europe/fr/"+fileN)));
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        List<Town> list = new ArrayList<>();
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	i++;
        	if(i == 1 )
        		continue;
        	
        	Cell cTown = row.getCell(5);
        	Cell cStreet = row.getCell(3);
        	
        	Cell cLongitude = row.getCell(0);
        	Cell cLatitude = row.getCell(1);
        	Cell cNumber = row.getCell(2);
        	Cell cPostCode = row.getCell(8);
        	
        	if(cTown == null || cStreet == null || cLongitude == null || cLatitude == null || cNumber == null || cPostCode == null)
        		continue;
        	
        	String town;
			String street;

			String postCode;
			try {
				town = cTown.toString().trim();
				street = cStreet.toString().trim(); if(street.isEmpty()) continue;
				postCode = cPostCode.toString().indexOf(".") == -1 ? cPostCode.toString() : cPostCode.toString().substring(0, cPostCode.toString().indexOf("."));
			} catch (NumberFormatException e) {
				continue;
			}
        	
        	boolean found = false; int found2 = -1;
        	for (int j = 0; j < list.size(); j++) {
				if(list.get(j).getName().equals(town) && list.get(j).getPostCode().equals(postCode)){
					found  = true;
					break;
				} else {
					found = false;
				}
			}
        	
        	if(!found){
        		Town twn = new Town();
        		twn.setName(town);
        		twn.setPostCode(postCode);
        		list.add(twn);
        	} 
        	//System.out.println(town + " " + street + " " + longitude  + " " + latitude + " " + number + " " + postCode);
        }
        
        Runtime.getRuntime().gc();
        return list;
        //print2(list);
	}

	@RequestMapping(value = "/getcords", method = RequestMethod.GET)
	public ResponseEntity<String> getCords() throws JSONException{
		List<Long> longs = new ArrayList<>();
		longs.add((long)40);
		longs.add((long)41);
		longs.add((long)42);
		longs.add((long)43);
		longs.add((long)44);
		longs.add((long)45);
		longs.add((long)46);
		longs.add((long)47);
		List<City> cities = cityService.retrieveAllById(longs);
		for (City city : cities) {
			Region region = city.getRegion();
			String lookingFor = city.getName() + ", " + region.getName() + ", France";
			JSONObject json = getAppr(lookingFor);
			if(json == null) continue;
			try {
				double latitude = json.getJSONObject("location").getDouble("x");
				double longitude = json.getJSONObject("location").getDouble("y");
				
				double minx = json.getJSONObject("extent").getDouble("xmin");
				double miny = json.getJSONObject("extent").getDouble("ymin");
				double maxx = json.getJSONObject("extent").getDouble("xmax");
				double maxy = json.getJSONObject("extent").getDouble("ymax");
				
				city.setLatitudeMax(maxx);
				city.setLongitudeMax(maxy);
				city.setLatitudeMin(minx);
				city.setLongitudeMin(miny);
				city.setLatitude(latitude);
				city.setLongitude(longitude);
				
				cityService.save(city);
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return new ResponseEntity<String>("fgfdg", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gettowncords", method = RequestMethod.GET)
	public ResponseEntity<String> getCordsTown() throws JSONException{
		
		List<Town> towns = townService.getOthers();
		for (Town town : towns) {
			City city = town.getCity();
			Region region = city.getRegion();
			
			String lookingFor = town.getName() + ", " + city.getName() + ", " + region.getName() + ", France";
			JSONObject json = getAppr(lookingFor);
			if(json == null) continue;
			try {
				double latitude = json.getJSONObject("location").getDouble("x");
				double longitude = json.getJSONObject("location").getDouble("y");
				
				double minx = json.getJSONObject("extent").getDouble("xmin");
				double miny = json.getJSONObject("extent").getDouble("ymin");
				double maxx = json.getJSONObject("extent").getDouble("xmax");
				double maxy = json.getJSONObject("extent").getDouble("ymax");
				
				town.setLatitude(latitude);
				town.setLongitude(longitude);
				town.setLatitudeMin(minx);
				town.setLongitudeMin(miny);
				town.setLatitudeMax(maxx);
				town.setLongitudeMax(maxy);
				
				townService.save(town);
				
			} catch (Exception e) {
				System.out.println("Patladı : " + e.toString());
			}
		}
		
		return new ResponseEntity<String>("fgfdg", HttpStatus.OK);
	}
	
	private JSONObject getAppr(String pars){
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String link = "http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/findAddressCandidates?singleLine=" + pars + "&outFields=addr_type&f=pjson";
			ResponseEntity<String> response = restTemplate.getForEntity(URLDecoder.decode(link, "UTF-8"), String.class);

			JSONObject json = new JSONObject(response.getBody());
			JSONArray jarr = json.getJSONArray("candidates");
			
			JSONObject secilen = jarr.getJSONObject(0);
			int ilkScore = StringUtils.getLevenshteinDistance(pars, secilen.getString("address"));
			if(ilkScore == 0)
				return secilen;
			for (int i = 1; i < jarr.length(); i++) {
				JSONObject karsi = jarr.getJSONObject(i);
				int secScore = StringUtils.getLevenshteinDistance(pars, karsi.getString("address"));
				if(secScore < ilkScore){
					ilkScore = secScore;
					secilen = karsi;
				}
			}
			return secilen;
		} catch (Exception e) {
			System.out.println("HATAAA : " + e.toString());
			return null;
		}
	}
	
	@RequestMapping(value = "/testim2222", method = RequestMethod.GET)
	public ResponseEntity<Country> deneme3() {
		
		Country c = new Country();
		c.setContinentName("gdfgd");
		c.setCurrencyCode("TRY");
		c.setIsoCode("TRY");
		c.setName("Hleo world");
		c.setPhoneCode("90");
		c.setTimeZone("fgfdg");
		c.setLocaleName("TR");
	
		return new ResponseEntity<Country>(c, HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/deneme", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> deneme() throws JSONException{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity( "http://restcountries.eu/rest/v1/all", String.class);
		
		List<Country> ccc = new ArrayList<>();
		
		JSONArray json = new JSONArray(response.getBody());
		//JSONObject js = new JSONObject(response.toString());
		for(int i = 0; i < json.length(); i++){
			Country c =  new Country();
			
			JSONObject jobj = json.getJSONObject(i);
			
			c.setContinentName(jobj.getString("region"));
			c.setCurrencyCode(jobj.getJSONArray("currencies").getString(0));
			c.setIsoCode(jobj.getString("alpha2Code"));
			try {
				c.setLocaleName(jobj.getJSONArray("languages").getString(0).toUpperCase());
			} catch (Exception e1) {
				try {
					c.setLocaleName(jobj.getString("languages").toUpperCase());
				} catch (Exception e) {
					c.setLocaleName("EN");
				}
			}
			c.setName(jobj.getString("nativeName"));
			c.setPhoneCode(jobj.getJSONArray("callingCodes").getString(0));
			try {
				c.setTimeZone(jobj.getJSONArray("timezones").getString(0));
			} catch (Exception e) {
				try {
					c.setTimeZone(jobj.getString("timezones"));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			
			Map<String, String> map = new HashMap<>();
			map.put("en", jobj.getString("name"));
			map.put("fr", jobj.getJSONObject("translations").getString("fr"));
			map.put("de", jobj.getJSONObject("translations").getString("de"));
			map.put("es", jobj.getJSONObject("translations").getString("es"));
			map.put("it", jobj.getJSONObject("translations").getString("it"));
			
			c.setTranslation(map);
			
			ccc.add(countryService.save(c));
		}
		
		System.out.println("dddddddddddddd*************************************************");
		
		return new ResponseEntity<List<Country>>(ccc, HttpStatus.OK);
	}
	

	
}
