package app.controller;

import app.model.StudentGradeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String usersQuery = "select username, password FROM users where password = ?";
    private static final String rolesQuery = "select username,authority from authorities where username = ?";

    @Autowired
    private StudentGradeDAO std;

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(std.getJdbc().getDataSource());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
        .antMatchers("/grades/**").hasRole("USER")
                        .antMatchers("/course/**").hasRole("USER")
                   //     .antMatchers("/student").permitAll()
                        .and().formLogin();
                http.csrf().disable();
          //
    }
}
