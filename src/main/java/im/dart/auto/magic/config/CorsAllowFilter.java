package im.dart.auto.magic.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>描述: 暂无描述信息</p>
 *
 * <p>创建时间：2023-04-14 15:19</p>
 * <p>更新时间：暂无</p>
 *
 * @author Kevin.Xu
 * @version 1.0
 */
@Component
public class CorsAllowFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        this.allowCORSWithCredentials(req, resp);
        chain.doFilter(req, resp);
    }

    /**
     * 允许携带Cookie需要使用原始的 origin 和 headers 信息，而不能使用 *
     *
     * @param req
     * @param resp
     */
    private void allowCORSWithCredentials(HttpServletRequest req, HttpServletResponse resp) {
        resp.addHeader("Access-Control-Max-Age", "3600");
        resp.addHeader("Access-Control-Allow-Methods", "GET,POST");
        resp.addHeader("Content-type", "application/json;charset=utf8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");

        //使用原始的origin才可以跨域带Cookie，以保持Session一致
//        String origin = req.getHeader("origin");
//        resp.addHeader("Access-Control-Allow-Origin", Checker.isEmpty(origin) ? "*" : origin);
//        resp.addHeader("Access-Control-Allow-Credentials", "true");
//        StringBuilder headerNames = new StringBuilder();
//        Enumeration<String> names = req.getHeaderNames();
//        if (names.hasMoreElements()) {
//            headerNames.append(",").append(names.nextElement());
//            while (names.hasMoreElements()) {
//                headerNames.append(",").append(names.nextElement());
//            }
//        }
//        String header = headerNames.toString();
//        resp.setHeader("Access-Control-Allow-Headers", header);
    }
}
