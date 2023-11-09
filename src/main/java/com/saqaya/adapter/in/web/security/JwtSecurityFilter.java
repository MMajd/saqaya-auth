package com.saqaya.adapter.in.web.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.saqaya.adapter.in.web.service.JwtService;
import com.saqaya.common.WebService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@WebService
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String BEAER_STR_TOKEN = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTH_HEADER_NAME);
        log.info("AuthHeader: {}", authHeader);

        if (!StringUtils.hasLength(authHeader) ||
                !StringUtils.startsWithIgnoreCase(authHeader, BEAER_STR_TOKEN)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(BEAER_STR_TOKEN.length());
        final String userId = jwtService.extractUserId(jwt);

        if (hasNoContext(userId)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            log.info("UserDetails: {}", userDetails);

            if (jwtService.isTokenValid(jwt, userDetails)) {

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);

                log.info("AuthToken: {}", authToken);
            }

        }

        filterChain.doFilter(request, response);
    }

    private boolean hasNoContext(String userId) {
        return StringUtils.hasLength(userId)
                && SecurityContextHolder.getContext().getAuthentication() == null;
    }

}
