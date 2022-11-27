from typing import Dict


class Coord:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y


def calculate_coords1(path: str):
    current_step = Coord(0, 0)
    coords = {}
    steps_taken = 0
    for step in path.split(','):
        direction = step[:1]
        count = int(step[1:])
        if direction == 'R':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x + 1, current_step.y)
                if current_step.x not in coords:
                    coords[current_step.x] = {}
                if current_step.y not in coords[current_step.x]:
                    coords[current_step.x][current_step.y] = steps_taken
        elif direction == 'L':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x - 1, current_step.y)
                if current_step.x not in coords:
                    coords[current_step.x] = {}
                if current_step.y not in coords[current_step.x]:
                    coords[current_step.x][current_step.y] = steps_taken
        elif direction == 'U':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x, current_step.y + 1)
                if current_step.x not in coords:
                    coords[current_step.x] = {}
                if current_step.y not in coords[current_step.x]:
                    coords[current_step.x][current_step.y] = steps_taken
        elif direction == 'D':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x, current_step.y - 1)
                if current_step.x not in coords:
                    coords[current_step.x] = {}
                if current_step.y not in coords[current_step.x]:
                    coords[current_step.x][current_step.y] = steps_taken
        else:
            print('Unknown direction ' + direction)
    return coords


def calculate_mutual_coords(path: str, existing_coords: Dict[int, Dict[int, int]]):
    current_step = Coord(0, 0)
    mutual_coords = {}
    steps_taken = 0
    for step in path.split(','):
        direction = step[:1]
        count = int(step[1:])
        if direction == 'R':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x + 1, current_step.y)
                if current_step.x in existing_coords and current_step.y in existing_coords[current_step.x]:
                    if current_step.x not in mutual_coords:
                        mutual_coords[current_step.x] = {}
                    if current_step.y not in mutual_coords[current_step.x]:
                        mutual_coords[current_step.x][current_step.y] = existing_coords[current_step.x][current_step.y] + steps_taken
        elif direction == 'L':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x - 1, current_step.y)
                if current_step.x in existing_coords and current_step.y in existing_coords[current_step.x]:
                    if current_step.x not in mutual_coords:
                        mutual_coords[current_step.x] = {}
                    if current_step.y not in mutual_coords[current_step.x]:
                        mutual_coords[current_step.x][current_step.y] = existing_coords[current_step.x][
                                                                            current_step.y] + steps_taken
        elif direction == 'U':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x, current_step.y + 1)
                if current_step.x in existing_coords and current_step.y in existing_coords[current_step.x]:
                    if current_step.x not in mutual_coords:
                        mutual_coords[current_step.x] = {}
                    if current_step.y not in mutual_coords[current_step.x]:
                        mutual_coords[current_step.x][current_step.y] = existing_coords[current_step.x][
                                                                            current_step.y] + steps_taken
        elif direction == 'D':
            for i in range(1, count + 1):
                steps_taken += 1
                current_step = Coord(current_step.x, current_step.y - 1)
                if current_step.x in existing_coords and current_step.y in existing_coords[current_step.x]:
                    if current_step.x not in mutual_coords:
                        mutual_coords[current_step.x] = {}
                    if current_step.y not in mutual_coords[current_step.x]:
                        mutual_coords[current_step.x][current_step.y] = existing_coords[current_step.x][
                                                                            current_step.y] + steps_taken
        else:
            print('Unknown direction ' + direction)
    return mutual_coords


with open('input') as fp:
    wire1path = fp.readline()
    wire2path = fp.readline()
wire1coords = calculate_coords1(wire1path)
mutual_coords = calculate_mutual_coords(wire2path, wire1coords)
first_intersect = None
first_intersect_distance = -1
for x in mutual_coords:
    for y in mutual_coords[x]:
        distance = mutual_coords[x][y]
        if first_intersect_distance < 0 or distance < first_intersect_distance:
            first_intersect_distance = distance
            first_intersect = Coord(x, y)
print(str(first_intersect.x) + ',' + str(first_intersect.y))
print(str(first_intersect_distance))
# closest_coord_distance = -1
# closest_coord = None
# for coord in mutual_coords:
#     distance = abs(coord.x) + abs(coord.y)
#     print(distance)
#     if closest_coord_distance == -1 or distance < closest_coord_distance:
#         closest_coord_distance = distance
#         closest_coord = coord
# print(str(closest_coord.x) + ',' + str(closest_coord.y))
# print(closest_coord_distance)
