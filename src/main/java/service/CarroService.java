package service;

import model.Carro;

public class CarroService {
    public void ligar(Carro carro) {
        carro.setLigado(true);
    }

    public void desligar(Carro carro) throws Exception {
        if(carro.getVelocidadeAtual() > 0) {
            throw new Exception("So é possivel desligar um carro parado!");
        }

        carro.setLigado(false);
    }

    public void mostrarEstadoAtual(Carro carro) {
        System.out.println(carro.toString());
    }

    // TODO: REFACTOR
    public void acelerar(Carro carro, int velocidade) {
        if (velocidade < 0) {
            throw new IllegalArgumentException("A velocidade não pode ser negativa!");
        }

        if (!carro.getLigado()) {
            throw new IllegalArgumentException("O carro precisa estar ligado para acelerar!");
        }

        if(carro.getVelocidadeAtual() + velocidade >= carro.getVelocidadeMaxima()) {
            carro.setVelocidadeAtual(carro.getVelocidadeMaxima());
            return;
        }

        carro.setVelocidadeAtual(carro.getVelocidadeAtual() + velocidade);
    }

    public void frear(Carro carro, int velocidade) {
        if (velocidade < 0) {
            throw new IllegalArgumentException("A velocidade não pode ser negativa!");
        }

        if (!carro.getLigado()) {
            throw new IllegalArgumentException("O carro precisa estar ligado para frear!");
        }

        if (carro.getVelocidadeAtual() < velocidade) {
            throw new IllegalArgumentException("A velocidade não pode ser maior que a velocidade atual!");
        }

        if(carro.getVelocidadeAtual() - velocidade <= 0) {
            carro.setVelocidadeAtual(0);
            return;
        }

        carro.setVelocidadeAtual(carro.getVelocidadeAtual()-velocidade);
    }
}
