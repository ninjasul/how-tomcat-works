package ex02.pyrmont;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

/**
 * ServletProcessor1이 Servlet.service() 메소드 호출 시 Request 객체와 Response 객체를 그대로 넘기게 되는데
 * 이렇게 하면 Request 객체와 Response 객체의 public 메소드가 그대로 노출될 위험이 있다.
 * 그래서 Facade 객체들을 생성하여 public 메소드들을 노출하지 않도록 처리함.
 */
public class RequestFacade implements ServletRequest {

    private ServletRequest request = null;

    public RequestFacade(Request request) {
        this.request = request;
    }

    /* implementation of the ServletRequest */
    public Object getAttribute(String attribute) {
        return request.getAttribute(attribute);
    }

    public Enumeration getAttributeNames() {
        return request.getAttributeNames();
    }

    public String getRealPath(String path) {
        return request.getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return request.getRequestDispatcher(path);
    }

    public boolean isSecure() {
        return request.isSecure();
    }

    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }

    public int getContentLength() {
        return request.getContentLength();
    }

    public String getContentType() {
        return request.getContentType();
    }

    public ServletInputStream getInputStream() throws IOException {
        return request.getInputStream();
    }

    public Locale getLocale() {
        return request.getLocale();
    }

    public Enumeration getLocales() {
        return request.getLocales();
    }

    public String getParameter(String name) {
        return request.getParameter(name);
    }

    public Map getParameterMap() {
        return request.getParameterMap();
    }

    public Enumeration getParameterNames() {
        return request.getParameterNames();
    }

    public String[] getParameterValues(String parameter) {
        return request.getParameterValues(parameter);
    }

    public String getProtocol() {
        return request.getProtocol();
    }

    public BufferedReader getReader() throws IOException {
        return request.getReader();
    }

    public String getRemoteAddr() {
        return request.getRemoteAddr();
    }

    public String getRemoteHost() {
        return request.getRemoteHost();
    }

    public String getScheme() {
        return request.getScheme();
    }

    public String getServerName() {
        return request.getServerName();
    }

    public int getServerPort() {
        return request.getServerPort();
    }

    public void removeAttribute(String attribute) {
        request.removeAttribute(attribute);
    }

    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
        request.setCharacterEncoding(encoding);
    }

}