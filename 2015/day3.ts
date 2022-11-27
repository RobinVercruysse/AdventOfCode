import {getInput} from "./helper";

let directions: string = getInput('input/day3.txt')
let visits: Map<string, number> = new Map<string, number>()

function visit(coordinates: string) {
    let visitCount: number = visits.get(coordinates)
    if (visitCount == undefined) {
        visitCount = 0
    }
    visits.set(coordinates, visitCount + 1)
}

class Santa {
    x: number = 0
    y: number = 0

    navigate(direction: string) {
        switch (direction) {
            case '<': {
                this.x--
                break
            }
            case '>': {
                this.x++
                break
            }
            case '^': {
                this.y++
                break
            }
            case 'v': {
                this.y--
                break
            }
            default: {
                console.error('unknown direction: ' + direction)
                break
            }
        }
    }

    reset() {
        this.x = 0
        this.y = 0
    }

    getCoordinates(): string {
        return this.x.toString() + ',' + this.y.toString()
    }
}

let humanSanta: Santa = new Santa()
let roboSanta: Santa = new Santa()
let currentSanta: Santa = humanSanta

visit(currentSanta.getCoordinates())
for (let i: number = 0; i < directions.length; i++) {
    let direction: string = directions[i]
    currentSanta.navigate(direction)
    visit(currentSanta.getCoordinates())
}
console.log('Houses visited by single santa: ' + visits.size)

visits.clear()
currentSanta.reset()

visit(currentSanta.getCoordinates())
for (let i: number = 0; i < directions.length; i++) {
    let direction: string = directions[i]
    currentSanta.navigate(direction)
    visit(currentSanta.getCoordinates())
    if (currentSanta == humanSanta) {
        currentSanta = roboSanta
    } else {
        currentSanta = humanSanta
    }
}
console.log('Houses visited by pair of santas: ' + visits.size)