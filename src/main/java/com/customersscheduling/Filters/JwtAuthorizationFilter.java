package com.customersscheduling.Filters;

import com.customersscheduling.Domain.Client;
import com.customersscheduling.ExceptionHandling.CustomExceptions.JwtTokenMissingException;
import com.customersscheduling.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.customersscheduling.Service.ClientService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private ClientService service;

    private final Log logger = LogFactory.getLog(getClass());

    public JwtAuthorizationFilter() throws Exception{
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\BitoEngineer\\Desktop\\Projeto e Seminario\\Codigo\\Customers Scheduling Server\\Firebase\\serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        logger.info("Authorization header: "+header);

        if (header == null || !header.startsWith("Bearer ")) {
            logger.error("Authorization header doesn't have the right format");
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String idToken = header.substring(7);
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            throw new JwtTokenMissingException("ID Token couldn't be verified");
        }

        String email = decodedToken.getEmail();
        try{
            service.getClient(email);
        }catch(ResourceNotFoundException e){
            Client c = new Client();
            c.setEmail(email);
            c.setName(decodedToken.getName());
            service.insertClient(c);
        }
        filterChain.doFilter(request, response);
    }
}