package net.mirwaldt.aoc.year2015.day23;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.ToIntFunction;

import static net.mirwaldt.aoc.year2015.day23.Computer.Register.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerTest {
    @Test
    void test() {
        final List<ToIntFunction<Computer>> program =
                Arrays.asList(
                        (computer) -> computer.inc(a),
                        (computer) -> computer.jio(a, +2),
                        (computer) -> computer.tpl(a),
                        (computer) -> computer.inc(a)
                );
        Computer computer = new DefaultComputer();
        final EnumMap<Computer.Register, Long> registers = computer.run(program);
        assertEquals(2, registers.get(a));
    }
}
