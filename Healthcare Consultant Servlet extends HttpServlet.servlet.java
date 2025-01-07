import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HealthcareConsultantServlet extends HttpServlet {
    // doGet method: Displays the registration form or profile page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            // Show the registration form
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        } else if ("profile".equals(action)) {
            // Show the user profile
            RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp"); // Default landing page
        }
    }

    // doPost method: Handles the form submission for user registration
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the registration page
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");

        // Create a User object to hold the user's data
        User user = new User(name, email, Integer.parseInt(age));

        // Set user data as a request attribute
        request.setAttribute("user", user);

        // Forward to the profile page to display the user info
        RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
    }
}
