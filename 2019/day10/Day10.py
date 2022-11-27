from math import atan2, degrees

asteroid_map = {}
asteroid_coords = []

with open('input') as fp:
    x = 0
    y = 0
    line = fp.readline().rstrip()
    while line:
        asteroid_map[y] = []
        for char in line:
            asteroid_map[y].append(char)
            if char == '#':
                asteroid_coords.append((x, y))
            x += 1
        x = 0
        y += 1
        line = fp.readline().rstrip()

stations = {}
for asteroid1_x, asteroid1_y in asteroid_coords:
    station = (asteroid1_x, asteroid1_y)
    stations[station] = {}
    for asteroid2_x, asteroid2_y in asteroid_coords:
        if asteroid1_x == asteroid2_x and asteroid1_y == asteroid2_y:
            continue
        angle = degrees(atan2(asteroid1_y - asteroid2_y, asteroid1_x - asteroid2_x))
        if angle < 0:
            angle = angle + 360
        else:
            angle = angle
        angle += 360
        angle -= 360
        if angle not in stations[station]:
            stations[station][angle] = []
        stations[station][angle].append((asteroid2_x, asteroid2_y))
most_asteroids = 0
monitoring_coords = None
for station in stations:
    count = len(stations[station])
    if count > most_asteroids:
        most_asteroids = count
        monitoring_coords = station
print(most_asteroids)
print(monitoring_coords)
angles_from_monitor = stations[monitoring_coords]
sorted_angles = []
station_x, station_y = monitoring_coords
for angle in angles_from_monitor:
    if angle < 90:
        sorted_angles.append(angle + 360)
    else:
        sorted_angles.append(angle)
sorted_angles.sort()
vaporized = []
# TODO fix vaporizing function
# while len(angles_from_monitor) > 0:
#     for angle in sorted_angles:
#         first_to_hit = None
#         min_dist = -1
#         if angle >= 360:
#             key = angle - 360
#         else:
#             key = angle
#         for x, y in angles_from_monitor[key]:
#             dist = abs(station_x - x) + abs(station_y - y)
#             if min_dist == -1 or dist < min_dist:
#                 min_dist = dist
#                 first_to_hit = (x, y)
#         vaporized.append((x, y))
#         angles_from_monitor[key].remove((x, y))
#         if len(angles_from_monitor[key]) == 0:
#             angles_from_monitor.pop(key)
#             sorted_angles.remove(angle)
print(vaporized[199])
