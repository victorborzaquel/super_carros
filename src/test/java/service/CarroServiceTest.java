package service;

import model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarroServiceTest {
    private final CarroService service = new CarroService();
    private Carro carro;

    @BeforeEach
    public void setUp() {
        carro = new Carro();
    }

//    RULES
    @Test
    void turnOfCarWhenRunning() {
        service.ligar(carro);
        service.acelerar(carro, 1);

        assertThrows(Exception.class, () -> service.desligar(carro));
    }

    @Test
    void turnOfCarWhenStopped() throws Exception {
        service.ligar(carro);
        service.desligar(carro);

        assertDoesNotThrow(() -> assertFalse(carro.getLigado()));
    }

    @Test
    void velocityCannotBeBrakeNegative() {
        service.ligar(carro);
        service.frear(carro, 1);

        assertEquals(0, carro.getVelocidadeAtual());
    }

//    TODO: REFACTOR
    @Test
    void velocityCannotBeAccelerateNegative() {
        service.ligar(carro);
        service.acelerar(carro, -1);

        assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    void velocityCannotBeAccelerateGreaterThanMax() {
        service.ligar(carro);
        service.acelerar(carro, 101);

        assertEquals(100, carro.getVelocidadeAtual());
    }

    @Test
    void velocityCannotBeAddAccelerateGreaterThanMax() {
        service.ligar(carro);
        service.acelerar(carro, 50);
        service.acelerar(carro, 51);

        assertEquals(100, carro.getVelocidadeAtual());
    }

    @Test
    void velocityCannotBeAddBrakeGreaterThanVelocity() {
        service.ligar(carro);
        service.acelerar(carro, 50);
        service.frear(carro, 51);

        assertEquals(50, carro.getVelocidadeAtual());
    }

    @Test
    void velocityCannotBeAddBrakeGreaterThanMinimum() {
        service.ligar(carro);
        service.frear(carro, 1);

        assertEquals(0, carro.getVelocidadeAtual());
    }
//    ON
    @Test
    void turnOnCar() {
        service.ligar(carro);

        assertTrue(carro.getLigado());
    }

    @Test
    void turnOffCar() throws Exception {
        service.ligar(carro);
        service.desligar(carro);

        assertFalse(carro.getLigado());
    }

    @Test
    void accelerateCar() {
        service.ligar(carro);
        service.acelerar(carro, 1);

        assertEquals(1, carro.getVelocidadeAtual());
        assertTrue(carro.getLigado());
    }

    @Test
    void brakeCar() {
        service.ligar(carro);
        service.acelerar(carro, 1);
        service.frear(carro, 1);

        assertEquals(0, carro.getVelocidadeAtual());
        assertTrue(carro.getLigado());
    }

    @Test
    void showCurrentState() {
        service.ligar(carro);
        service.acelerar(carro, 1);
        service.frear(carro, 1);

        assertDoesNotThrow(() -> service.mostrarEstadoAtual(carro));
        assertTrue(carro.getLigado());
    }

//    OFF
    @Test
    void turnOffCarWhenOff() throws Exception {
        service.desligar(carro);

        assertFalse(carro.getLigado());
    }

    @Test
    void accelerateCarWhenOff() {
        service.acelerar(carro, 1);

        assertEquals(0, carro.getVelocidadeAtual());
        assertFalse(carro.getLigado());
    }

    @Test
    void brakeCarWhenOff() {
        service.acelerar(carro, 10);
        service.frear(carro, 1);

        assertEquals(0, carro.getVelocidadeAtual());
        assertFalse(carro.getLigado());
    }

    @Test
    void showCurrentStateWhenOff() {
        service.acelerar(carro, 1);
        service.frear(carro, 1);

        assertDoesNotThrow(() -> service.mostrarEstadoAtual(carro));
    }
}