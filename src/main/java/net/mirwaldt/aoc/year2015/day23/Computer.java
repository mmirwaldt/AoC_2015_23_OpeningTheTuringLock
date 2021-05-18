package net.mirwaldt.aoc.year2015.day23;

import java.util.EnumMap;
import java.util.List;
import java.util.function.ToIntFunction;

public interface Computer {
    enum Register { a, b }
    int hlf(Register register);
    int tpl(Register register);
    int inc(Register register);
    int jmp(int offset);
    int jie(Register register, int offset);
    int jio(Register register, int offset);
    EnumMap<Computer.Register, Long> run(List<ToIntFunction<Computer>> statements);

    void resetRegisters();
    void initRegisterValues(EnumMap<Register, Long> registerValues);
}
