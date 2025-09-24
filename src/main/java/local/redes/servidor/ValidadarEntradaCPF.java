//DIEGO OLIVEIRA DOS REIS
package local.redes.servidor;


public class ValidadarEntradaCPF {
    
    public static String entradaValida(String cpf) throws EntradaInvalidaException{
        
            return validarSomenteNumerosCPF(validarNumerosDiferentes(validarTamanhoCPF(cpf)));
        
    }
    
    public static String validarTamanhoCPF(String cpf) throws EntradaInvalidaException{
        
        if(cpf.length()==11){
            return cpf;
        }else{
            throw new EntradaInvalidaException("ENTRADA INVALIDA - Voce nao digitou 11 numeros do CPF.");
        }
        
    }
    
    public static String validarSomenteNumerosCPF(String cpf) throws EntradaInvalidaException{
        
        if(cpf.matches("\\d+")){
            return cpf;
        }else{
            throw new EntradaInvalidaException( "ENTRADA INVALIDA - Digite Apenas numeros para o CPF.");
        }
        
    }
    
    public static boolean verificadorNumerosDiferente(String cpf){
        return cpf.chars()
                .distinct()
                .count()==1;
    }
    
    public static String validarNumerosDiferentes (String cpf) throws EntradaInvalidaException{
        
        if(!verificadorNumerosDiferente(cpf)){
            return cpf;
        }else{
            throw new EntradaInvalidaException( "ENTRADA INVALIDA - O CPF digitado tem apenas numeros iguais.");
        }
        
    }
    
}
