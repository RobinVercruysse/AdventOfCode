class Moon:
    def __init__(self, x: int, y: int, z: int):
        self.original_x = x
        self.original_y = y
        self.original_z = z
        self.x = x
        self.y = y
        self.z = z
        self.vel_x = 0
        self.vel_y = 0
        self.vel_z = 0

    def update_velocity(self, moon):
        if moon.x > self.x:
            self.vel_x += 1
        elif moon.x < self.x:
            self.vel_x -= 1
        if moon.y > self.y:
            self.vel_y += 1
        elif moon.y < self.y:
            self.vel_y -= 1
        if moon.z > self.z:
            self.vel_z += 1
        elif moon.z < self.z:
            self.vel_z -= 1

    def apply_velocity(self):
        self.x += self.vel_x
        self.y += self.vel_y
        self.z += self.vel_z

    def get_potential_energy(self):
        return abs(self.x) + abs(self.y) + abs(self.z)

    def get_kinetic_energy(self):
        return abs(self.vel_x) + abs(self.vel_y) + abs(self.vel_z)

    def get_energy(self):
        return self.get_potential_energy() * self.get_kinetic_energy()

    def is_in_original_pos(self):
        return self.original_x == self.x and self.original_y == self.y and self.original_z == self.z


moons = []
with open('input') as fp:
    line = fp.readline()
    while line:
        x, y, z = line.replace('<', '').replace('>', '').replace('=', '').replace(',', '').replace('x', '').replace('y', '').replace('z', '').rstrip().split(' ')
        moons.append(Moon(int(x), int(y), int(z)))
        line = fp.readline()
drifted = True
steps = 1
while drifted:
    steps += 1
    for i in range(0, len(moons) - 1):
        for j in range(i + 1, len(moons)):
            moons[i].update_velocity(moons[j])
            moons[j].update_velocity(moons[i])
    drifted = False
    for moon in moons:
        moon.apply_velocity()
        if not moon.is_in_original_pos():
            drifted = True
print(steps)
