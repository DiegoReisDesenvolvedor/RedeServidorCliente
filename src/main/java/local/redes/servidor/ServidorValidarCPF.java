//DIEGO OLIVEIRA DOS REIS
package local.redes.servidor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ServidorValidarCPF {
    
    public static final double divisor_verificador = 11.0;
    public static int fator_verificador = 10;
    public static int indiceDoCpfASerVerificado = 9;
    
    @FunctionalInterface
    interface Operacao <T,V,Y>{
        T executar(V a, Y b);
        
    }
    
    @FunctionalInterface
    interface Verificacao <T,V>{
        void executar(T a, V b);
        
    }
    
    
    
    public Integer extracaoDeNumeroDoCPf(String Cpf){
        
        List<String> entradaCPF = new ArrayList<>(List.of(Cpf.split("")));
       
        AtomicInteger resultado = new AtomicInteger(0);
        AtomicInteger index = new AtomicInteger(fator_verificador);
        Operacao<Integer,Integer,Integer> soma = (a,b) -> resultado.addAndGet(a*b);
                
        entradaCPF.stream()
                .limit(indiceDoCpfASerVerificado)
                .forEach(digito ->{
                       soma.executar(index.get(),Integer.parseInt(digito));
                       index.decrementAndGet();
                       
                });
        fator_verificador++;
        indiceDoCpfASerVerificado++;
                
        return resultado.get();
    };
    
    
    public boolean compararNumeroCPF(Integer entradaResultado, Integer numeroTesteAserVerficado){
         Operacao<Double,Integer,Double> divisao = (a,b) -> (a/b) - Math.floor(a/b);
         Operacao<Double,Double,Double> multiplicacao = (a,b) -> divisor_verificador-a*b;
         
         if(divisao.executar(entradaResultado, divisor_verificador)>=0.2){
             return (int) Math.round(multiplicacao.executar(divisor_verificador, divisao.executar(entradaResultado,divisor_verificador)))==numeroTesteAserVerficado;
          }else{
             return 0==numeroTesteAserVerficado;
         }
         
   
    }
    
    
    
    public String validarCPF(String numeroDoCpf){
        
                
        if(compararNumeroCPF((extracaoDeNumeroDoCPf(numeroDoCpf)),Character.getNumericValue(numeroDoCpf.charAt(9)))){
            if(compararNumeroCPF((extracaoDeNumeroDoCPf(numeroDoCpf)), Character.getNumericValue(numeroDoCpf.charAt(10)))){
                return "O CPF E VALIDO";
            }else{
                return "O CPF E INVALIDO";
                }
            } return "O CPF E INVALIDO";
            
        }
    
}
