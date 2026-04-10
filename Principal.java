import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 Inserindo funcionários (dados da tabela)
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 Remover João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // Formatadores
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat moedaFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        System.out.println("\n--- LISTA DE FUNCIONÁRIOS ---");

        // 3.3 Imprimir dados formatados
        for (Funcionario f : funcionarios) {
            System.out.println(
                    f.getNome() + " | " +
                    f.getDataNascimento().format(dataFormat) + " | " +
                    moedaFormat.format(f.getSalario()) + " | " +
                    f.getFuncao()
            );
        }

        // 3.4 Aumento de 10%
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")));
        }

        // 3.5 Agrupar por função
        Map<String, List<Funcionario>> agrupados = new HashMap<>();
        for (Funcionario f : funcionarios) {
            agrupados.computeIfAbsent(f.getFuncao(), k -> new ArrayList<>()).add(f);
        }

        // 3.6 Imprimir agrupados
        System.out.println("\n--- AGRUPADOS POR FUNÇÃO ---");
        for (String funcao : agrupados.keySet()) {
            System.out.println("\nFunção: " + funcao);
            for (Funcionario f : agrupados.get(funcao)) {
                System.out.println(f.getNome());
            }
        }

        // 3.8 Aniversariantes mês 10 e 12
        System.out.println("\n--- ANIVERSARIANTES (OUTUBRO E DEZEMBRO) ---");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println(f.getNome());
            }
        }

        // 3.9 Funcionário mais velho
        Funcionario maisVelho = Collections.min(funcionarios,
                Comparator.comparing(Funcionario::getDataNascimento));

        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("\n--- FUNCIONÁRIO MAIS VELHO ---");
        System.out.println(maisVelho.getNome() + " | " + idade + " anos");

        // 3.10 Ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("\n--- ORDEM ALFABÉTICA ---");
        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome());
        }

    
    
        // 3.11 Total dos salários
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }

        System.out.println("\n--- TOTAL DOS SALÁRIOS ---");
        System.out.println(moedaFormat.format(total));

        // 3.12 Salários mínimos
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n--- SALÁRIOS MÍNIMOS ---");
        for (Funcionario f : funcionarios) {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd + " salários mínimos");
        }
    }
}
