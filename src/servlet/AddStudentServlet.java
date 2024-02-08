package servlet;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Students;

import java.io.IOException;

@WebServlet(value = "/addStudent")
public class AddStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthdate = req.getParameter("birthdate");
        String city = req.getParameter("city");

        Students student = new Students();
        student.setName(name);
        student.setSurname(surname);
        student.setCity(city);
        student.setBirthdate(birthdate);

        DBManager.addStudent(student);
        resp.sendRedirect("/students");
    }
}
