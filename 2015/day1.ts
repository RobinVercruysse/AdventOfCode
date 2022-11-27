import {getInput} from "./helper";

let instructions: string = getInput('input/day1.txt')
let [floor, firstBasement] = navigate(instructions)
console.log("Floor: " + floor)
console.log("First basement index: " + firstBasement)

function navigate(instructions: string) {
    let firstBasement: number = -1
    let floor: number = 0
    for (let i = 0; i < instructions.length; i++) {
        let instruction: string = instructions[i]
        if (instruction == "(") {
            floor++
        } else {
            floor--
        }
        if (firstBasement < 0 && floor < 0) {
            firstBasement = i + 1
        }
    }
    return [floor, firstBasement]
}