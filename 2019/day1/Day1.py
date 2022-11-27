with open('input') as fp:
    line = fp.readline()
    total_fuel = 0
    while line:
        mass = int(line)
        fuel = int(mass / 3) - 2
        total_fuel = total_fuel + fuel
        while fuel > 0:
            mass = fuel
            fuel = int(mass / 3) - 2
            if fuel < 0:
                fuel = 0
            total_fuel = total_fuel + fuel
        line = fp.readline()
    print(str(total_fuel))
