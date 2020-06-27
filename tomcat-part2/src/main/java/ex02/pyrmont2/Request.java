package ex02.pyrmont2;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.*;


public class Request implements ServletRequest {

    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }


    public void parse() {
    	
    	System.out.println("Request --- parse()");
    	
        // Read a set of characters from the socket
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
        	System.out.println("input " + input + " 可读len = " + input.available());
            i = input.read(buffer);
            System.out.println("Request --- read len = " + i);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.print(Thread.currentThread().getName()
                + " 解析请求------start----\n"
                + request.toString());
        System.out.println(Thread.currentThread().getName() + " 解析请求-----end----\n\n");
        
        uri = parseUri(request.toString());
    }


    /**
     * 我加的
     */
    public void parse2() {
        StringBuffer request = new StringBuffer(2048);
        byte[] buffer = new byte[2048];
        int len = 0;

        try {
            while ((len = input.read(buffer)) > 0) {
                for (int j = 0; j < len; j++) {
                    request.append((char) buffer[j]);
                }
                System.out.println(Thread.currentThread().getName() + " 请求参数: " + request.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(Thread.currentThread().getName()
                + " 解析请求------start----\n"
                + request.toString());
        System.out.println(Thread.currentThread().getName() + " 解析请求-----end----\n\n");
        uri = parseUri(request.toString());
    }

    public String getUri() {
        return uri;
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }

    /* implementation of the ServletRequest*/
    public Object getAttribute(String attribute) {
        return null;
    }

    public Enumeration getAttributeNames() {
        return null;
    }

    public String getRealPath(String path) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IllegalStateException {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

    public AsyncContext getAsyncContext() {
        return null;
    }

    public DispatcherType getDispatcherType() {
        return null;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public int getContentLength() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration getLocales() {
        return null;
    }

    public String getParameter(String name) {
        return null;
    }

    public Map getParameterMap() {
        return null;
    }

    public Enumeration getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String parameter) {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public void removeAttribute(String attribute) {
    }

    public void setAttribute(String key, Object value) {
    }

    public void setCharacterEncoding(String encoding)
            throws UnsupportedEncodingException {
    }

}