package br.com.codenation;


import br.com.codenation.DesafioMeuTimeApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DesafioMeuTimeApplicationTest {

    DesafioMeuTimeApplication desafio;

    @BeforeEach
    void setUp() {
        this.desafio = new DesafioMeuTimeApplication();
    }

    @Test
    void deveIncluirTime_Test() {
        this.desafio.incluirTime(1L, "TIME1",  LocalDate.of(2000, 01, 01), "azul", "vermelho");
        assertEquals("TIME1", this.desafio.buscarNomeTime(1L));
    }

    @Test
    void deveLancarExcessaoSeIdJaUtilizadoAoIncluirTime_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException.class,() -> {
            Long id = 1L;
            this.desafio.incluirTime(id, "TIME1",  LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirTime(id, "TIME2",  LocalDate.of(2000, 1, 1), "verde", "rosa");
        });
    }

    @Test
    void deveIncluirJogador_Test() {
        this.desafio.incluirTime(1L, "TIME1",  LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000,1,1), 50, new BigDecimal(10000.00));
        assertEquals("JOGADOR1", this.desafio.buscarNomeJogador(1L));
    }

    @Test
    void deveLancarExcessaoAoIncluirJogadorMesmoId_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException.class,() -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
            this.desafio.incluirJogador(1L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        });
    }

    @Test
    void deveLancarExcessaoAoIncluirJogadorMesmoIdTimesDiferentes_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException.class,() -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
            this.desafio.incluirJogador(1L, 2L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        });
    }


    @Test
    void deveLancarExcessaoAoIncluirJogadorETimeNaoEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class,() -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 2L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        });
    }

    @Test
    void deveDefinirCapitao_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));

        this.desafio.definirCapitao(1L);

        assertEquals(1L, this.desafio.buscarCapitaoDoTime(1L));

        this.desafio.definirCapitao(2L);

        assertEquals(2L, this.desafio.buscarCapitaoDoTime(1L));

        this.desafio.definirCapitao(1L);

        assertEquals(1L, this.desafio.buscarCapitaoDoTime(1L));

    }
    @Test
    void deveLancarExcessaoAoBuscarCapitaoDoTimeETimeNaoForEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class,() -> {
            this.desafio.buscarCapitaoDoTime(1L);
        });
    }

    @Test
    void deveLancarExcessaoAoBuscarCapitaoDoTimeECapitaoNaoDefinido_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException.class,() -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));

            this.desafio.buscarCapitaoDoTime(1L);
        });
    }

    @Test
    void buscarNomeJogador_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));

        assertEquals("JOGADOR1", this.desafio.buscarNomeJogador(1L));
    }

    @Test
    void deveLancarExessaoAobuscarNomeJogadorENaoForEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException.class, () -> {
            this.desafio.buscarNomeJogador(1L);
        });
    }



    @Test
    void buscarNomeTime_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        assertEquals("TIME1", this.desafio.buscarNomeTime(1L));
    }

    @Test
    void deveLancarExessaoAobuscarNomeTimeENaoForEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarNomeTime(1L);
        });
    }


    @Test
    void deveBuscarJogadoresDoTime_Test() {
        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));
        this.desafio.incluirJogador(6L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 50, new BigDecimal(10000.00));
        this.desafio.incluirJogador(3L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 40, new BigDecimal(1000.00));

        List<Long> listaEsperada = Arrays.asList(1L,2L,3L,4L,5L,6L);

        assertEquals(listaEsperada, this.desafio.buscarJogadoresDoTime(1L));
    }

    @Test
    void deveLancarExcessaoAoBuscarJogadoresDoTimeETimeNaoEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarJogadoresDoTime(1L);
        });
    }

    @Test
    void deveBuscarMelhorJogadorDoTime_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2000, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2000, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2000, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2000, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(7L, this.desafio.buscarMelhorJogadorDoTime(1L));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 60, new BigDecimal(1000.00));
        assertEquals(7L, this.desafio.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    void deveLancarExcessaoAoBuscarMelhorJogadorDoTimeETimeNaoEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarMelhorJogadorDoTime(1L);
        });
    }

    @Test
    void deveBuscarJogadorMaisVelho_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(7L, this.desafio.buscarJogadorMaisVelho(1L));

        this.desafio.incluirJogador(6L, 1L, "JOGADOR6", LocalDate.of(2000, 1, 1), 70, new BigDecimal(1000.00));
        assertEquals(6L, this.desafio.buscarJogadorMaisVelho(1L));
    }

    @Test
    void deveLancarExcessaoAoBuscaJogadorMaisVelhoDoTimeETimeNaoEncontrado_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarJogadorMaisVelho(1L);
        });
    }

    @Test
    void deveBuscarTimes_Test() {

        List<Long> listaEsperada = Arrays.asList();

        assertEquals(listaEsperada, this.desafio.buscarTimes());

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(5L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME3", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(6L, "TIME4", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(3L, "TIME5", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(4L, "TIME6", LocalDate.of(2000, 1, 1), "azul", "vermelho");


        listaEsperada = Arrays.asList(1L,2L,3L,4L,5L,6L);

        assertEquals(listaEsperada, this.desafio.buscarTimes());
    }

    @Test
    void deveBuscarJogadorMaiorSalario_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 1L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 1L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(1L, this.desafio.buscarJogadorMaiorSalario(1L));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(100000.00));
        assertEquals(9L, this.desafio.buscarJogadorMaiorSalario(1L));
    }
    @Test
    void deveLancarExcessaoAoBuscarJogadorMaiorSalarioSemTime_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarJogadorMaiorSalario(1L);
        });
    }

    @Test
    void deveBuscarSalarioDoJogador_Test() {


        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 1L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 2L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 2L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(1L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(2L));
        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(5L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(4L));
        assertEquals(new BigDecimal(10000.00), this.desafio.buscarSalarioDoJogador(7L));
        assertEquals(new BigDecimal(1000.00), this.desafio.buscarSalarioDoJogador(8L));

    }


    @Test
    void deveLancarExcessaoAoBuscarSalarioDoJogadorEJogadorNaoExistir_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException.class, () -> {
            this.desafio.buscarSalarioDoJogador(1L);
        });
    }

    @Test
    void deveBuscarTopJogadores_Test() {

        List<Long> listaEsperada;

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "vermelho");

        this.desafio.incluirJogador(1L, 1L, "JOGADOR1", LocalDate.of(2001, 1, 1), 10, new BigDecimal(10000.00));
        this.desafio.incluirJogador(2L, 2L, "JOGADOR2", LocalDate.of(2002, 1, 1), 50, new BigDecimal(1000.00));
        this.desafio.incluirJogador(5L, 1L, "JOGADOR3", LocalDate.of(2003, 1, 1), 30, new BigDecimal(10000.00));
        this.desafio.incluirJogador(4L, 2L, "JOGADOR4", LocalDate.of(2004, 1, 1), 20, new BigDecimal(1000.00));
        this.desafio.incluirJogador(7L, 2L, "JOGADOR5", LocalDate.of(2000, 1, 1), 60, new BigDecimal(10000.00));
        this.desafio.incluirJogador(8L, 1L, "JOGADOR6", LocalDate.of(2010, 1, 1), 0, new BigDecimal(1000.00));

        listaEsperada = Arrays.asList(7L, 2L, 5L, 4L, 1L, 8L);
        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(6));

        this.desafio.incluirJogador(9L, 1L, "JOGADOR7", LocalDate.of(2010, 1, 1), 60, new BigDecimal(1000.00));

        listaEsperada = Arrays.asList(7L, 9L, 2L, 5L, 4L);

        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(5));
    }

    @Test
    void deveBuscarTopJogadoresVazio_Test() {

        List<Long> listaEsperada = new ArrayList<>();
        assertEquals(listaEsperada, this.desafio.buscarTopJogadores(1));
    }


    @Test
    void deveBuscarCorCamisaTimeDeForaUniformesPrincipaisIguais_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "azul", "amarelo");
        assertEquals("amarelo", this.desafio.buscarCorCamisaTimeDeFora(1L, 2L));

    }


    @Test
    void deveBuscarCorCamisaTimeDeForaUniformesPrincipaisDiferentes_Test() {

        this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
        this.desafio.incluirTime(2L, "TIME2", LocalDate.of(2000, 1, 1), "verde", "amarelo");
        assertEquals("verde", this.desafio.buscarCorCamisaTimeDeFora(1L, 2L));

    }

    @Test
    void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoPrimeiroTimeNaoExista_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.buscarCorCamisaTimeDeFora(3L, 1L);
        });


    }
    @Test
    void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoSegundoTimeNaoExista_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.incluirTime(1L, "TIME1", LocalDate.of(2000, 1, 1), "azul", "vermelho");
            this.desafio.buscarCorCamisaTimeDeFora(1L, 3L);
        });

    }

    @Test
    void deveLancarExcessaoAoBuscarCorCamisetaTimeDeForaCasoAmbosTimesNaoExistam_Test() {
        assertThrows(br.com.codenation.desafio.exceptions.TimeNaoEncontradoException.class, () -> {
            this.desafio.buscarCorCamisaTimeDeFora(1L, 3L);
        });

    }
}
