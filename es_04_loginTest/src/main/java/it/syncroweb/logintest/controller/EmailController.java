package it.syncroweb.logintest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

    @PostMapping()
    public String creaAccount(){
        return "ok";
    }

    /*Contiene la pagina con il link
     *In caso di errore reinderizzo ad una pagina di errore
     *//*
    @GetMapping()
    public String paginaLinkConferma(){
        return "c";
    }*/

    /*Salva l'account e reinderizza alla homepage
     *Se il token è scaduto reinderizza ad una nuova pagina
     *//*
    @GetMapping()
    public String confermaMailToHomePage(){
        return "l";
    }*/

    /*Contiene il link per richiedere un nuovo token e mandare una nuova mail
     *Rimanda ad una pagina dove Confermo che ho mandato una mail
     *//*
    @GetMapping()
    public String resendRegistrationToken(){
        return "ok";
    }*/

    /* 1. @GetMapping("pagina_dove_ti_registri")
     * 2. @PostMapping("registra_utente")
     * 3. @GetMapping("pagina_di_conferma_con_link")
     * 4. @GetMapping("
     *//*
}
*/