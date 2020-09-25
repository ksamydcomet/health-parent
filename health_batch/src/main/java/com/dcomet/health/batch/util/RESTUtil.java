package com.dcomet.health.batch.util;

import com.dcomet.fw.exception.DcometBatchException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

/**
 *
 * @author Dev2
 */
public class RESTUtil {

    private String accept = MediaType.APPLICATION_JSON;

//    // static for backwards compatibility
//    @Deprecated
//    public static ClientRequestFactory getRestClientFactory() throws Exception {
//        return new RESTUtil().getRestClientFactory(REST_HOST, USER_NAME, PASSWORD);
//    }
//
//    @Deprecated
//    public ClientRequestFactory getRestClientFactory(String rootURI, String userName, String password)
//            throws Exception {
//        ClientExecutor clientExecutor = new ApacheHttpClient4Executor(new RESTUtil().getHttpClient(userName, password));
//
//        URL url = new URL(rootURI);
//        ClientRequestFactory factory = new ClientRequestFactory(clientExecutor, url.toURI());
//        return factory;
//    }
//
//    public CloseableHttpClient getHttpClient() throws Exception {
//        return getHttpClient(USER_NAME, PASSWORD);
//    }
    public CloseableHttpClient getHttpClient(String userName, String password) throws DcometBatchException {
        org.apache.http.auth.UsernamePasswordCredentials credentials = new org.apache.http.auth.UsernamePasswordCredentials(userName, password);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(org.apache.http.auth.AuthScope.ANY, credentials);
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        return client;
    }
//
//    public HttpResponse assert200Post(String uri, String payload) throws Exception {
//        return assert200Post(uri, payload, USER_NAME, PASSWORD);
//    }
//

    public HttpResponse assert200Post(String uri, String username, String password, String data) throws DcometBatchException {
        try {
            org.apache.http.client.HttpClient httpClient = getHttpClient(username, password);
            HttpPost post = new HttpPost(uri);
            post.addHeader("Accept", accept);
            StringEntity input = new StringEntity(data);
            input.setContentType(MediaType.APPLICATION_JSON);
            post.setEntity(input);
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_NO_CONTENT
                    && HttpServletResponse.SC_OK != response.getStatusLine().getStatusCode()) {
                throw new DcometBatchException(String.valueOf(response.getStatusLine().getStatusCode()));
            }
            return response;
        } catch (DcometBatchException | IOException e) {
            throw new DcometBatchException(e);
        }
    }

    public HttpResponse assert200Put(String uri, String username, String password, String data) throws DcometBatchException {
        try {
            org.apache.http.client.HttpClient httpClient = getHttpClient(username, password);
            HttpPut post = new HttpPut(uri);
            post.addHeader("Accept", accept);
            StringEntity input = new StringEntity(data);
            input.setContentType(MediaType.APPLICATION_JSON);
            post.setEntity(input);
            HttpResponse response = httpClient.execute(post);
            if (HttpServletResponse.SC_OK != response.getStatusLine().getStatusCode()) {
                throw new DcometBatchException(String.valueOf(response.getStatusLine().getStatusCode()));
            }
            return response;
        } catch (DcometBatchException | IOException e) {
            throw new DcometBatchException(e);
        }
    }

    public String getContent(HttpResponse response) throws java.io.IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        StringBuilder buf = new StringBuilder();

        String output;
        while ((output = br.readLine()) != null) {
            buf.append(output);
        }

        return buf.toString();
    }

    public HttpResponse assert200(String uri, String username, String password) throws DcometBatchException {
        HttpResponse response = get(uri, username, password);
        if (HttpServletResponse.SC_OK != response.getStatusLine().getStatusCode()) {
            throw new DcometBatchException(String.valueOf(response.getStatusLine().getStatusCode()));
        }
        return response;
    }

    public HttpResponse get(String uri, String username, String password) throws DcometBatchException {
        try {
            org.apache.http.client.HttpClient httpClient = getHttpClient(username, password);
            HttpGet get = new HttpGet(uri);
            get.addHeader("Accept", accept);
            HttpResponse response = httpClient.execute(get);
            return response;
        } catch (DcometBatchException | IOException e) {
            throw new DcometBatchException(e);
        }
    }

    public RESTUtil setXML() {
        accept = MediaType.APPLICATION_XML;
        return this;
    }

    public RESTUtil setJSON() {
        accept = MediaType.APPLICATION_JSON;
        return this;
    }

//    public org.apache.http.auth.UsernamePasswordCredentials getCommonsV4UsernamePassword() {
//        org.apache.http.auth.UsernamePasswordCredentials
//                credentials = new org.apache.http.auth.UsernamePasswordCredentials(USER_NAME, PASSWORD);
//        return credentials;
//    }
//
//    public org.apache.commons.httpclient.UsernamePasswordCredentials getCommonsV3UsernamePassword() {
//        org.apache.commons.httpclient.UsernamePasswordCredentials
//                credentials = new org.apache.commons.httpclient.UsernamePasswordCredentials(USER_NAME, PASSWORD);
//        return credentials;
//    }
    public ApacheHttpClient4Executor getApacheHttpClient4Executor(String username, String password) throws Exception {
        return new ApacheHttpClient4Executor(getHttpClient(username, password));
    }

//    public ApacheHttpClient4Executor getApacheHttpClient4Executor() throws Exception {
//        return getApacheHttpClient4Executor(USER_NAME, PASSWORD);
//    }
//    public static Dispatcher buildDispatcher() {
//        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
//        dispatcher.getProviderFactory().registerProvider(JacksonJaxbJsonProvider.class);
//        dispatcher.getProviderFactory().registerProvider(AthenaRestExceptionMapper.class);
//        return dispatcher;
//    }
//
//    public org.jboss.resteasy.client.jaxrs.ResteasyWebTarget getRestEasyWebTarget(String url, String username, String password) {
//        BasicAuthentication authentication = new BasicAuthentication(username, password);
//
//        ResteasyClient aclient = new ResteasyClientBuilder()
//                .register(JacksonJaxbJsonProvider.class)
//                .register(authentication)
//                .build();
//
//        return aclient.target(url);
//    }
//
//    public static WebTarget getWebTarget(String restUrl) {
//        return getWebTarget(restUrl, USER_NAME, PASSWORD);
//    }
//
//    public static WebTarget getWebTarget(String restUrl, String username, String password) {
//        BasicAuthentication authentication = new BasicAuthentication(username, password);
//
//        ResteasyClient client =  new ResteasyClientBuilder()
//                .register(JacksonJaxbJsonProvider.class)
//                .register(authentication)
//                .build();
//        return client.target(restUrl);
//    }
}
