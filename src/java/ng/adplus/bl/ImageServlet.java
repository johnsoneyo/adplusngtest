/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ng.adplus.bl;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johnson3yo
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {

    @EJB
    AdplusEJB adp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acct_id = req.getParameter("account_id");

        byte[] image = adp.fetchImage(Integer.parseInt(acct_id));
        ServletOutputStream os = resp.getOutputStream();
        os.write(image);
        os.flush();
    }
}
