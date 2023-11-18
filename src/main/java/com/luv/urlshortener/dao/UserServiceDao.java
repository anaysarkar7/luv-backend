package com.luv.urlshortener.dao;

import com.luv.urlshortener.enums.Role;
import com.luv.urlshortener.model.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * UserServiceDao Class is used for Communicating/Using the UserService Instance in any other class.
 */
@Data
@Builder
@AllArgsConstructor
public class UserServiceDao {
    private String email;
    private String hashedPassword;
    private String firstname;
    private String lastname;
    private Role role;
    private List<Token> tokensList;
}
