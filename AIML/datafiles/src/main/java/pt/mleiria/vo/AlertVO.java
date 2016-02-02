/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

/**
 *
 * @author manuel
 */
public class AlertVO {
    private String nome;
    private String descricao;
    private String diaSemana;
    private String hora;
    private String horaFim;
    private int activo;
    private int voice;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getActivo() {
        return UtilsVO.yesNo(activo);
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getVoice() {
        return UtilsVO.yesNo(voice);
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }
    
    
}
