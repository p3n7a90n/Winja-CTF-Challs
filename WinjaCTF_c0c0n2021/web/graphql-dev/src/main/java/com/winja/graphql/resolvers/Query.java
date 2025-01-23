package com.winja.graphql.resolvers;

import com.winja.graphql.Model.UserModel;
import com.winja.graphql.Repo.UserRepo;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    public UserModel login(String username, String password)
    {
        UserRepo userRepo = new UserRepo();
         return userRepo.getUserDetailsByLogin(username,password);


    }

}
