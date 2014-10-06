package br.com.fiap.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class LoginListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5180756750068892757L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		FacesContext context = arg0.getFacesContext();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		
		String pagina = context.getViewRoot().getViewId();
		
		if (!pagina.contains("noticias")){
			if ( session == null || session.getAttribute("usuario") == null){
				NavigationHandler navigation = 
								context.getApplication().getNavigationHandler();
				navigation.handleNavigation(context, null, "/xhtml/login/login");
			}	
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}




