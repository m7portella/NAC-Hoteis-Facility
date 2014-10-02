package br.com.fiap.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class LoginListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		//Recuperar a sessão do usuário
		FacesContext context = arg0.getFacesContext();
		HttpSession session = (HttpSession) 
				context.getExternalContext().getSession(false);
		
		//Recupera a página que o usuário quer acessar
		String pagina = context.getViewRoot().getViewId();
		

		if (session == null || session.getAttribute("usuario") == null){
			NavigationHandler navigation = 
							context.getApplication().getNavigationHandler();
			navigation.handleNavigation(context, null, "/xhtml/login/login");
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}




