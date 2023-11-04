package edu.miu.apsd.olpe.config;


import edu.miu.apsd.olpe.entity.RoleTypes;
import edu.miu.apsd.olpe.filter.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class OlpeWebAppSecurityConfig {


    private UserDetailsService olpeUserDetailsService;
    private JWTAuthFilter jwtAuthFilter;

    String [] roles = {RoleTypes.ROLE_ADMIN.toString(), RoleTypes.ROLE_TEACHER.toString(), RoleTypes.ROLE_STUDENT.toString()};
    String [] unsecuredUrls = {
            "/olpeApp/api/v1/service/public/**"};

    String [] genericLoggedInUserUrls = {
            "/olpeApp/api/v1/service/public/authenticate"
    };
    String [] adminUrls = {
            "/olpeApp/api/v1/service/private/**"

    };
    String [] teacherUrls = {
            "/olpeApp/api/v1/service/private/courses/list",
            "/olpeApp/api/v1/service/private/courses/list_approved",
            "/olpeApp/api/v1/service/private/courses/get/**",
            "/olpeApp/api/v1/service/private/courses/update/**",
            "/olpeApp/api/v1/service/private/courses/delete/**"
    };
    String [] studentUrls = {
            "/olpeApp/api/v1/service/private/student_courses/list",
            "/olpeApp/api/v1/service/private/student_courses/get/**",
            "/olpeApp/api/v1/service/private/student_courses/register",
            "/olpeApp/api/v1/service/private/student_courses/get_all/**",
            "/olpeApp/api/v1/service/private/student_courses/delete/pending/**",
            "/olpeApp/api/v1/service/private/student_courses/update/bookmark/**"
    };


    public OlpeWebAppSecurityConfig(UserDetailsService adsUserDetailsService,
                                    JWTAuthFilter jwtAuthFilter) {
        this.olpeUserDetailsService = adsUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth
//                                    .requestMatchers("/olpeApp/api/v1/service/public/**").permitAll()
//                                    .requestMatchers("/olpeApp/api/v1/service/private/**").authenticated()
                                    .requestMatchers(unsecuredUrls).permitAll()
                                    .requestMatchers(genericLoggedInUserUrls).hasAnyAuthority(roles)
                                    .requestMatchers(teacherUrls).hasAnyAuthority(RoleTypes.ROLE_TEACHER.toString(), RoleTypes.ROLE_ADMIN.toString())
                                    .requestMatchers(studentUrls).hasAnyAuthority(RoleTypes.ROLE_ADMIN.toString(), RoleTypes.ROLE_STUDENT.toString())
                                    .requestMatchers(adminUrls).hasAuthority(RoleTypes.ROLE_ADMIN.toString())
                            ;
                        }
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(olpeUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
