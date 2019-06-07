package com.eh2s.ocm.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "AUTHORIZATION";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    private static final String SECRET_KEY = "OCM2018EH2S@RestFulAPIService!0?";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        List<String> SECURED_URL_PREFIX = intpath();
        boolean auth = false;
        for (String url : SECURED_URL_PREFIX) {
            if (requestContext.getUriInfo().getPath().contains(url)) {
                auth = true;
                break;
            }
        }
        System.out.println("Token " + requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY));
        if (auth) {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            System.out.println("Token " + authHeader.get(0));
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                // authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                System.out.println("Token " + authToken);
                if (SECRET_KEY.equals(authToken)) {
                    return;
                } else {
                    throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                }

            }
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

    }

    protected List<String> intpath() {
        List<String> SECURED_URL_PREFIX = new ArrayList<>();
        SECURED_URL_PREFIX.add("RestIncidents");
        SECURED_URL_PREFIX.add("RestArticles");
        SECURED_URL_PREFIX.add("RestLigneCommandes");
        SECURED_URL_PREFIX.add("RestCategories");
        SECURED_URL_PREFIX.add("RestClients");
        SECURED_URL_PREFIX.add("Restnotifications");
        SECURED_URL_PREFIX.add("RestRubriques");
        SECURED_URL_PREFIX.add("RestSRubriques");
        SECURED_URL_PREFIX.add("RestSociete");
        SECURED_URL_PREFIX.add("RestSourcesIncidents");
        SECURED_URL_PREFIX.add("Reststatutincident");
        SECURED_URL_PREFIX.add("RestCommandes");
        SECURED_URL_PREFIX.add("RestEtatCommandes");
        SECURED_URL_PREFIX.add("RestPriority");
        SECURED_URL_PREFIX.add("RestTypeClients");
        SECURED_URL_PREFIX.add("RestUsers");
        SECURED_URL_PREFIX.add("FermerIncident");
        return SECURED_URL_PREFIX;
    }

}
