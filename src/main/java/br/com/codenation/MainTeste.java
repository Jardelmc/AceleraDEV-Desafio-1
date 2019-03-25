package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainTeste {
    public static void main(String[] args) {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        LocalDate l = LocalDate.parse("2018-02-02");
        LocalDate l1 = LocalDate.parse("2010-02-02");
        LocalDate l2 = LocalDate.parse("2009-02-02");
        BigDecimal b = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(150);
        BigDecimal b3 = new BigDecimal(100);
        BigDecimal b4 = new BigDecimal(70);
        desafio.incluirTime(0L, "Vasco", LocalDate.parse("2018-02-02"), "Preto", "Branco");
        desafio.incluirTime(1L, "Flamengo", LocalDate.parse("2018-02-02"), "Preto", "Branco");


        desafio.incluirJogador(1L, 0L, "Jardel", l2, 9, b2);
        desafio.incluirJogador(0L, 0L, "Maria", l1, 9, b2);
        desafio.incluirJogador(2L, 0L, "Pedro", l, 9, b2);

        desafio.incluirJogador(7L, 1L, "Jose", l2, 8, b2);
        desafio.incluirJogador(5L, 1L, "Tete", l, 8, b2);

       // desafio.definirCapitao(1L);
      //  desafio.buscarCapitaoDoTime(0L);
      //  desafio.buscarNomeJogador(1L);
      //  desafio.buscarNomeTime(0L);
      //  desafio.buscarJogadoresDoTime(0L);
        desafio.buscarMelhorJogadorDoTime(1L);
      //  desafio.buscarJogadorMaisVelho(1L);
      //  desafio.buscarTimes();


      //  desafio.buscarJogadorMaiorSalario(1L);
      //  desafio.buscarSalarioDoJogador(1L);
      //  desafio.buscarTopJogadores(5);
      //  desafio.buscarCorCamisaTimeDeFora(0L, 1L);

    }
}
