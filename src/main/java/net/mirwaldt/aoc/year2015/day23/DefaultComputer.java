package net.mirwaldt.aoc.year2015.day23;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public class DefaultComputer implements Computer {
    // Don't use int instead of long because it will lead to an overflow and infinite loop in part two!
    private final EnumMap<Register, long[]> registers = new EnumMap<>(Register.class);

    public DefaultComputer() {
        for (Register register : Register.values()) {
            registers.put(register, new long[1]);
        }
    }

    private long[] value(Register register) {
        return registers.get(register);
    }

    @Override
    public void resetRegisters() {
        for (long[] value : registers.values()) {
            value[0] = 0;
        }
    }

    @Override
    public void initRegisterValues(EnumMap<Register, Long> registerValues) {
        for (Map.Entry<Register, Long> entry : registerValues.entrySet()) {
            registers.get(entry.getKey())[0] = entry.getValue();
        }
    }

    public EnumMap<Register, Long> getRegisterValues() {
        final EnumMap<Register, Long> result = new EnumMap<>(Register.class);
        for (Map.Entry<Register, long[]> entry : registers.entrySet()) {
            result.put(entry.getKey(), entry.getValue()[0]);
        }
        return result;
    }

    @Override
    public int hlf(Register register) {
//        System.out.println("hlf " + register.name());
        long[] value = value(register);
        value[0] = value[0] / 2;
        return +1;
    }

    @Override
    public int tpl(Register register) {
//        System.out.println("tpl " + register.name());
        long[] value = value(register);
        value[0] = value[0] * 3;
        return +1;
    }

    @Override
    public int inc(Register register) {
//        System.out.println("inc " + register.name());
        long[] value = value(register);
        value[0]++;
        return +1;
    }

    @Override
    public int jmp(int offset) {
//        System.out.println("jmp " + offset);
        return offset;
    }

    @Override
    public int jie(Register register, int offset) {
//        System.out.println("jie " + register.name() + " " + offset);
        long[] value = value(register);
        if (value[0] % 2 == 0) {
            return offset;
        } else {
            return +1;
        }
    }

    @Override
    public int jio(Register register, int offset) {
//        System.out.println("jio " + register.name() + " " + offset);
        long[] value = value(register);
        if (value[0] == 1) {
            return offset;
        } else {
            return +1;
        }
    }

//    public void printProgram(List<ToIntFunction<Computer>> program) {
//        for (ToIntFunction<Computer> statement : program) {
//            statement.applyAsInt(this);
//        }
//    }

    @Override
    public EnumMap<Computer.Register, Long> run(List<ToIntFunction<Computer>> program) {
        int programCounter = 0;
        while (0 <= programCounter && programCounter < program.size()) {
            ToIntFunction<Computer> statement = program.get(programCounter);
            programCounter += statement.applyAsInt(this);
            if(registers.get(Computer.Register.a)[0] < 0) {
                System.out.println();
            }
//            System.out.println("programCounter " + programCounter
//                    + ", a=" + registers.get(Computer.Register.a)[0] + ", b=" + registers.get(Computer.Register.b)[0]);
        }
        return getRegisterValues();
    }


}
