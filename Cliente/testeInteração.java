
public class testeInteração {
        public static void main(String[] args) throws InterruptedException {
            int contador = 510;
    
            long startTime = System.currentTimeMillis();

            while (contador > 0) {
                contador--;
                Thread.sleep(0, 10);
            }
    
            long endTime = System.currentTimeMillis();
            long tempoTotal = endTime - startTime;
    
            System.out.println("Tempo total para decrementar de 1000 para 0: " + tempoTotal + " milissegundos");
    }
}
