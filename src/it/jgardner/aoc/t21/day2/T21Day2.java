package it.jgardner.aoc.t21.day2;

import it.jgardner.aoc.utils.AOCUtils;

import java.util.List;
import java.util.stream.Collectors;

public class T21Day2 {

    private static class Day2Values {
        int horiz;
        int depth;
        int aim;

        Day2Values() {
            horiz = 0;
            depth = 0;
            aim = 0;
        }
    }

    public static void main(String[] args) {
        List<String> input = AOCUtils.getInputData(2021, 2);
        List<String[]> commands =
                input.stream().map(command -> command.split(" +")).collect(Collectors.toList());

        Day2Values part1Result = new Day2Values();

        commands.forEach((command) -> {
            int value = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward" -> part1Result.horiz += value;
                case "up" -> part1Result.depth -= value;
                case "down" -> part1Result.depth += value;
            }
        });

        int part1Total = part1Result.horiz * part1Result.depth;
        System.out.println("Part1: " + part1Total);

        Day2Values part2Result = new Day2Values();

        commands.stream().forEachOrdered((command) -> {
            int value = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward" -> {
                    part2Result.horiz += value;
                    part2Result.depth += part2Result.aim * value;
                }

                case "up" -> part2Result.aim -= value;
                case "down" -> part2Result.aim += value;
            }
        });

        int part2Total = part2Result.horiz * part2Result.depth;
        System.out.println("Part2: " + part2Total);
    }
}
