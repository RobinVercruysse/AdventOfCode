from typing import List
from sys import exit


def get_result(intcodes: List[int]):
    ptr = 0
    while int(intcodes[ptr]) != 99:
        intcode = intcodes[ptr]
        if intcode == 1:
            ptr1 = intcodes[ptr + 1]
            ptr2 = intcodes[ptr + 2]
            ptr3 = intcodes[ptr + 3]
            intcodes[ptr3] = intcodes[ptr1] + intcodes[ptr2]
            ptr += 4
        elif intcode == 2:
            ptr1 = intcodes[ptr + 1]
            ptr2 = intcodes[ptr + 2]
            ptr3 = intcodes[ptr + 3]
            intcodes[ptr3] = intcodes[ptr1] * intcodes[ptr2]
            ptr += 4
    return intcodes[0]


original_list = []
with open('input') as fp:
    line = fp.readline()
    for code in line.strip().split(','):
        original_list.append(int(code))
for noun in range(0, 100):
    for verb in range(0, 100):
        temp_list = original_list.copy()
        temp_list[1] = noun
        temp_list[2] = verb
        result = get_result(temp_list)
        if result == 19690720:
            print('found it: ' + str(noun) + ' & ' + str(verb))
            exit(0)
