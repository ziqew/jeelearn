package com.abien.stm.control;

import com.abien.stm.entity.Snapshot;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Adam Bien, blog.adam-bien.com
 */
public class DataProvider {

    public static final String BASE_URL = "http://localhost:4848/monitoring/domain/server/";
    public static final String HEAP_SIZE = "jvm/memory/usedheapsize-count";
    private static final String THREAD_COUNT = "jvm/thread-system/threadcount";
    private static final String ERROR_COUNT = "http-service/server/request/errorcount";
    private static final String AVG_PROCESSING_TIME = "http-service/server/request/processingtime";
    private static final String HTTP_BUSY_THREADS = "network/thread-pool/currentthreadsbusy";
    private static final String COMMITTED_TX = "transaction-service/committedcount";
    private static final String ROLLED_BACK_TX = "transaction-service/rolledbackcount";
    private static final String QUEUED_CONNS = "network/connection-queue/countqueued";
    private Client client;

    @PostConstruct
    public void initializeClient() {
        this.client = Client.create();
    }

    public Snapshot fetchData(){
        try {
            long usedHeapSize = usedHeapSize();
            int threadCount = threadCount();
            int totalErrors = totalErrors();
            int currentThreadBusy = currentThreadBusy();
            int committedTX = committedTX();
            int rolledBackTX = rolledBackTX();
            int queuedConnections = queuedConnections();
            return new Snapshot(usedHeapSize, threadCount, totalErrors, currentThreadBusy, committedTX, rolledBackTX, queuedConnections);
        } catch (JSONException e) {
            throw new IllegalStateException("Cannot fetch monitoring data because of: "+ e);
        }
    }

    
    long usedHeapSize() throws JSONException{
        final String uri = BASE_URL + HEAP_SIZE;
        return getLong(uri,"usedheapsize-count");
    }

    int threadCount() throws JSONException{
        final String uri = BASE_URL + THREAD_COUNT;
        return getInt(uri,"threadcount");
    }

    int totalErrors() throws JSONException{
        final String uri = BASE_URL + ERROR_COUNT;
        return getInt(uri,"errorcount");
    }

    int currentThreadBusy() throws JSONException{
        final String uri = BASE_URL + HTTP_BUSY_THREADS;
        return getInt(uri,"currentthreadsbusy");
    }
    
    
    int committedTX() throws JSONException{
        final String uri = BASE_URL + COMMITTED_TX;
        return getInt(uri,"committedcount");
    }

    int rolledBackTX() throws JSONException{
        final String uri = BASE_URL + ROLLED_BACK_TX;
        return getInt(uri,"rolledbackcount");
    }
    
    int queuedConnections() throws JSONException{
        final String uri = BASE_URL + QUEUED_CONNS;
        return getInt(uri,"countqueued");
    }
    
    
    long getLong(String uri,String name) throws JSONException{
        ClientResponse result = client.resource(uri).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        return getJSONObject(result,name).getLong("count");
    
    }
    int getInt(String uri,String name) throws JSONException{
        ClientResponse result = client.resource(uri).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        return getJSONObject(result,name).getInt("count");
    
    }

    JSONObject getJSONObject(ClientResponse result,String name) throws JSONException {
        JSONObject response = result.getEntity(JSONObject.class);
        return response.getJSONObject("extraProperties").
                getJSONObject("entity").
                getJSONObject(name);
    }
}
