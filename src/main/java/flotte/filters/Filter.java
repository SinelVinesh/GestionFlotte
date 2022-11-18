package flotte.filters;

import flotte.response.StatusResponse;
import flotte.services.TokenService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Filter  extends OncePerRequestFilter {
    private TokenService tokenService;

    public Filter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(tokenService.authenticate(token)) {
            filterChain.doFilter(request, response);
        } else {
            StatusResponse status = new StatusResponse(401,"unauthorized");
            ResponseEntity<StatusResponse> failed = new ResponseEntity<>(status, HttpStatus.UNAUTHORIZED);
            response.setStatus(401);
        }
    }
}
