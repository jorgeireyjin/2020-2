/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doit.jin.stuck;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Administrador
 */
public class Test {
private class TipoCYSexo {

    private StringBuffer tipi;
    private StringBuffer sexo;
    private boolean flag_nat = false;
    private char flag_sexo;

    public TipoCYSexo() {
        tipi = new StringBuffer(TAMANO_MAX_ARREGLO);
        sexo = new StringBuffer(TAMANO_MAX_ARREGLO);
    }

    public void setTipi(String tipi) {
        this.tipi.replace(0, tipi.length(), tipi);
    }

    public void setSexo(String sexo) {
        this.sexo.replace(0, sexo.length(), sexo);
    }

    public void setFlag_nat(boolean flag_nat) {
        this.flag_nat = flag_nat;
    }

    public void setFlag_sexo(char flag_sexo) {
        this.flag_sexo = flag_sexo;
    }

    public String getTipi() {
        return tipi.toString();
    }

    public StringBuffer getTipiSB() {
        return tipi;
    }

    public String getSexo() {
        return sexo.toString();
    }

    public StringBuffer getSexoSB() {
        return sexo;
    }

    public boolean isFlag_nat() {
        return this.flag_nat;
    }

    public char getFlag_Sexo() {
        return this.flag_sexo;
    }

    public String toString() {
        return ("tipi: " + tipi + " sexo: " + sexo + " flag_nat: " + flag_nat + " flag_sexo: " + flag_sexo);
    }
}

private class Palabra {
    private String ori;
    private String nor;
    private String tipo;
    private String sexo;
    private String err;
    public Palabra(){
      ori = " ";
      nor = " ";
      tipo = " ";
      sexo = " ";
      err = " ";
    }
    public void setOri(String ori){
      this.ori = ori;
    }
    public void setNor(String nor){
      this.nor = nor;
    }
    public void setTipo(String tipo){
      this.tipo = tipo;
    }
    public void setSexo(String sexo){
      this.sexo = sexo;
    }
    public void setErr(String err){
      this.err = err;
    }
    public String getOri(){
      return this.ori;
    }
    public String getNor(){
      return this.nor;
    }
    public String getTipo(){
      return this.tipo;
    }
    public String getSexo(){
      return this.sexo;
    }
    public String getErr(){
      return this.err;
    }
    
    public String toString() {
    	return ("Ori: " + ori + " Nor: " + nor + " Tipo: " + tipo + " Sexo: " + sexo + " Err: " + err);
    }
  }

  private static int TAMANO_MAX_ARREGLO = 30;
  String str_wrk = "SUPERINTENDENCIA NACIONAL DE ADMINISTRACION TRIBUTARIA";
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Test t = new Test();
        t.principal();
    }

    public void principal() {
        TipoCYSexo tipoysexo = new TipoCYSexo(); 
        tipoysexo.setTipi("Z");
        tipoysexo.setSexo(" ");

        String str_tipi_tmp = tipoysexo.getTipi();
        int pos_v = 0;
        int pos_w = 0;
        int pos_x = 0;
        if (!tipoysexo.isFlag_nat()) {
            pos_v = tipoysexo.getTipi().indexOf('V');
            while (pos_v != -1) {
                tipoysexo.setTipi(tipoysexo.getTipiSB().replace(pos_v, pos_v + 1, "Z").toString());
                pos_v = tipoysexo.getTipi().indexOf('V');
                System.out.println("primer bucle");
            }
        }
    }
    
}

