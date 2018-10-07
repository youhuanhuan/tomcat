package ex03.pyrmont;

import ex03.pyrmont.connector.http.Constants;
import ex03.pyrmont.connector.http.HttpRequest;
import ex03.pyrmont.connector.http.HttpResponse;
import ex03.pyrmont.connector.http.HttpRequestFacade;
import ex03.pyrmont.connector.http.HttpResponseFacade;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import javax.servlet.Servlet;

public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);

            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            
            //外观设计模式
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);

            servlet.service(requestFacade, responseFacade);

            //刷新PrintWriter缓存
            ((HttpResponse) response).finishResponse();
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } catch (Throwable e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}