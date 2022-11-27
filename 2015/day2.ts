import {getInput} from "./helper";

let dimensions: string[] = getInput('input/day2.txt').split('\n')

let wrappingPaper: number = 0
let ribbon: number = 0

for (let i = 0; i < dimensions.length; i++) {
    let dimension: string = dimensions[i]
    let dimensionParts: string[] = dimension.split("x")
    let l: number = parseInt(dimensionParts[0])
    let w: number = parseInt(dimensionParts[1])
    let h: number = parseInt(dimensionParts[2])
    let sortedDimensions: number[] = [l, w, h].sort((a, b) => a - b)
    wrappingPaper += (2 * l * h) + (2 * l * w) + (2 * w * h) + (sortedDimensions[0] * sortedDimensions[1])
    ribbon += (2 * sortedDimensions[0]) + (2 * sortedDimensions[1]) + (l * w * h)
}

console.log(wrappingPaper)
console.log(ribbon)