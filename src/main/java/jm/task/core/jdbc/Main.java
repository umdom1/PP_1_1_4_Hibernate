package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;



public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Tom", "LastName1", (byte) 20);
        userDao.saveUser("Jon", "LastName2", (byte) 25);
        userDao.saveUser("Kati", "LastName3", (byte) 31);
        userDao.saveUser("Pool", "LastName4", (byte) 38);
        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }

}