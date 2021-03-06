package edu.uci.ics.luci.groupstatusserver.userdatabase;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO;

@SuppressWarnings("serial")
public class ServletCreateUser extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating a new user");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		String userID = checkNull(req.getParameter("userID"));
		String userPW = checkNull(req.getParameter("userPW"));
		String group = checkNull(req.getParameter("group"));
		String type = checkNull(req.getParameter("type"));
		String startingDateOfExp = checkNull(req.getParameter("startingDateOfExp"));
		String timeIntervalOfExp = checkNull(req.getParameter("timeIntervalOfExp"));
		String other = checkNull(req.getParameter("other"));

		UserDAO.INSTANCE.add(userID, userPW, group, type, startingDateOfExp, timeIntervalOfExp, other);

		resp.sendRedirect("/ParticipantManagementApplication.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ CreateAUser");

		doPost(req, resp);

	}

	private String checkNull(String s) {
		if (s == null) {
			return "n/a";
		}
		return s;
	}
}