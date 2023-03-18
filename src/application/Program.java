package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("Entre com o caminho do arquivo: ");
        String path = sc.nextLine();
        System.out.println();
        System.out.print("Digite o salário a comparar: ");
        double salary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> empMoreThanSalary = list.stream()
                    .filter(emp -> emp.getSalary() > 2000.00)
                    .map(emp -> emp.getEmail()).collect(Collectors.toList());

            System.out.printf("Email das pessoas que possuem mais de %.2f: %n",salary);
            empMoreThanSalary.forEach(System.out::println);

            double sumM = list.stream()
                    .filter(emp -> emp.getName().toUpperCase().charAt(0) == 'M')
                    .map(emp -> emp.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.printf("A soma do salários das pessoas que começam com M: %.2f", sumM);
        }
        catch (IOException e){
            System.out.println("Erro: "+ e.getMessage());
        }
        sc.close();
    }
}