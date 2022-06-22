package pl.skefb.springshop.jwt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.skefb.springshop.exception.ApiRequestException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UsernameAndPasswordAuthenticationRequest authenticationRequest =
                    mapper.readValue(request.getInputStream(),
                            UsernameAndPasswordAuthenticationRequest.class);
            if (authenticationRequest.getPassword() == null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> map = new HashMap<>();
                map.put("message", "Nie podano hasła");
                map.put("status", HttpServletResponse.SC_BAD_REQUEST);
                map.put("timestamp", ZonedDateTime
                        .now(ZoneId.of("Europe/Warsaw")).toOffsetDateTime().toString());
                String json = new ObjectMapper().writeValueAsString(map);
                response.getWriter().write(json);
                return null;
            }
            if (authenticationRequest.getUsername() == null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                Map<String, Object> map = new HashMap<>();
                map.put("message", "Nie podano emaila");
                map.put("status", HttpServletResponse.SC_BAD_REQUEST);
                map.put("timestamp", ZonedDateTime
                        .now(ZoneId.of("Europe/Warsaw")).toOffsetDateTime().toString());
                String json = new ObjectMapper().writeValueAsString(map);
                response.getWriter().write(json);
                return null;
            }
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String key = "7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(H+";
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes())).compact();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("authorization_token", "Bearer " + token);
        map.put("data", tokenMap);
        map.put("message", "Sukces");
        map.put("status", HttpServletResponse.SC_OK);
        map.put("timestamp", ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toOffsetDateTime().toString());
        String json = new ObjectMapper().writeValueAsString(map);
        response.getWriter().write(json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Podano zły login lub hasło");
        map.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("timestamp", ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toOffsetDateTime().toString());
        String json = new ObjectMapper().writeValueAsString(map);
        response.getWriter().write(json);
    }


}
