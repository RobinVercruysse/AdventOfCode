from time import time


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
        return self.vel_x == 0 and self.vel_y == 0 and self.vel_z == 0 and self.original_x == self.x and self.original_y == self.y and self.original_z == self.z


def update_velocities(moon1: Moon, moon2: Moon):
    if moon1.x > moon2.x:
        moon1.vel_x += 1
        moon2.vel_x -= 1
    elif moon1.x < moon2.x:
        moon1.vel_x -= 1
        moon2.vel_x += 1
    if moon1.y > moon2.y:
        moon1.vel_y += 1
        moon2.vel_y -= 1
    elif moon1.y < moon2.y:
        moon1.vel_y -= 1
        moon2.vel_y += 1
    if moon1.z > moon2.z:
        moon1.vel_z += 1
        moon2.vel_z -= 1
    elif moon1.z < moon2.z:
        moon1.vel_z -= 1
        moon2.vel_z += 1


moons = []
with open('input') as fp:
    line = fp.readline()
    while line:
        x, y, z = line.replace('<', '').replace('>', '').replace('=', '').replace(',', '').replace('x', '').replace('y', '').replace('z', '').rstrip().split(' ')
        moons.append(Moon(int(x), int(y), int(z)))
        line = fp.readline()
drifted = True
steps = 1
moon_a = moons[0]
moon_b = moons[1]
moon_c = moons[2]
moon_d = moons[3]
start = time()
update_velocities(moon_a, moon_b)
update_velocities(moon_a, moon_c)
update_velocities(moon_a, moon_d)
update_velocities(moon_b, moon_c)
update_velocities(moon_b, moon_d)
update_velocities(moon_c, moon_d)
moon_a.apply_velocity()
moon_b.apply_velocity()
moon_c.apply_velocity()
moon_d.apply_velocity()
while not moon_a.is_in_original_pos() or not moon_b.is_in_original_pos() or not moon_c.is_in_original_pos() or not moon_d.is_in_original_pos():
    steps += 1
    update_velocities(moon_a, moon_b)
    update_velocities(moon_a, moon_c)
    update_velocities(moon_a, moon_d)
    update_velocities(moon_b, moon_c)
    update_velocities(moon_b, moon_d)
    update_velocities(moon_c, moon_d)
    moon_a.apply_velocity()
    moon_b.apply_velocity()
    moon_c.apply_velocity()
    moon_d.apply_velocity()
    if steps % 1000000 == 0:
        end = time()
        print(str(steps) + ' took ' + str((end - start) * 1000) + 'ms')
        start = time()
print(steps)
