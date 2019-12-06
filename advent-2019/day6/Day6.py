class Node:
    def __init__(self, name: str, parent: str):
        self.name = name
        self.parent = parent


orbit_mapping = {}
with open('input') as fp:
    line = fp.readline()
    while line:
        parts = line.split(')')
        orbitee = parts[0].rstrip()
        orbiter = parts[1].rstrip()
        orbit_mapping[orbiter] = Node(orbiter, orbitee)
        line = fp.readline()
total_orbits = 0
for node_name in orbit_mapping:
    current_node = orbit_mapping[node_name]
    orbits = 1
    while current_node.parent != 'COM':
        orbits += 1
        current_node = orbit_mapping[current_node.parent]
    total_orbits += orbits
print('total orbits: ' + str(total_orbits))

you_path = []
san_path = []
current_node = orbit_mapping['YOU']
while current_node.parent != 'COM':
    you_path.append(current_node.parent)
    current_node = orbit_mapping[current_node.parent]
current_node = orbit_mapping['SAN']
while current_node.parent != 'COM':
    san_path.append(current_node.parent)
    current_node = orbit_mapping[current_node.parent]
first_common_orbit = None
you_steps_to_common = 0
for node in you_path:
    you_steps_to_common += 1
    if node in san_path:
        first_common_orbit = node
        break
san_steps_to_common = 0
for node in san_path:
    san_steps_to_common += 1
    if node == first_common_orbit:
        break
print('Orbital transfers to santa: ' + str(you_steps_to_common + san_steps_to_common - 2))
