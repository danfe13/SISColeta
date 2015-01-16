package br.ufs.coleta.SISColeta.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufs.coleta.SISColeta.entities.TbUsuario;

public class AutenticacaoFilter extends UsernamePasswordAuthenticationFilter {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("coleta_ds");
    private EntityManager em;
    
    public TbUsuario Logon(String login) {
    	em = factory.createEntityManager();
        TbUsuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM TbUsuario u WHERE u.login = :login");
            query.setParameter("login", login);

            usuario = (TbUsuario) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return usuario;
	}
	
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, BadCredentialsException {
        String login = request.getParameter("j_username");
        String senha = request.getParameter("j_password");
		
		TbUsuario usuario = this.Logon(login);
		
		List<SimpleGrantedAuthority> papeis = new ArrayList<SimpleGrantedAuthority>();
		String cript = HashGenerator.gerar(senha);
		if (usuario == null) {
			throw new UsernameNotFoundException(
					"Usuário não encontrado!");
		} else if (!usuario.getSenha().equals(cript)) {
			throw new BadCredentialsException("Senha incorreta");
		} else if (!usuario.getAtivo()) {
			throw new DisabledException("Usuário " + login + " está desativado");
		} else {
			if (usuario.getTbPerfil().getDescricao().equals("ROLE_ADMIN")) {
				papeis.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			else if (usuario.getTbPerfil().getDescricao().equals("ROLE_ALUNO")) {
				papeis.add(new SimpleGrantedAuthority("ROLE_ALUNO"));
			}
			else{
				throw new AuthenticationServiceException("Ocorreu uma falha no sistema. Favor informar administrador do sistema");
			}
			request.getSession().setAttribute("usuarioLogado", usuario);
			Authentication result = new UsernamePasswordAuthenticationToken(
					usuario.getLogin(), usuario.getSenha(), papeis);
			return result;
		}
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        response.sendRedirect("/SISColeta/private/index.jsf");
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendRedirect("/SISColeta/public/login.jsf");
    }
}
