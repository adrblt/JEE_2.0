package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ConnexionBean;

public class MSFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String chemin = request.getRequestURI().substring(request.getContextPath().length());
		if (chemin.startsWith("/resources")) {
			chain.doFilter(request, response);
			return;
		}
		ConnexionBean connexionBean = (ConnexionBean) request.getSession().getAttribute("connexionBean");
		if (connexionBean != null){
			if(connexionBean.isLoggedIn()) // IDMS
				chain.doFilter(request, response);
			else
				response.sendRedirect( request.getContextPath() + "/connexion.xhtml" );
		}
		else
			response.sendRedirect( request.getContextPath() + "/connexion.xhtml" );		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
