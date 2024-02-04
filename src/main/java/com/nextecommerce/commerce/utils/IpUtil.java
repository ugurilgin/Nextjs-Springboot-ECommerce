package com.nextecommerce.commerce.utils;

import com.nextecommerce.commerce.configs.EnvironmentConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IpUtil {

    private final EnvironmentConfig environmentConfig;

    public String getClientIpAddr(HttpServletRequest request) {
        return environmentConfig.isTrustHeaders() ? getTrustedIpHeader(request) : getDefaultIp(request);
    }

    private String getTrustedIpHeader(HttpServletRequest request) {
        String ip = request.getHeader(environmentConfig.getTrustedIpHeader());
        int port = request.getHeader(environmentConfig.getTrustedPortHeader()) != null
                ? Integer.parseInt(request.getHeader(environmentConfig.getTrustedPortHeader()))
                : -1;

        return nullCheck(request, ip, port);
    }

    private String getDefaultIp(HttpServletRequest request) {
        return request.getRemoteAddr() + ":" + request.getRemotePort();
    }

    private String nullCheck(HttpServletRequest request, String ip, int port) {
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            return getDefaultIp(request);
        } else {
            return ip + ":" + port;
        }
    }
}