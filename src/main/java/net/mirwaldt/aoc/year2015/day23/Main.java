package net.mirwaldt.aoc.year2015.day23;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.ToIntFunction;

public class Main {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input"), StandardCharsets.US_ASCII);
        final List<ToIntFunction<Computer>> program = new ArrayList<>();
        for (String line : lines) {
            String[] tokens = line.split(" ");

            final String command = tokens[0];
            if ("hlf".equals(command)) {
                program.add((computer) -> computer.hlf(Computer.Register.valueOf(tokens[1])));
            } else if ("tpl".equals(command)) {
                program.add((computer) -> computer.tpl(Computer.Register.valueOf(tokens[1])));
            } else if ("inc".equals(command)) {
                program.add((computer) -> computer.inc(Computer.Register.valueOf(tokens[1])));
            } else if ("jmp".equals(command)) {
                program.add((computer) -> computer.jmp(Integer.parseInt(tokens[1])));
            } else if ("jie".equals(command)) {
                program.add((computer) ->
                        computer.jie(
                                Computer.Register.valueOf(tokens[1].replace(",","")),
                                Integer.parseInt(tokens[2])));
            } else if ("jio".equals(command)) {
                program.add((computer) ->
                        computer.jio(
                                Computer.Register.valueOf(tokens[1].replace(",","")),
                                Integer.parseInt(tokens[2])));
            } else {
                throw new RuntimeException("Cannot evaluate statement " + line + " in line " + program.size());
            }
        }

        final DefaultComputer computer = new DefaultComputer();
        final EnumMap<Computer.Register, Long> registersResultForPartOne = computer.run(program);
        System.out.println(registersResultForPartOne.get(Computer.Register.b)); // result: 170

        computer.resetRegisters();
        EnumMap<Computer.Register, Long> initialRegisterValues = new EnumMap<>(Computer.Register.class);
        initialRegisterValues.put(Computer.Register.a, 1L);
        computer.initRegisterValues(initialRegisterValues);
        final EnumMap<Computer.Register, Long> registersResultForPartTwo = computer.run(program);
        System.out.println(registersResultForPartTwo.get(Computer.Register.b)); // result: 247
    }
}
