package ressources;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("logements")
public class LogementRessources {
    public static LogementBusiness logementMetier = new LogementBusiness();

    @POST
    @Consumes("application/xml")
    public Response addLogement(Logement l) {
     if(logementMetier.addLogement(l))
         return  Response.status(Status.CREATED).build();
     return  Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Produces("application/json")
    public Response getLogements(@QueryParam("delegation") String delegation, @QueryParam("reference") String reference) {
        List<Logement> liste=new ArrayList<Logement>();
        if(reference == null && delegation != null) {
            liste = logementMetier.getLogementsByDeleguation(delegation);
        } else if(delegation == null && reference !=null ) {
           liste =logementMetier.getLogementsListeByref(Integer.parseInt(reference));
        } else {
            liste = logementMetier.getLogements() ;
        }
           if(liste.size()==0)
               return  Response.status(Status.NOT_FOUND).build();
        return  Response.status(Status.OK).entity(liste).header("Access-Control-Allow-Origin", "*").build();
    }

    @Path("{ref}")
    @PUT
    @Consumes("application/xml")
    public Response updateLogement(Logement updatedLogement, @PathParam("ref") int reference) {
        if (logementMetier.updateLogement(reference,updatedLogement)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }


    @Path("{ref}")
    @DELETE
        public  Response deleteLogement(@PathParam("ref") int reference){
           if(logementMetier.deleteLogement(reference))
                    return Response.status(Status.OK).build();
            return Response.status(Status.NOT_FOUND).build();
        }
}
