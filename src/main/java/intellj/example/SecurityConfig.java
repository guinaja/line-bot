package intellj.example;

import org.springframework.context.annotation.Configuration;

/**
 * Created by prayoon.su on 3/24/2017.
 */
@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
}
