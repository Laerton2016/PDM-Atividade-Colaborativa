/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.service;

import br.edu.ifpb.modelo.Abastecimento;
import br.edu.ifpb.modelo.PostoDeCombustivel;
import br.edu.ifpb.modelo.TipoDeCombustivel;
import br.edu.ifpb.persitencia.AbastecimentoDAO;

/**
 *
 * @author laerton
 */
public class AbastecimentoService {
    private AbastecimentoDAO dao = new AbastecimentoDAO();
    public Abastecimento findAbastecimento(long id){
        return dao.getById(Abastecimento.class, id);
    }
    public Abastecimento findValorCombustivel (TipoDeCombustivel tipo, PostoDeCombustivel posto){
        return dao.findAbastByTipoByPosto(tipo, posto);
    }
    public void deletar (Abastecimento abastecimento){
        dao.delete(abastecimento);
    }
    public Abastecimento salvar (Abastecimento abastecimento){
        if (abastecimento.getId() == 0){
            return dao.save(abastecimento);
        }
        return dao.update(abastecimento);
    }
}
