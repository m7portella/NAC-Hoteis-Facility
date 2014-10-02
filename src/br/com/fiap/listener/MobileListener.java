package br.com.fiap.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MobileListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		//Recuperar o contexto
		FacesContext context = arg0.getFacesContext();
		
		String userAgent = 
				context.getExternalContext()
					.getRequestHeaderMap().get("User-agent");
		
		String pagina = context.getViewRoot().getViewId();
		
		NavigationHandler navigation = 
				context.getApplication().getNavigationHandler();
		
		if (pagina.contains("login/login")){
			//Verifica o dispositivo...
			if (userAgent.contains("iPhone") || 
					userAgent.contains("Android")){
				navigation.handleNavigation(context, null, "/xhtml/login/login-mobile");
			}else{
				navigation.handleNavigation(context, null, "/xhtml/login/login");
			}
		}
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
