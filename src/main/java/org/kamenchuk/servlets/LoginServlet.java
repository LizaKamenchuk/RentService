package org.kamenchuk.servlets;

import lombok.SneakyThrows;
import org.kamenchuk.dao.factories.DaoFactory;
import org.kamenchuk.dto.CreateUserDto;
import org.kamenchuk.models.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String email = request.getParameter("email"),
                password = request.getParameter("password");
        System.out.println(email+" "+password);
        Role role = new Role(2,"user");

        PrintWriter printWriter = response.getWriter();
        String htmlResponse = "<html>"+"<h2>Your email is: "+email+"</h2></html>";

        CreateUserDto userDto = CreateUserDto.builder()
                .login(email)
                .password(password)
                .role(role)
                .build();
        DaoFactory.INSTANCE.getUserDao().insert(userDto);

        printWriter.print(htmlResponse);
        printWriter.flush();
    }
}
