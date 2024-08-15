import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get_medals")
public class GetMedalsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medal> medals = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM medals")) {

            while (rs.next()) {
                Medal medal = new Medal();
                medal.setCountry(rs.getString("country"));
                medal.setGold(rs.getInt("gold"));
                medal.setSilver(rs.getInt("silver"));
                medal.setBronze(rs.getInt("bronze"));
                medals.add(medal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(medals));
        out.flush();
    }

    private class Medal {
        private String country;
        private int gold;
        private int silver;
        private int bronze;

        // Getters and setters
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public int getGold() { return gold; }
        public void setGold(int gold) { this.gold = gold; }
        public int getSilver() { return silver; }
        public void setSilver(int silver) { this.silver = silver; }
        public int getBronze() { return bronze; }
        public void setBronze(int bronze) { this.bronze = bronze; }
    }
}
