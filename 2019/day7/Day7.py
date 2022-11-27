from typing import List
from itertools import permutations


with open('input') as fp:
    original_intcodes = [int(intcode) for intcode in fp.readline().split(',')]
amplifier_inputs = {}


def amplifier_process(intcodes: List[int], phase_setting, amplifier_index):
    take_input = False
    ptr = 0
    while ptr < len(intcodes):
        intcodestr = str(intcodes[ptr])
        intcode = int(intcodestr[len(intcodestr) - 2:])
        parameter_modes = intcodestr[:len(intcodestr) - 2][::-1]
        if intcode == 1:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 1 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            ptr3 = intcodes[ptr + 3]
            intcodes[ptr3] = value1 + value2
            ptr += 4
        elif intcode == 2:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 1 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            ptr3 = intcodes[ptr + 3]
            intcodes[ptr3] = value1 * value2
            ptr += 4
        elif intcode == 3:
            ptr1 = intcodes[ptr + 1]
            if take_input:
                intcodes[ptr1] = amplifier_inputs[amplifier_index]
            else:
                intcodes[ptr1] = phase_setting
                take_input = True
            ptr += 2
        elif intcode == 4:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value = ptr1
            else:
                value = intcodes[ptr1]
            yield value
            ptr += 2
        elif intcode == 5:
            ptr1 = intcodes[ptr + 1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            if len(parameter_modes) > 0 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            if value1 != 0:
                ptr = value2
            else:
                ptr += 3
        elif intcode == 6:
            ptr1 = intcodes[ptr + 1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            if len(parameter_modes) > 0 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            if value1 == 0:
                ptr = value2
            else:
                ptr += 3
        elif intcode == 7:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 1 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            ptr3 = intcodes[ptr + 3]
            if value1 < value2:
                intcodes[ptr3] = 1
            else:
                intcodes[ptr3] = 0
            ptr += 4
        elif intcode == 8:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value1 = ptr1
            else:
                value1 = intcodes[ptr1]
            ptr2 = intcodes[ptr + 2]
            if len(parameter_modes) > 1 and parameter_modes[1:2] == '1':
                value2 = ptr2
            else:
                value2 = intcodes[ptr2]
            ptr3 = intcodes[ptr + 3]
            if value1 == value2:
                intcodes[ptr3] = 1
            else:
                intcodes[ptr3] = 0
            ptr += 4
        elif intcode == 99:
            raise Exception()


def get_result(phase_settings):
    amplifiers = []
    for amplifier in range(5):
        amplifier_inputs[amplifier] = 0
        amplifiers.append(amplifier_process(original_intcodes.copy(), phase_settings[amplifier], amplifier))
    output = 0
    try:
        current_amplifier = 0
        while True:
            output = next(amplifiers[current_amplifier])
            if current_amplifier >= 4:
                current_amplifier = 0
            else:
                current_amplifier += 1
            amplifier_inputs[current_amplifier] = output
    except Exception:
        return output


highest_signal = 0
best_permutation = None
for permutation in permutations([5, 6, 7, 8, 9]):
    signal = get_result(permutation)
    if signal > highest_signal:
        highest_signal = signal
        best_permutation = permutation
print(best_permutation)
print(highest_signal)
