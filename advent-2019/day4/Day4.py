candidates = []
for password in range(137683, 596254):
    passwordstr = str(password)
    never_decreases = True
    has_same_adjacents = False
    for i in range(1, len(passwordstr) - 1):
        if i == 1:
            digit1 = int(passwordstr[:1])
            digit2 = int(passwordstr[1:2])
        elif i == (len(passwordstr) - 1):
            digit1 = int(passwordstr[4:5])
            digit2 = int(passwordstr[5:])
        else:
            digit1 = int(passwordstr[i-1:i])
            digit2 = int(passwordstr[i:i+1])
        if digit1 > digit2:
            never_decreases = False
        if digit1 == digit2:
            has_same_adjacents = True
    if never_decreases and has_same_adjacents:
        candidates.append(password)
print(len(candidates))
