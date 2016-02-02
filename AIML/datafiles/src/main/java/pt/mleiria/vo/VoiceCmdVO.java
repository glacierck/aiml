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
public class VoiceCmdVO {
    
    
    
    public static final String ENG = "en";
    public static final String PT = "pt";
    
    private final String header;
    private final String content;

    public VoiceCmdVO(final String header, final String content) {
        this.header = header;
        this.content = content;
    }
    
    public String getCommand(){
        return header + ";" + content;
    }
    
    
}
