package pl.java.ee.quicksort.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.java.ee.quicksort.service.ServiceException;
import pl.java.ee.quicksort.service.SortService;

/**
 * Controller class which provides REST communication between server and javascript client.
 * @author Wojciech Bedkowski
 * @version 1.0
 */
@Path("/rest")
public class RestQSController {
	
	/**
	 * Service object required for sorting operation.
	 */
	@Inject
	SortService sortService;
	
	/**
	 * Controller method which parse request json object, sort data inside, and sends list back.
	 * @param data Request string with request data.
	 * @return Response class with status code and repsonse json.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sortStrings(String data){
		ObjectMapper objectMapper = new ObjectMapper();
		String responseData;
		try {
			StringDataContainer requestData = objectMapper.readValue(data, StringDataContainer.class); //map input data as json object
			
			if(requestData.getData()==null){
				return Response.ok(requestData).build();
			}
			
			if(requestData.getIsInteger()){ //check if requested values are integers
				ArrayList<Integer> integerData = new ArrayList<Integer>();
				
				for(String i : requestData.getData()){ //parse strings to integers
					if((i=="")||i==null){ //if there is an empty line skip it
						continue;
					}
					integerData.add(Integer.parseInt(i));
				}
				
				sortService.sort((List)integerData); //sort data
				
				requestData.getData().clear(); //clear request list and place sorted data
				
				for(Integer i : integerData){
					requestData.getData().add(Integer.toString(i));
				}
				
				
			} else { //if data contains
				for(int i = 0; i < requestData.getData().size(); i++){ //remove empty lines from collection
					if(requestData.getData().get(i) == ""){
						requestData.getData().remove(i);
						i--;
					}
				}
				sortService.sort((List)requestData.getData());
			}
			responseData = objectMapper.writeValueAsString(requestData);
			return Response.ok(responseData).build(); //send json with sorted values if everything is ok
			
		} catch (JsonParseException | JsonMappingException e) {
			return Response.status(Status.BAD_REQUEST).build(); //send 400 error if incorrect Json requested
		} catch (NumberFormatException e){
			return Response.status(Status.BAD_REQUEST).build(); //send 400 error if incorrect data requested to sort
		} catch(ServiceException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (IOException e){
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); //send 500 error if some internal exception occured
		}
	}
}
