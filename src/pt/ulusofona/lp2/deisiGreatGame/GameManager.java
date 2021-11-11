package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameManager {
    ArrayList<Programmer> programadores = new ArrayList<>();
    int nrCasas;
    int nrTurnos = 1;
    int turnoAtual = 0;

    ProgrammerColor encontrarCor(String cor){
        switch (cor){
            case "PURPLE":
                return ProgrammerColor.PURPLE;

            case "BLUE":
                return ProgrammerColor.BLUE;

            case "GREEN":
                return ProgrammerColor.GREEN;

            case "BROWN":
                return ProgrammerColor.BROWN;

            default:
                return null;
        }
    }

    public GameManager() {
    }

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        programadores.clear();
        nrTurnos = 1;
        turnoAtual = 0;

        if (boardSize <= 1) {
            return false;
        }

        nrCasas = boardSize;
        for (int i = 0; i < playerInfo.length; i++) {
            ArrayList<String> languages = new ArrayList();
            languages.addAll(Arrays.asList(playerInfo[i][2].split(";")));
            Programmer player = new Programmer(playerInfo[i][1], languages, Integer.parseInt(playerInfo[i][0]), encontrarCor(playerInfo[i][3].toUpperCase()), 1, "Em Jogo");
            programadores.add(player);
        }

        if (programadores.size() > 4 || programadores.size() < 2 || nrCasas < programadores.size() * 2 ){
            return false;
        }

        HashSet<ProgrammerColor> colorDuplicado = new HashSet<>(); // não pode haver cores repetidas
        HashSet<Integer> idDuplicado = new HashSet<>(); // não pode haver iDs repetidos
        for (Programmer programador : programadores) {
            if (programador.getId() == 0 || colorDuplicado.contains(programador.getColor()) || idDuplicado.contains(programador.getId()) ||
                    programador.getColor() == null || programador.getName().equals("")) {
                return false;
            }
            idDuplicado.add(programador.getId());
            colorDuplicado.add(programador.getColor());

        }
        programadores.sort(Comparator.comparing((Programmer programador1) -> programador1.getId()));
        getImagePng(1);
        getImagePng(boardSize);
        return true;
    }

    public String getImagePng(int position) {
        if (position > nrCasas) {
            return null;
        }
        if (position == nrCasas) {
            return "glory.png";
        }

        for (Programmer programador: programadores) {
            if (programador.getPosicao() == position) {
                return "player" + programador.getColor().toString() + ".png";
            }
        }
        return null;
    }

    public ArrayList<Programmer> getProgrammers() {
        return programadores;
    }

    public ArrayList<Programmer> getProgrammers(int position) {
        if (position < 1 || position > nrCasas){
            return null;
        }
        ArrayList<Programmer> programadoresNaPosicao = new ArrayList<>();
        for (Programmer programador: programadores) {
            if (programador.getPosicao() == position) {
                programadoresNaPosicao.add(programador);
            }
        }
        /*if (programadoresNaPosicao.size() == 0 || position > nrCasas) { // Verificar numero de casas
            return null;
        }*/

        return programadoresNaPosicao;
    }

    public int getCurrentPlayerID() {
        return programadores.get(turnoAtual).getId();
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6) {
            return false;
        }
        for (Programmer programador: programadores) {
            if (programador.getId() == getCurrentPlayerID()){
                if (programador.getPosicao() + nrPositions <= nrCasas) {
                    programador.mover(nrPositions); // falta verificar se está fora do tabuleiro
                }else{
                    programador.recuar(nrPositions, nrCasas);
                }
            }
        }
        turnoAtual++;
        nrTurnos++;
        if(turnoAtual >= programadores.size()) {
            turnoAtual = 0;
        }
        return true;
    }

    public boolean gameIsOver() {
        for (Programmer programador: programadores){
            if (programador.getPosicao() == nrCasas){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("O GRANDE JOGO DO DEISI");
        resultados.add("");
        resultados.add("NR. DE TURNOS");
        resultados.add(String.valueOf(nrTurnos));
        resultados.add("");
        resultados.add("VENCEDOR");
        programadores.sort(Comparator.comparing((Programmer programador1) -> programador1.getPosicao()).reversed());
        resultados.add(programadores.get(0).getName());
        resultados.add("");
        resultados.add("RESTANTES");
        for (Programmer programador: programadores){
            if (programadores.get(0).getId() == programador.getId()){
                continue;
            }
            resultados.add(programador.getName() + " " + programador.getPosicao());
        }

        return resultados;
    }

    public JPanel getAuthorsPanel() {
        JPanel creditos = new JPanel();
        creditos.setSize(new Dimension(300, 300));
        JLabel linha1 = new JLabel();
        linha1.setText("Projeto Deisi Great Game");
        creditos.add(linha1);

        JLabel linha2 = new JLabel();
        linha1.setText("Tiago Águeda a22001757");
        creditos.add(linha2);

        JLabel linha3 = new JLabel();
        linha1.setText("João Antas a22002629");
        creditos.add(linha3);


        return creditos;
    }
}