package com.winja.graphql.Repo;

import com.winja.graphql.Model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepo {

    private static Logger log = LoggerFactory.getLogger(UserRepo.class);

    public List<UserModel> getAllUserDetails()
    {
        UserModel userModel = new UserModel();
        userModel.setUsername("admin");
        userModel.setPassword("admin");
        userModel.setDesc("Owner of Account");
        userModel.setRole("Admin");
        UserModel userModel1 = new UserModel();
        userModel.setUsername("hoyChANcEN");
        userModel.setPassword("UleTHEMBAtuLzAr");
        userModel.setDesc("Here is your flag:Flag{3StvZstqDjLH15ZAaeSa_graphql_is_good}");
        userModel.setRole("Developer");
        return List.of(userModel,userModel1);
    }

    public UserModel getUserDetailsByLogin(String username,String password) {
        try {
            return getAllUserDetails().
                    stream().filter(d -> (d.getUsername().equals(username) && d.getPassword().equals(password))).
                    findFirst().get();
        }
        catch (Exception e)
        {
            log.error("Something wrong in getting the information",e);

        }
        return null;
    }
}
