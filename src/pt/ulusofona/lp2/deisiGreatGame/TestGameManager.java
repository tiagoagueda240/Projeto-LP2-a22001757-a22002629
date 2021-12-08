package pt.ulusofona.lp2.deisiGreatGame;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestGameManager {
    GameManager gameManagerTestes = new GameManager();

    @Test
    public void test02_tamanhoTabuleiro01() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 0);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_tamanhoTabuleiro02() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 2);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_ferramentaInvalida() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"1", "10", "2"}, {"1", "2", "4"}, {"1", "3", "6"}, {"0", "0", "9"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 15, ferramentasEAbismo);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_abismoInvalida() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"1", "4", "2"}, {"1", "2", "4"}, {"1", "3", "6"}, {"0", "10", "9"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 30, ferramentasEAbismo);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_idAbismoOuFerramenta() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"1", "4", "2"}, {"1", "2", "4"}, {"1", "3", "6"}, {"5", "4", "9"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 30, ferramentasEAbismo);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_corInvalida() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Yellow"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_limiteJogador() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20);
        assertEquals(false, iniciar);
    }

    @Test
    public void test02_informacaoJogadores() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Yellow"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"0", "7", "2"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20);
        boolean movimento = gameManagerTestes.moveCurrentPlayer(1);
        String mensagem = gameManagerTestes.reactToAbyssOrTool();
        List<Programmer> infoJogadores = gameManagerTestes.getProgrammers(true);
        assertEquals("22001757 | Tiago Águeda | 2 | No tools | Java, C, Kotlin | Em Jogo", infoJogadores.get(0).toString());
    }

    @Test
    public void test02_posicaoForaTabuleiro() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Yellow"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"0", "7", "2"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20);

        assertEquals(null, gameManagerTestes.getProgrammers(30));
    }


    @Test
    public void test01_AtivaFerramentas() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}, {"19999639", "Camelo Cabral", "Python, C++", "Purple"}};
        String[][] ferramentasEAbismo = {{"1", "4", "2"}, {"1", "2", "4"}, {"1", "3", "6"}, {"0", "0", "9"}, {"0", "1", "10"}, {"0", "2", "13"}};
        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20, ferramentasEAbismo);
        boolean movimento = gameManagerTestes.moveCurrentPlayer(1);
        String mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(3);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(5);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(8);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(2, gameManagerTestes.programadores.get(0).getPosicao());
        movimento = gameManagerTestes.moveCurrentPlayer(6);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(10, gameManagerTestes.programadores.get(1).getPosicao());
        movimento = gameManagerTestes.moveCurrentPlayer(7);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(6, gameManagerTestes.programadores.get(2).getPosicao());
    }

    @Test
    public void test02_SemAjuda() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Green"}};
        String[][] ferramentasEAbismo = {{"0", "4", "14"}, {"0", "9", "16"}, {"0", "7", "17"}, {"0", "8", "19"}};

        boolean iniciar = gameManagerTestes.createInitialBoard(info, 25, ferramentasEAbismo);
        boolean movimento = gameManagerTestes.moveCurrentPlayer(3);
        String mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(5);
        mensagem = gameManagerTestes.reactToAbyssOrTool();

        movimento = gameManagerTestes.moveCurrentPlayer(5);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(1, gameManagerTestes.programadores.get(1).getPosicao());
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(2);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(17, gameManagerTestes.programadores.get(0).getPosicao());
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals("Derrotado", gameManagerTestes.programadores.get(0).getEstado());
    }

    @Test
    public void test03_2Ferramentas() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Purple"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Brown"}};
        String[][] ferramentasEAbismo = {{"1", "0", "3"}, {"1", "0", "7"}, {"1", "4", "12"}};

        boolean iniciar = gameManagerTestes.createInitialBoard(info, 20, ferramentasEAbismo);
        boolean movimento = gameManagerTestes.moveCurrentPlayer(2);
        String mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(1, gameManagerTestes.programadores.get(0).getFerramentas().size());
        movimento = gameManagerTestes.moveCurrentPlayer(6);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(5);
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(2, gameManagerTestes.programadores.get(0).getFerramentas().size());
    }

    @Test
    public void test04_CicloInfinito() {
        String[][] info = {{"22001757", "Tiago Águeda", "Java, C, Kotlin", "Blue"}, {"22002629", "João Antas", "Javascript, C++, Assembly", "Purple"}};
        String[][] ferramentasEAbismo = {{"1", "0", "3"}, {"1", "4", "12"}, {"0", "8", "19"}};

        boolean iniciar = gameManagerTestes.createInitialBoard(info, 30, ferramentasEAbismo);
        boolean movimento = gameManagerTestes.moveCurrentPlayer(2); //3
        String mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(5); //6
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(6); // 9
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(6); // 12
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(6); // 15
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(3); //15
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        movimento = gameManagerTestes.moveCurrentPlayer(4); //19
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(true, gameManagerTestes.programadores.get(0).getValorPreso());
        movimento = gameManagerTestes.moveCurrentPlayer(4); //19
        mensagem = gameManagerTestes.reactToAbyssOrTool();
        assertEquals(false, gameManagerTestes.programadores.get(0).getValorPreso());
        assertEquals(true, gameManagerTestes.programadores.get(1).getValorPreso());
        assertEquals(22001757, gameManagerTestes.getCurrentPlayerID()); // testa para ver se esta a ir buscar bem o jogador
        assertEquals("Tiago Águeda : Herança | João Antas : IDE", gameManagerTestes.getProgrammersInfo()); // ve se as informações do jogador estão corretas
    }

}