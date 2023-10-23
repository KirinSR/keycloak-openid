package com.keycloak.zig.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.saml.metadata.ExtendedMetadataDelegate;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
    }


    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .saml2Login(withDefaults());
//        return http.build();
//    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

//    @Bean
//    @Qualifier("idp-keycloak")
//    public ExtendedMetadataDelegate keycloakExtendedMetadataProvider(Environment env)
//            throws MetadataProviderException {
//        String idpKeycloakMetadataURL = env.getRequiredProperty("keycloak.auth-server-url") + "/protocol/saml/descriptor";
//        HTTPMetadataProvider httpMetadataProvider = new HTTPMetadataProvider(
//                this.backgroundTaskTimer, httpClient(), idpKeycloakMetadataURL);
//        httpMetadataProvider.setParserPool(parserPool());
//        ExtendedMetadataDelegate extendedMetadataDelegate =
//                new ExtendedMetadataDelegate(httpMetadataProvider, extendedMetadata());
//        extendedMetadataDelegate.setMetadataTrustCheck(true);
//        extendedMetadataDelegate.setMetadataRequireSignature(false);
//        backgroundTaskTimer.purge();
//        return extendedMetadataDelegate;
//    }

//    @Bean
//    @Qualifier("metadata")
//    public CachingMetadataManager metadata(List<MetadataProvider> providers) {
//        return new CachingMetadataManager(providers);
//    }
}