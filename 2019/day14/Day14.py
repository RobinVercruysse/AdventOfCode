from typing import List
from math import ceil


class Reaction:
    def __init__(self, inputs: List, output_count: int, output_chemical):
        self.inputs = inputs
        self.output_count = output_count
        self.output_chemical = output_chemical
        self.excess = 0

    def produce(self, count: int):
        count -= self.excess
        reaction_count = ceil(count / output_count)



reactions = {}
with open('input') as fp:
    line = fp.readline()
    while line:
        equation_parts = line.split('=>')
        inputs = []
        for inputstr in equation_parts[0].strip().split(','):
            input_parts = inputstr.strip().split(' ')
            inputs.append((int(input_parts[0]), input_parts[1]))
        output_parts = equation_parts[1].strip().split(' ')
        output_count = int(output_parts[0])
        output_chemical = output_parts[1]
        reactions[output_chemical] = Reaction(inputs, output_count, output_chemical)
        line = fp.readline()
