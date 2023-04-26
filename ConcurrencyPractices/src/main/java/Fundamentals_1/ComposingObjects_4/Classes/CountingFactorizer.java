package Fundamentals_1.ComposingObjects_4.Classes;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    public void service(ServletRequest request, ServletResponse response) {
        BigInteger bigInteger = extractFromRequest(request);
        BigInteger[] factors = factor(bigInteger);
        count.incrementAndGet();
        encodeIntoResponse(response, factors);
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger bigInteger) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return BigInteger.ONE;
    }
}
