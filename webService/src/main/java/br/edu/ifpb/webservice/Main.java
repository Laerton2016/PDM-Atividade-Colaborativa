package br.edu.ifpb.webservice;


import br.edu.ifpb.modelo.Abastecimento;
import br.edu.ifpb.modelo.PostoDeCombustivel;
import br.edu.ifpb.modelo.TipoDeCombustivel;
import br.edu.ifpb.modelo.Usuario;
import br.edu.ifpb.persitencia.AbastecimentoDAO;
import br.edu.ifpb.persitencia.JPAUtil;
import br.edu.ifpb.persitencia.UsuarioDAO;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laerton
 */
public class Main {

    public static void main(String[] args) {
        //EntityManager em = JPAUtil.getEntityManager();
        Usuario user = new Usuario("Laerton Marques de Figueiredo", "laerton240311@gmail.com", "5555");
        PostoDeCombustivel posto = new PostoDeCombustivel("Queirogão", "PB", "Sousa");
        Abastecimento ab = new Abastecimento(user, posto, TipoDeCombustivel.ETANOL_COMUM, 5, 3.99, 56, LocalDateTime.now());
        AbastecimentoDAO dao = new AbastecimentoDAO();
        ab = dao.save(ab);
        System.out.println(ab);
        Abastecimento ab2 = dao.findAbastByTipoByPosto(TipoDeCombustivel.ETANOL_COMUM, posto);
        System.out.println(ab2);
    }
    
}
