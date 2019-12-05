from typing import List
from time import time


current_input = int(input('input: '))


def get_result(intcodes: List[int]):
    ptr = 0
    while int(intcodes[ptr]) != 99:
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
            intcodes[ptr1] = current_input
            ptr += 2
        elif intcode == 4:
            ptr1 = intcodes[ptr + 1]
            if len(parameter_modes) > 0 and parameter_modes[:1] == '1':
                value = ptr1
            else:
                value = intcodes[ptr1]
            print(str(value) + '\n')
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
    return intcodes[0]


original_list = []
with open('input') as fp:
    line = fp.readline()
    for code in line.strip().split(','):
        original_list.append(int(code))
start = time()
get_result(original_list.copy())
end = time()
