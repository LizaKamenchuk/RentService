package org.kamenchuk;

import org.kamenchuk.dao.factories.DaoFactory;
import org.kamenchuk.models.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> users = DaoFactory.INSTANCE.getUserDao().getAll();
        users.stream().forEach(System.out::println);
        Long id = Long.valueOf(4);
        System.out.println(DaoFactory.INSTANCE.getUserDao().getById(id));
   }
}
