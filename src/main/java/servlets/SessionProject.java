package servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionProject {

	public void saveSessionString(HttpServletRequest req, String key, String value) {
		HttpSession session = req.getSession();
		session.setAttribute(key, value);
	}
	
	public void saveSessionTimeOut(HttpServletRequest req, int time) {
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(time);
	}
	
	public void invalidateSession(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
	public Object getSessionString(HttpServletRequest req, String key) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return null;
		}
		
		return session.getAttribute(key);
	}
}
