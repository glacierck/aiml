/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manuel
 */
public class Disciplina {

    public static final List<String> disciplina = new ArrayList<String>();

    static {
        disciplina.add("Matemática");
        disciplina.add("Português");
        disciplina.add("Estudo do Meio");
        disciplina.add("Inglês");
        disciplina.add("Ciências");
        disciplina.add("Geografia");
        disciplina.add("História");
    }

}
