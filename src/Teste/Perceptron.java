/*
 * Disciplina: A.I  Inteligência Artificial.
 * Trabalho: Software Perceptron AND. Com 3 entradas! 
 * Grupo: Marcelo Vieira, Marcelo Ferreira, Vladir Orlando, 7º Período.
 * Última atualização: v2.4 03/07/2018 13:00
 * 
 */
package Teste;

import javax.swing.JOptionPane;

public class Perceptron {

    private final double teta;
    private final double alfa;
    private double x1, x2, x3, t, resul_yent, y, w1, w2, w3, b, vw1, vw2, vw3, vb;
    private double w1Old;
    private double w2Old;
    private double w3Old;
    private double bOld;
    final double entrada[][] = {{1, 1, 1, 1, 1}, {1, 1, 0, 1, -1}, {1, 0, 1, 1, -1}, {1, 0, 0, 1, -1}, {0, 1, 1, 1, -1}, {0, 1, 0, 1, -1}, {0, 0, 1, 1, -1}, {0, 0, 0, 1, -1}};
    double variacoes[][] = new double[8][4];
    private int cont;

    public Perceptron(double alfa, double teta) {
        w1 = 0;
        w2 = 0;
        w3 = 0;
        b = 0;
        
        this.alfa = alfa;
        this.teta = teta;
        
        encontrarEpocas();
    }

    private void encontrarEpocas() {
        do {
            for (int i = 0; i < entrada.length; i++) {
                x1 = entrada[i][0];
                x2 = entrada[i][1];
                x3 = entrada[i][2];
                t = entrada[i][4];
                
                resul_yent = ((x1 * w1) + (x2 * w2) + (x3 * w3) + b);
                y = y();
                
                w1Old = w1;
                w2Old = w2;
                w3Old = w3;
                bOld = b;
                
                w1 = wI(y, t, w1, x1);
                w2 = wI(y, t, w2, x2);
                w3 = wI(y, t, w3, x3);
                b = b(y, t);
                
                vw1 = w1 - w1Old;
                vw2 = w2 - w2Old;
                vw3 = w3 - w3Old;
                vb = b - bOld;
                
                variacoes[i][0] = vw1;
                variacoes[i][1] = vw2;
                variacoes[i][2] = vw3;
                variacoes[i][3] = vb;
            }
            cont++;
        } while (variacao());
        JOptionPane.showMessageDialog(null, cont + " épocas encontradas."
                + "\nPeso 1: " + w1 + "\nPeso 2: " + w2 + "\nPeso 3: " + w3
                + "\n***** B: " + b);
    }

    private int y() {
        if (resul_yent >= teta) {
            return 1;
        } else if (resul_yent <= (teta * -1)) {
            return -1;
        } else {
            return 0;
        }
    }

    private double b(double y, double t) {
        if (y != t) {
            return b + (alfa * t);
        } else {
            return b;
        }
    }

    private double wI(double y, double t, double w, double x) {
        if (y != t) {
            return (w + (alfa * x * t));
        } else {
            return w;
        }
    }

    private boolean variacao() {
        for (int i = 0; i < variacoes.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (variacoes[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
