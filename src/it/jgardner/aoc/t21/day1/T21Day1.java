package it.jgardner.aoc.t21.day1;

import it.jgardner.aoc.utils.AOCUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class T21Day1 {
    public static void main(String[] args) {
        List<String> input = AOCUtils.getInputData(2021, 1);
        List<Integer> intInput = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        int part1result = countIncreases(intInput);
        System.out.println("Part1: " + part1result);

        List<Integer> windowSums =
                windowList(intInput, 3)
                        .map(window -> window.stream().reduce(Integer::sum).orElse(0))
                        .collect(Collectors.toList());
        int part2result = countIncreases(windowSums);
        System.out.println("Part2: " + part2result);
    }

    private static int countIncreases(List<Integer> list) {
        return (int)
                windowList(list, 2)
                        .map(window -> window.get(1) - window.get(0))
                        .filter(e -> e > 0)
                        .count();
    }

    private static <T> Stream<List<T>> windowList(List<T> list, int size) {
        if (size > list.size()) return Stream.empty();
        return IntStream.range(0, list.size() - size + 1)
                .mapToObj(start -> list.subList(start, start + size));
    }
}
