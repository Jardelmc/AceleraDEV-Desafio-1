package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private Map<Long, Time> mapTime = new HashMap<Long, Time>();
    private Map<Long, Jogador> mapJogador = new HashMap<Long, Jogador>();
    private Map<Long, Long> mapCapitao = new HashMap<Long, Long>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

        if (mapTime.get(id) == null) {
            Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            mapTime.put(id, time);
            mapCapitao.put(id, null);
        } else {
            throw new IdentificadorUtilizadoException();
        }

    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){

        if (mapJogador.get(id) == null) {
            if (mapTime.get(idTime) != null) {
                Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
                mapJogador.put(id, jogador);
            } else {
                throw new TimeNaoEncontradoException();
            }
        } else {
            throw new IdentificadorUtilizadoException();
        }

    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {

        if (mapJogador.get(idJogador) == null) {
            throw new JogadorNaoEncontradoException();
        } else {
            mapCapitao.put(mapJogador.get(idJogador).getIdTime(), idJogador);
        }

    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            if (mapCapitao.get(idTime) != null) {
                return mapCapitao.get(idTime);
            } else {
                throw new CapitaoNaoInformadoException();
            }
        }

    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {

        if (mapJogador.get(idJogador) == null) {
            throw new JogadorNaoEncontradoException();
        } else {
            return mapJogador.get(idJogador).getNome();
        }
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            return mapTime.get(idTime).getNome();
        }
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            List<Long> jogadores = new ArrayList<Long>();
            for (Map.Entry<Long, Jogador> map : mapJogador.entrySet()) {
                if(map.getValue().getIdTime().equals(idTime)) {
                    jogadores.add(map.getValue().getId());
                }
            }
            jogadores.sort(Long::compareTo);
            return jogadores;
        }
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            List<Jogador> jogadores = this.getJogadoresDoTime(idTime);
            jogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId));
            return jogadores.get(0).getId();
        }
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            List<Jogador> jogadores = this.getJogadoresDoTime(idTime);
            jogadores.sort(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId));
            return jogadores.get(0).getId();
        }
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {

        if (mapTime.isEmpty()) {
            return new ArrayList<Long>();
        } else {
            Set<Long> ids = mapTime.keySet();
            List<Long> times = new ArrayList<Long>();
            times = new ArrayList<>(ids);
            times.sort(Long::compareTo);
            return times;
        }
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {

        if (mapTime.get(idTime) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            List<Jogador> jogadores = this.getJogadoresDoTime(idTime);
            jogadores.sort(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId));
            return jogadores.get(0).getId();
        }
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {

        if (mapJogador.get(idJogador) == null) {
            throw new JogadorNaoEncontradoException();
        } else {
            return mapJogador.get(idJogador).getSalario();
        }
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {

        if (mapJogador.isEmpty()) {
            return new ArrayList<Long>();
        } else {
            List<Jogador> jogadores = new ArrayList<>(mapJogador.values());
            jogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId));
            List<Long> tops = new ArrayList<Long>();
            for (int i = 0; i < top; i++) {
                tops.add(jogadores.get(i).getId());
            }
            return tops;
        }
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

        if (mapTime.get(timeDaCasa) == null || mapTime.get(timeDeFora) == null) {
            throw new TimeNaoEncontradoException();
        } else {
            if (mapTime.get(timeDaCasa).getCorUniformePrincipal().equals(mapTime.get(timeDeFora).getCorUniformePrincipal())) {
                return mapTime.get(timeDeFora).getCorUniformeSecundario();
            } else {
                return mapTime.get(timeDeFora).getCorUniformePrincipal();
            }
        }
    }


    private List<Jogador> getJogadoresDoTime(Long idTime) {
        List<Jogador> jogadores = new ArrayList<Jogador>();
        for (Map.Entry<Long, Jogador> map : mapJogador.entrySet()) {
            if(map.getValue().getIdTime().equals(idTime)) {
                jogadores.add(map.getValue());
            }
        }
        return jogadores;
    }
}

