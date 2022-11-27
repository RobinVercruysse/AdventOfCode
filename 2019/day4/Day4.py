candidates = []
for password in range(137683, 596254):
    passwordstr = str(password)
    never_decreases = True
    has_same_adjacents = False
    for i in range(1, len(passwordstr)):
        if i == 1:
            digit1 = int(passwordstr[:1])
            digit2 = int(passwordstr[1:2])
            if digit1 == digit2:
                next_digit = int(passwordstr[2:3])
                if digit1 != next_digit:
                    has_same_adjacents = True
        elif i == (len(passwordstr) - 1):
            digit1 = int(passwordstr[4:5])
            digit2 = int(passwordstr[5:])
            if digit1 == digit2:
                previous_digit = int(passwordstr[3:4])
                if digit1 != previous_digit:
                    has_same_adjacents = True
        else:
            digit1 = int(passwordstr[i-1:i])
            digit2 = int(passwordstr[i:i+1])
            if digit1 == digit2:
                previous_digit = int(passwordstr[i-2:i-1])
                next_digit = int(passwordstr[i+1:i+2])
                if digit1 != previous_digit and digit1 != next_digit:
                    has_same_adjacents = True
        if digit1 > digit2:
            never_decreases = False
    if never_decreases and has_same_adjacents:
        candidates.append(password)
print(len(candidates))
