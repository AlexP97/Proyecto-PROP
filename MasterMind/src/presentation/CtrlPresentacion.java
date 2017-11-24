/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.CtrlDominio;
import domain.CtrlDominioJugador;
import domain.CtrlDominioPartida;
import domain.CtrlDominioRanking;

/**
 *
 * @author Usuario
 */
public class CtrlPresentacion {
    private final CtrlDominio CD;
    private final CtrlDominioJugador CDj;
    private final CtrlDominioPartida CDp;
    private final CtrlDominioRanking CDr;
    private CtrlPresentacionLoginRegister CPlr;
    private CtrlPresentacionMenu CPm;
    
    public CtrlPresentacion() {
        CD = new CtrlDominio();
        CDj = CD.getCtrlDominioJugador();
        CDp = CD.getCtrlDominioPartida();
        CDr = CD.getCtrlDominioRanking();
    }
    
    private void iniciarRegistroLogin(){
        CPlr = new CtrlPresentacionLoginRegister(CDj);
        if (CPlr.loginRegister()){
            iniciarCrearCargarRanking();
        }
    }
    
    private void iniciarCrearCargarRanking(){
        CPm = new CtrlPresentacionMenu(CDp,CDr);
        if(CPm.crearCargarRanking()){
            
        }
    }
    
    public void iniciarMastermind(){
        iniciarRegistroLogin();
    }
}
