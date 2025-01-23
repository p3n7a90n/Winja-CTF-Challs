package com.winja.adapter.output;

import com.winja.application.out.UserRegistrationPort;
import com.winja.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSaveUser implements UserRegistrationPort {

    @Override
    public void saveUser(User user) {
        // Todo: Save the user in the database
    }


}
