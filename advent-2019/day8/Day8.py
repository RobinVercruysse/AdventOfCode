layers = []
width = 25
height = 6


def print_layer(layer):
    for h in range(height):
        row = ''
        for w in range(width):
            index = (h * (width - 1)) + w
            row += str(layer[index])
        print(row)


with open('input') as fp:
    layer_index = 0
    w = 0
    h = 0
    current_layer = []
    for digit in fp.readline():
        current_layer.append(int(digit))
        w += 1
        if w >= width:
            w = 0
            h += 1
        if h >= height:
            h = 0
            layers.append(current_layer)
            current_layer = []
            layer_index += 1
layer_fewest_zeroes = None
fewest_zeroes = -1
fewest_zeroes_ones = 0
fewest_zeroes_twos = 0
for layer in layers:
    zeroes = 0
    ones = 0
    twos = 0
    for digit in layer:
        if digit == 0:
            zeroes += 1
        elif digit == 1:
            ones += 1
        elif digit == 2:
            twos += 1
    if fewest_zeroes == -1 or zeroes < fewest_zeroes:
        fewest_zeroes = zeroes
        layer_fewest_zeroes = layer
        fewest_zeroes_ones = ones
        fewest_zeroes_twos = twos
print(fewest_zeroes)
print_layer(layer_fewest_zeroes)
print(fewest_zeroes_ones)
print(fewest_zeroes_twos)
print(fewest_zeroes_ones * fewest_zeroes_twos)
print('*'*40)

for h in range(0, height):
    row = ''
    for w in range(0, width):
        if h == 0:
            index = w
        else:
            index = (h * width) + w

        final_digit = ''
        for layer in layers:
            current_digit = layer[index]
            if current_digit == 0:
                final_digit = ' '
                break
            elif current_digit == 1:
                final_digit = '■'
                break
        row += final_digit
    print(row)
