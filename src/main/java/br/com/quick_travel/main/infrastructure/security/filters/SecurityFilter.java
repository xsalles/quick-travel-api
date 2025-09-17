package br.com.quick_travel.main.infrastructure.security.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.quick_travel.main.common.provider.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider JWTProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");

        if (header != null) {
            var subjectToken = this.JWTProvider.validateToken(header);

            if (subjectToken.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            request.setAttribute("user_id", subjectToken);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subjectToken, null,
                    Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("Authentication set for user: " + subjectToken);
        }

        filterChain.doFilter(request, response);
    }

}
