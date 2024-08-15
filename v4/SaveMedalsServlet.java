import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/save_medals")
public class SaveMedalsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        int gold = Integer.parseInt(request.getParameter("gold"));
        int silver = Integer.parseInt(request.getParameter("silver"));
        int bronze = Integer.parseInt(request.getParameter("bronze"));

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO medals (country, gold, silver, bronze) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, country);
            stmt.setInt(2, gold);
            stmt.setInt(3, silver);
            stmt.setInt(4, bronze);

            int rowsAffected = stmt.executeUpdate();
            response.getWriter().write(rowsAffected > 0 ? "success" : "failure");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }
}
