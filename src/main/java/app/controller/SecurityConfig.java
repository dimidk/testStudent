package app.controller;

import app.model.StudentGradeDAO;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 //   private static final String usersQuery = "select username, password FROM users where password = ?";
 //   private static final String rolesQuery = "select username,authority from authorities where username = ?";
    /**
     * because at first was used inMemory and created a test user with encryption this user
     * can not login because NoOpPasswordEncoder is used.
     * On the other hand all other users work correctly as must be.
     *
     */

    @Autowired
    private StudentGradeDAO std;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(std.getJdbc().getDataSource());
        //        .withUser("dimitra")
        //        .password("dimitra")
        //        .roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
        .antMatchers("/grades/**").hasAuthority("ROLE_USER")
                .antMatchers("/studcourse/**").hasAuthority("ROLE_USER")
                        .antMatchers("/course/**").hasRole("USER")
                   //     .antMatchers("/student").permitAll()
                        .and().formLogin();
                http.csrf().disable();
          //
    }
}
