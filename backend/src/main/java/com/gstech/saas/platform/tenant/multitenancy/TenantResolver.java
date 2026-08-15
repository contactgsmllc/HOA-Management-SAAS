package com.gstech.saas.platform.tenant.multitenancy;

import com.gstech.saas.platform.tenant.repository.TenantRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class TenantResolver {

    private final TenantRepository tenantRepository;
    private final SecretKey jwtKey;

    public TenantResolver(
            TenantRepository tenantRepository,
            @Value("${jwt.secret}") String jwtSecret) {
        this.tenantRepository = tenantRepository;
        this.jwtKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public Long resolve(HttpServletRequest request) {

        String host = request.getServerName();

        // ── Production: resolve tenant from subdomain ─────────────────────────
        // Srini commentt and add below //if (host != null && !host.equals("localhost") && !host.equals("127.0.0.1")) {
        boolean platformHost =
                host == null
                        || host.equalsIgnoreCase("localhost")
                        || host.equals("127.0.0.1")
                        || host.equalsIgnoreCase("gsittech.com")
                        || host.equalsIgnoreCase("www.gsittech.com");

        // SaaS tenant subdomains:
        // company1.gsittech.com -> company1
        if (!platformHost && host.toLowerCase().endsWith(".gsittech.com")) {
        //Srini add end
            String subdomain = host.split("\\.")[0];
            return tenantRepository
                    .findBySubdomain(subdomain)
                    .orElseThrow(() -> new RuntimeException("Invalid tenant: " + subdomain))
                    .getId();
        }

        // Main platform domain:
        // authenticated requests get tenantId from JWT
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(jwtKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Long tenantId = claims.get("tenantId", Long.class);
                if (tenantId != null) {
                    return tenantId;
                }
            } catch (Exception ignored) {
            }
        }

        // Public platform requests such as signup/check-company
        return 0L;
    }
}