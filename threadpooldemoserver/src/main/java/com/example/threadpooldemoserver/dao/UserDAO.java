package com.example.threadpooldemoserver.dao;

import com.example.threadpooldemoserver.task3.UserDomain;

/**
 * description
 * history:
 */
public interface UserDAO {

    UserDomain getById(Integer id);
}
